package org.example.searadar.mr231_3;

import org.example.searadar.mr231_3.convert.Mr231_3Converter;
import org.example.searadar.mr231_3.station.Mr231_3StationType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.oogis.searadar.api.message.InvalidMessage;
import ru.oogis.searadar.api.message.SearadarStationMessage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    Mr231_3StationType mr231_3 = new Mr231_3StationType();
    Mr231_3Converter mr231_3Converter = mr231_3.createConverter();
    List<SearadarStationMessage> searadarMessages;

    String getSubstring (String input) {
        int secondCommaIndex = input.indexOf(',', input.indexOf(',') + 1);

        if (secondCommaIndex == -1) {
            return "ru.oogis.searadar.api.message.InvalidMessage";
        } else {
            return "{" + input.substring(secondCommaIndex + 2);
        }
    }

    @Test
    void parseMinValuesTTM() {
        String expected = "{targetNumber=1, distance=0.0, bearing=0.0, course=0.0, speed=0.0, type=UNKNOWN, status=UNRELIABLE_DATA, iff=UNKNOWN, interval=0}";

        String message = "$RATTM,1,00.00,000.0,T,00.0,000.0,T,0.0,00.0,N,d,Q,,0,А*1";
        searadarMessages = mr231_3Converter.convert(message);
        String result = getSubstring(searadarMessages.get(0).toString());

        Assertions.assertEquals(expected, result);
    }

    @Test
    void parseMaxValuesTTM() {
        String expected = "{targetNumber=50, distance=32.0, bearing=359.9, course=359.9, speed=90.0, type=UNKNOWN, status=UNRELIABLE_DATA, iff=UNKNOWN, interval=99999}";

        String message = "$RATTM,50,32.00,359.9,T,90.0,359.9,T,9.9,99.9,N,d,Q,,99999,А*1";
        searadarMessages = mr231_3Converter.convert(message);
        String result = getSubstring(searadarMessages.get(0).toString());

        Assertions.assertEquals(expected, result);
    }

    @Test
    void parseOutOfRangeValueTTM() {
        String expected = "ru.oogis.searadar.api.message.InvalidMessage";

        String message = "$RATTM,50,32.00,359.9,T,95.0,359.9,T,9.9,99.9,N,d,Q,,99999,А*1";
        searadarMessages = mr231_3Converter.convert(message);
        String result = getSubstring(searadarMessages.get(0).toString());

        Assertions.assertEquals(expected, result);
    }

    @Test
    void parseMinValuesRSD() {
        String expected = "{initialBearing=0.0, movingCircleOfDistance=0.0, bearing=0.0, distanceFromShip=0.0, bearing2=0.0, distanceScale=0.125, distanceUnit=K, displayOrientation=H, workingMode=S}";

        String message = "$RARSD,1,0,0,0,,,,,0,0,0.125,K,H,S*1";
        searadarMessages = mr231_3Converter.convert(message);
        String result = getSubstring(searadarMessages.get(0).toString());

        Assertions.assertEquals(expected, result);
    }

    @Test
    void parseMaxValuesRSD() {
        String expected = "{initialBearing=359.9, movingCircleOfDistance=99.9, bearing=359.9, distanceFromShip=99.9, bearing2=359.9, distanceScale=96.0, distanceUnit=K, displayOrientation=H, workingMode=S}";

        String message = "$RARSD,99.9,359.9,99.9,359.9,,,,,99.9,359.9,96.0,K,H,S*1";
        searadarMessages = mr231_3Converter.convert(message);
        String result = getSubstring(searadarMessages.get(0).toString());

        Assertions.assertEquals(expected, result);
    }

    @Test
    void parseIncorrectDistanceValueRSD() {
        String expected = "ru.oogis.searadar.api.message.InvalidMessage";

        String message = "$RARSD,99.9,359.9,99.9,359.9,,,,,99.9,359.9,97.0,K,H,S*1";
        searadarMessages = mr231_3Converter.convert(message);
        String result = getSubstring(searadarMessages.get(0).toString());

        Assertions.assertEquals(expected, result);
    }
}
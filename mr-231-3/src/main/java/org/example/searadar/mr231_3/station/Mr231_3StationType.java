package org.example.searadar.mr231_3.station;

import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;

import org.example.searadar.mr231_3.convert.Mr231_3Converter;
import ru.oogis.searadar.api.convert.SearadarExchangeConverter;
import ru.oogis.searadar.api.station.AbstractStationType;

import java.nio.charset.Charset;

/**
 * Класс протокола МР-231-3.
 *
 * @author Вадим Комиссаров
 * @version 1.0
 */
public class Mr231_3StationType {

    /** Поле название протокола */
    private static final String STATION_TYPE = "МР-231-3";

    /** Поле название кодека */
    private static final String CODEC_NAME = "mr231_3";

    protected void doInitialize() {
        TextLineCodecFactory textLineCodecFactory = new TextLineCodecFactory(
                Charset.defaultCharset(),
                LineDelimiter.UNIX,
                LineDelimiter.CRLF
        );
    }

    /**
     * Метод создания конвертера для протокола МР-231-3
     * @return экземпляр класса {@link Mr231_3Converter}
     */
    public Mr231_3Converter createConverter() {
        return new Mr231_3Converter();
    }
}

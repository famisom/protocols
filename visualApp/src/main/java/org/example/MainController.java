package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import org.example.searadar.mr231.convert.Mr231Converter;
import org.example.searadar.mr231.station.Mr231StationType;
import org.example.searadar.mr231_3.convert.Mr231_3Converter;
import org.example.searadar.mr231_3.station.Mr231_3StationType;
import ru.oogis.searadar.api.message.SearadarStationMessage;

public class MainController {
    @FXML
    private ComboBox<String> messages;
    @FXML
    private ComboBox<String> protocols;
    @FXML
    private TextField message;
    @FXML
    private Button parse;
    @FXML
    private Label result;

    public MainController() {
    }

    public void initialize() {
        Connection connection = DatabaseConnection.getConnection();
        this.messages.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.setResult(newValue, 0);
        });
        this.parse.setOnAction((event) -> {
            String selectedMessage = this.message.getText();
            String messageType = selectedMessage.substring(2, 6);
            int selectedProtocol = this.protocols.getSelectionModel().getSelectedIndex();
            if (selectedMessage.isEmpty()) {
                (new Alert(AlertType.ERROR, "Введите сообщение", new ButtonType[0])).show();
            } else if ("VHW".equals(messageType) && selectedProtocol == 1) {
                (new Alert(AlertType.ERROR, "Выбранный протокол не поддерживает данный формат сообщения", new ButtonType[0])).show();
            } else {
                try {
                    String sql = "CALL searadar.add_message(?, ?);";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setInt(1, selectedProtocol + 1);
                    statement.setString(2, selectedMessage);
                    statement.executeUpdate();
                    this.setResult(selectedMessage, selectedProtocol);
                } catch (SQLException var8) {
                    (new Alert(AlertType.ERROR, var8.getMessage(), new ButtonType[0])).show();
                    var8.printStackTrace();
                }

            }
        });
    }

    private void setResult(String message, int protocol) {
        List searadarMessages;
        if (protocol == 0) {
            Mr231StationType mr231 = new Mr231StationType();
            Mr231Converter converter = mr231.createConverter();
            searadarMessages = converter.convert(message);
            this.result.setText(((SearadarStationMessage)searadarMessages.get(0)).toString());
        } else {
            Mr231_3StationType mr231_3 = new Mr231_3StationType();
            Mr231_3Converter converter = mr231_3.createConverter();
            searadarMessages = converter.convert(message);
            this.result.setText(((SearadarStationMessage)searadarMessages.get(0)).toString());
        }

        this.result.setVisible(true);
    }
}

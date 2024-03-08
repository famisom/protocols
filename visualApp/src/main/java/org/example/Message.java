package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Message {
    private int id;
    private int protocolId;
    private String text;

    public Message(int id, int protocolId, String text) {
        this.id = id;
        this.protocolId = protocolId;
        this.text = text;
    }

    public static ObservableList<String> loadMessages() {
        ObservableList<String> messages = FXCollections.observableArrayList();

        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT message FROM searadar.messages");

            while(result.next()) {
                messages.add(result.getString(1));
            }

            result.close();
            statement.close();
            connection.close();
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return messages;
    }
}

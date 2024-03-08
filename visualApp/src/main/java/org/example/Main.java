package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("main.fxml"));
        primaryStage.setTitle("Парсер");
        primaryStage.setScene(new Scene(root, 700.0, 400.0));
        primaryStage.show();
        ComboBox<String> protocols = (ComboBox)root.lookup("#protocols");
        protocols.getItems().setAll(new String[]{"МР-231", "МР-231-3"});
        protocols.setValue(protocols.getItems().get(0));
        ComboBox<String> messages = (ComboBox)root.lookup("#messages");
        messages.setItems(Message.loadMessages());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
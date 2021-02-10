package com.evgeny_k.lesson_2.client;

import com.evgeny_k.lesson_2.server.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    private ServerService serverService;
    private Boolean isAuthorized = false;
    public VBox allPanels;
    public TextField newMessage;
    public TextField login;
    public TextField password;
    public TextArea allMessages;
    public GridPane pane;
    public Label authLabel;

    public Controller() {
        allMessages = new TextArea();
        serverService = new SocketServerService();
        serverService.openConnection();

        if (serverService.isConnected()) {
            new Thread(() -> {
                while (true) {
                    printToUI(allMessages, serverService.readMessages());
                }
            }).start();
        }

        new Thread(() -> {
            try {
                Thread.sleep(120000);
                if (!isAuthorized) {
                    allMessages.appendText("Authorisation timeout");
                    Thread.sleep(2000);
                    serverService.closeConnection();
                    Platform.exit();
                    System.exit(0);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void sendMessage(ActionEvent actionEvent) {
        String text = newMessage.getText();
        if (!text.equals("")) {
            if (serverService.isConnected())
                serverService.sendMessage(text);
            newMessage.clear();

        }
        newMessage.requestFocus();
    }

    public void Authorize(ActionEvent actionEvent) {
        String lgn = login.getText();
        String psw = password.getText();
        if (lgn != null && psw != null && !lgn.isEmpty() && !psw.isEmpty()) {
            try {
                String nick = serverService.authorization(lgn, psw);
                isAuthorized = true;
                authLabel.setText("Online, nick " + nick);
            } catch (IOException e) {
                e.printStackTrace();
            }
            new Thread(() -> {
                while (true) {
                    printToUI(allMessages, serverService.readMessages());
                }
            }).start();
        }
    }

    private void printToUI(TextArea chat, Message message) {
        chat.appendText("\n");
        chat.appendText((message.getNick() != null ? message.getNick() : "Сервер") + " написал: " + message.getMessage());
    }

}

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

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
    private String nick;
    private String fileName;


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
                nick = serverService.authorization(lgn, psw);
                isAuthorized = true;
                authLabel.setText("Online, nick " + nick);
                fileName = "history_" + nick + ".txt";
                getHistory();

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
        String msg = message.getMessage();
        if (msg != null) {
            String stringMessage = (message.getNick() != null ? message.getNick() : "Сервер") + " написал: " + msg;
            chat.appendText(stringMessage);
            try {
                saveToHistory(stringMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void getHistory() {
        try {
            FileReader fileReader = new FileReader(fileName);
            try (BufferedReader reader = new BufferedReader(fileReader)) {
                String line;
                ArrayList<String> lines = new ArrayList<>();

                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }

                int linesCount = lines.size();
                int start = linesCount > 100 ? linesCount - 100 : 0;

                new Thread(() -> {
                    for (int i = start; i < lines.size(); i++) {
                        System.out.println("i = " + i + " = " + lines.get(i));
                        allMessages.appendText(lines.get(i) + "\n");
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            } catch (IOException | NullPointerException | ArrayIndexOutOfBoundsException e) {
                System.out.println("вот тут упали");
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToHistory(String message) throws IOException {
        try {
            File historyFile = new File(fileName);
            if (!historyFile.exists()) {
                historyFile.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            bufferWriter.write("\n" + message);
            bufferWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

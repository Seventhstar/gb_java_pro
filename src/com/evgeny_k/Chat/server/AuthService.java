package com.evgeny_k.Chat.server;

public interface AuthService {
    void start();
    void stop();
    String getNickByLoginAndPass(String login, String password);
}

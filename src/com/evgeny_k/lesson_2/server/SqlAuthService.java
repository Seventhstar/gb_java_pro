package com.evgeny_k.lesson_2.server;

import java.sql.*;

public class SqlAuthService implements AuthService {
    private static Connection connection;
    private static Statement statement;


    public SqlAuthService() {

        try {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection("jdbc:sqlite:auth.sqlite");

            Statement st = connection.createStatement();
//            connection.setAutoCommit(false);
//            st.execute("CREATE TABLE IF NOT EXISTS Users" +
//                    "(" +
//                    "id SERIAL PRIMARY KEY," +
//                    "login VARCHAR(25)," +
//                    "password VARCHAR(16)," +
//                    "nick VARCHAR(16)");
//            connection.commit();

            ResultSet rs = st.executeQuery("select * from Users");
            if (!rs.next()) {
                st.execute("INSERT INTO Users VALUES " +
                        "(Null, \"sharik\", \"gav\", \"Auf\")," +
                        "(Null, \"ivan\", \"password\", \"Neivanov\"), " +
                        "(Null, \"otvertka\", \"shurup\", \"Kruchu-verchu\");");

            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    @Override
    public void start() {
        System.out.println("Сервис авторизации запущен");
    }

    @Override
    public void stop() {
        System.out.println("Сервис авторизации остановлен");
    }

    @Override
    public String getNickByLoginAndPass(String login, String password) {

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:auth.sqlite");
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(String.format("select * from Users where login = '%s' and password = '%s'", login, password));
            if (rs.next()) {
                String nick = rs.getString(3);
                connection.close();
                return nick;
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
}

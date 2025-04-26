package com.sistemadegestaodeprojetos;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class Teste {
    private static final String URL = "jdbc:mysql://localhost:3306/sistemadegestaodeprojetos?useTimezone=true&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Certifique-se de usar a classe correta para o MySQL 8
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL não encontrado", e);
        }
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Conexão com o MySQL bem-sucedida!");
                // Adicione aqui o código para interagir com o banco de dados
            } else {
                System.out.println("Conexão com o MySQL falhou.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao MySQL: " + e.getMessage());
        }
    }
}

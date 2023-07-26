/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ucan.ucanwallet.conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author amari
 */
public class FabricaConexaoPostgres {

    private static final String URL = "jdbc:postgresql://localhost:5432/ucanwallet";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "postgres";

    private static FabricaConexaoPostgres instancia;
    private Connection connection;

    private FabricaConexaoPostgres() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized FabricaConexaoPostgres getInstancia() {
        if (instancia == null) {
            instancia = new FabricaConexaoPostgres();
        }
        return instancia;
    }

    public Connection getConnection() {
        return connection;
    }
}

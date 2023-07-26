/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ucan.ucanwallet.dao;

import edu.ucan.ucanwallet.model.Cliente;
import edu.ucan.ucanwallet.model.Pessoa;
import edu.ucan.ucanwallet.model.Utilizador;
import edu.ucan.ucanwallet.util.TipoCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author amari
 */
public class ClienteDao {

    private Connection connection;

    public ClienteDao(Connection connection) {
        this.connection = connection;
    }

    public List<Cliente> get() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                clientes.add(criarCliente(rs));
            }
        }
        return clientes;
    }

    public Cliente getById(int pk_cliente) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE pk_cliente=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pk_cliente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarCliente(rs);
                }
            }
        }
        return null;
    }

    public Cliente getByNif(String nif) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE nif=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nif);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarCliente(rs);
                }
            }
        }
        return null;
    }

    public Cliente getByUtilizadorId(int fk_utilizador) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE fk_utilizador=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, fk_utilizador);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarCliente(rs);
                }
            }
        }
        return null;
    }

    public void create(Cliente cliente) throws SQLException {

        String sql = "INSERT INTO clientes (nif, tipo_cliente, fk_utilizador) VALUES (?, CAST(? AS tipo_cliente_enum), ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNif());
            stmt.setObject(2, cliente.getTipo_cliente().name());
            stmt.setInt(3, cliente.getFk_utilizador());
            stmt.executeUpdate();
        }
    }

    private Cliente criarCliente(ResultSet rs) throws SQLException {
        int pk_cliente = rs.getInt("pk_cliente");
        String nif = rs.getString("nif");
        String tipo_cliente = rs.getString("tipo_cliente");
        int fk_utilizador = rs.getInt("fk_utilizador");

        return new Cliente(pk_cliente, fk_utilizador, nif, TipoCliente.valueOf(tipo_cliente));
    }

    private Utilizador buscarUtilizador(Cliente cliente) throws SQLException {
        Utilizador utilizador = null;
        UtilizadorDao utilizadorDao = new UtilizadorDao(connection);
        utilizador = utilizadorDao.buscarPorId(cliente.getFk_utilizador());
        return utilizador;
    }

}

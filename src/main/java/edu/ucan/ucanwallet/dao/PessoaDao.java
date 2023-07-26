/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ucan.ucanwallet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import edu.ucan.ucanwallet.model.Pessoa;

/**
 *
 * @author amari
 */
public class PessoaDao {

    private Connection connection;

    public PessoaDao(Connection connection) {
        this.connection = connection;
    }

    public Pessoa create(Pessoa pessoa) throws SQLException {
        String sql = "INSERT INTO pessoas (nome, sobrenome, nif)"
                + "VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getSobrenome());
            stmt.setString(3, pessoa.getNif());
            stmt.executeUpdate();
        }
        
        return getPorNif(pessoa.getNif());

    }

    public Pessoa getPorNif(String nif ) throws SQLException {
        String sql = "SELECT * FROM pessoas WHERE nif=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nif);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarPessoa(rs);
                }
            }
        }
        return null;
    }
    
    public Pessoa getPorId(int id) throws SQLException {
        String sql = "SELECT * FROM pessoas WHERE pk_pessoa=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarPessoa(rs);
                }
            }
        }
        return null;
    }

    public List<Pessoa> get() throws SQLException {
        List<Pessoa> utilizadores = new ArrayList<>();
        String sql = "SELECT * FROM pessoas";
        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                utilizadores.add(criarPessoa(rs));
            }
        }
        return utilizadores;
    }

    private Pessoa criarPessoa(ResultSet rs) throws SQLException {
        int pk_pessoa = rs.getInt("pk_pessoa");
        String nome = rs.getString("nome");
        String sobrenome = rs.getString("sobrenome");
        String nif = rs.getString("nif");

        return new Pessoa(pk_pessoa, nome, sobrenome, nif);
    }
}

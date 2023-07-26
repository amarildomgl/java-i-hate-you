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
import edu.ucan.ucanwallet.model.Utilizador;
import edu.ucan.ucanwallet.model.Pessoa;
import edu.ucan.ucanwallet.model.Cliente;

import edu.ucan.ucanwallet.util.NivelAcessoEnum;
import edu.ucan.ucanwallet.util.SenhaHasher;

/**
 *
 * @author amari
 */
public class UtilizadorDao {

    private Connection connection;

    public UtilizadorDao(Connection connection) {
        this.connection = connection;
    }

    public void create(Utilizador utilizador) throws SQLException {
        String sql = "INSERT INTO utilizadores (login, senha, fk_pessoa) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, utilizador.getLogin());
            String hasSenha = SenhaHasher.gerarHashSenha(utilizador.getSenha());
            stmt.setString(2, hasSenha);
            stmt.setInt(3, utilizador.getFk_pessoa());
            stmt.executeUpdate();
        }
    }

    public void atualizar(Utilizador utilizador) throws SQLException {
        String sql = "UPDATE utilizadors SET login=?, senha=? WHERE pk_usuario=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, utilizador.getLogin());
            String hasSenha = SenhaHasher.gerarHashSenha(utilizador.getSenha());
            stmt.setString(2, hasSenha);
            stmt.executeUpdate();
        }
    }

    public void excluir(int pkUsuario) throws SQLException {
        String sql = "DELETE FROM utilizadores WHERE pk_usuario=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pkUsuario);
            stmt.executeUpdate();
        }
    }

    public Utilizador buscarPorId(int pkUsuario) throws SQLException {
        String sql = "SELECT * FROM utilizadores WHERE pk_usuario=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pkUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarUtilizador(rs);
                }
            }
        }
        return null;
    }

    public Utilizador buscarPorLogin(String login) throws SQLException {
        String sql = "SELECT * FROM utilizadores WHERE login=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, login);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarUtilizador(rs);
                }
            }
        }
        return null;
    }

    public List<Utilizador> buscarTodos() throws SQLException {
        List<Utilizador> utilizadores = new ArrayList<>();
        String sql = "SELECT * FROM utilizadores";
        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                utilizadores.add(criarUtilizador(rs));
            }
        }
        return utilizadores;
    }

    private Utilizador criarUtilizador(ResultSet rs) throws SQLException {
        int pkUsuario = rs.getInt("pk_usuario");
        String login = rs.getString("login");
        String senha = rs.getString("senha");
        int fkPessoa = rs.getInt("fk_pessoa");
        String nivel_acesso = rs.getString("nivel_acesso");

        return new Utilizador(pkUsuario, login, senha, fkPessoa, NivelAcessoEnum.valueOf(nivel_acesso));
    }

    private Pessoa buscarPessoa(Utilizador utilizador) throws SQLException {
        Pessoa pessoa = null;
        PessoaDao pessoaDao = new PessoaDao(connection);
        pessoa = pessoaDao.getPorId(utilizador.getFk_pessoa());
        return pessoa;
    }

    private Cliente buscarCliente(Utilizador utilizador) throws SQLException {
        Cliente cliente = null;
        ClienteDao clienteDao = new ClienteDao(connection);
        cliente = clienteDao.getByUtilizadorId(utilizador.getPk_usuario());

        return cliente;
    }

}

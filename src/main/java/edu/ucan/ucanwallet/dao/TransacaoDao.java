/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ucan.ucanwallet.dao;

import edu.ucan.ucanwallet.model.Transacao;
import edu.ucan.ucanwallet.util.EstadoTransacao;
import edu.ucan.ucanwallet.util.TipoMovimento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author amari
 */
public class TransacaoDao {

    private Connection connection;

    public TransacaoDao(Connection connection) {
        this.connection = connection;
    }

    public List<Transacao> getAllTransacoes() throws SQLException {
        List<Transacao> transacoes = new ArrayList<>();
        String sql = "SELECT * FROM transacoes";
        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                transacoes.add(createTransacaoFromResultSet(rs));
            }
        }
        return transacoes;
    }

    public Transacao getTransacaoById(int pk_transacao) throws SQLException {
        String sql = "SELECT * FROM transacoes WHERE pk_transacao=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pk_transacao);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return createTransacaoFromResultSet(rs);
                }
            }
        }
        return null;
    }

    public List<Transacao> getTransacaoByConta(UUID conta) throws SQLException {
        List<Transacao> transacoes = new ArrayList<>();
        String sql = "SELECT * FROM transacoes WHERE conta=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, conta);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    transacoes.add(createTransacaoFromResultSet(rs));
                }
            }
        }
        return transacoes;
    }

    public void createTransacao(Transacao transacao) throws SQLException {
        String sql = "INSERT INTO transacoes (conta, conta_destino, valor, data_transacao, tipo_movimento, estado_transacao) "
                + "VALUES (?, ?, ?, ?, CAST(? AS tipo_movimento_enum), CAST(? AS estado_transacao_enum))";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, transacao.getConta());
            stmt.setObject(2, transacao.getConta_destino());
            stmt.setDouble(3, transacao.getValor());
            stmt.setObject(4, transacao.getData_transacao());
            stmt.setObject(5, transacao.getTipo_movimento().name());
            stmt.setObject(6, transacao.getEstado_transacao().name());
            stmt.executeUpdate();
        }
    }

    public void updateTransacao(Transacao transacao) throws SQLException {
        String sql = "UPDATE transacoes SET conta=?, conta_destino=?, valor=?, data_transacao=?, "
                + "tipo_movimento=CAST(? AS tipo_movimento_enum), estado_transacao=CAST(? AS estado_transacao_enum) "
                + "WHERE pk_transacao=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, transacao.getConta());
            stmt.setObject(2, transacao.getConta_destino());
            stmt.setDouble(3, transacao.getValor());
            stmt.setObject(4, transacao.getData_transacao());
            stmt.setObject(5, transacao.getTipo_movimento().name());
            stmt.setObject(6, transacao.getEstado_transacao().name());
            stmt.setInt(7, transacao.getPk_transacao());
            stmt.executeUpdate();
        }
    }

    private Transacao createTransacaoFromResultSet(ResultSet rs) throws SQLException {
        int pk_transacao = rs.getInt("pk_transacao");
        UUID conta = (UUID) rs.getObject("conta");
        UUID conta_destino = (UUID) rs.getObject("conta_destino");
        double valor = rs.getDouble("valor");
        LocalDateTime data_transacao = rs.getTimestamp("data_transacao").toLocalDateTime();
        TipoMovimento tipo_movimento = TipoMovimento.valueOf(rs.getString("tipo_movimento"));
        EstadoTransacao estado_transacao = EstadoTransacao.valueOf(rs.getString("estado_transacao"));

        return new Transacao(pk_transacao, conta, conta_destino, valor, data_transacao, tipo_movimento, estado_transacao);
    }
}

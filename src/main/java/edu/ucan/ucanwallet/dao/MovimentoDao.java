/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ucan.ucanwallet.dao;

import edu.ucan.ucanwallet.model.Movimento;
import edu.ucan.ucanwallet.model.Pessoa;
import edu.ucan.ucanwallet.util.TipoMovimento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author amari
 */
public class MovimentoDao {

    private Connection connection;

    public MovimentoDao(Connection connection) {
        this.connection = connection;
    }

    public void create(Movimento movimento) throws SQLException {
        String sql = "INSERT INTO movimentos (descricao, conta, valor, tipo_movimento)"
                + "VALUES (?, ?, ?, CAST(? AS tipo_movimento_enum))";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, movimento.getDescricao());
            stmt.setObject(2, movimento.getConta());
            stmt.setDouble(3, movimento.getValor());
            stmt.setString(4, movimento.getTipo_movimento().name());

            stmt.executeUpdate();
        }

    }

    public List<Movimento> get(UUID conta) throws SQLException {
        List<Movimento> movimentos = new ArrayList<>();
        String sql = "SELECT * FROM movimentos WHERE conta=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, conta);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    movimentos.add(criarMovimento(rs));
                }
            }

        }
        return movimentos;
    }

    private Movimento criarMovimento(ResultSet rs) throws SQLException {

        String descricao = rs.getString("descricao");
        UUID conta = (UUID) rs.getObject("conta");
        double valor = rs.getDouble("valor");
        TipoMovimento tipo_movimento = TipoMovimento.valueOf(rs.getString("tipo_movimento"));

        return new Movimento(descricao, conta, valor, tipo_movimento);
    }
}

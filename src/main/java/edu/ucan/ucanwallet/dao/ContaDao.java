/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ucan.ucanwallet.dao;

import edu.ucan.ucanwallet.encirptacao.ChaveUtil;
import edu.ucan.ucanwallet.model.Conta;
import edu.ucan.ucanwallet.util.TipologiaConta;
import edu.ucan.ucanwallet.util.EstadoConta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.security.PrivateKey;
import java.security.PublicKey;


/**
 *
 * @author amari
 */
public class ContaDao {

    private Connection connection;

    public ContaDao(Connection connection) {
        this.connection = connection;
    }

    public List<Conta> get() throws SQLException, Exception {
        List<Conta> contas = new ArrayList<>();
        String sql = "SELECT * FROM contas";
        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                contas.add(criarConta(rs));
            }
        }
        return contas;
    }

    public Conta getByNumero(UUID numero) throws SQLException, Exception {
        String sql = "SELECT * FROM contas WHERE numero=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, numero);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarConta(rs);
                }
            }
        }
        return null;
    }

    public Conta getByCliente(int fk_cliente) throws SQLException, Exception {
        String sql = "SELECT * FROM contas WHERE fk_cliente=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, fk_cliente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarConta(rs);
                }
            }
        }
        return null;
    }

    public List<Conta> getByClienteFk(int fk_cliente) throws SQLException, Exception {
        List<Conta> contas = new ArrayList<>();
        String sql = "SELECT * FROM contas WHERE fk_cliente=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, fk_cliente);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    contas.add(criarConta(rs));
                }
            }
        }
        return contas;
    }

    public List<Conta> getByEstadoConta(EstadoConta estadoConta) throws SQLException, Exception {
        List<Conta> contas = new ArrayList<>();
        String sql = "SELECT * FROM contas WHERE estado_conta=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, estadoConta.name());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    contas.add(criarConta(rs));
                }
            }
        }
        return contas;
    }

    public List<Conta> getByTipologiaConta(TipologiaConta tipologiaConta) throws SQLException, Exception {
        List<Conta> contas = new ArrayList<>();
        String sql = "SELECT * FROM contas WHERE tipologia_conta=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, tipologiaConta.name());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    contas.add(criarConta(rs));
                }
            }
        }
        return contas;
    }

    public void create(Conta conta) throws SQLException {
        String sql = "INSERT INTO contas (fk_cliente, numero, saldo_contabilistico, "
                + "saldo_disponivel, tipologia_conta, estado_conta, chave_publica, chave_privada)"
                + " VALUES (?, ?, ?, ?, CAST(? AS tipologia_conta_enum), CAST(? AS estado_conta_enum), ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, conta.getFk_cliente());
            stmt.setObject(2, conta.getNumero());
            stmt.setDouble(3, conta.getSaldo_contablistico());
            stmt.setDouble(4, conta.getSaldo_disponivel());
            stmt.setObject(5, conta.getTipologia_conta().name());
            stmt.setObject(6, conta.getEstado_conta().name());
            stmt.setBytes(7, conta.getChave_publica().getEncoded());
            stmt.setBytes(8, conta.getChave_privada().getEncoded());
            stmt.executeUpdate();
        }
    }

    public void updateSaldo(Conta conta) throws SQLException {
        String sql = "UPDATE contas SET saldo_contabilistico=?, saldo_disponivel=? WHERE numero=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, conta.getSaldo_contablistico());
            stmt.setDouble(2, conta.getSaldo_disponivel());
            stmt.setObject(3, conta.getNumero());
            stmt.executeUpdate();
        }
    }
    
    public void update(Conta conta) throws SQLException {
        String sql = "UPDATE contas SET saldo_contabilistico=?, saldo_disponivel=?, CAST(? AS tipologia_conta_enum), CAST(? AS estado_conta_enum) WHERE numero=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, conta.getSaldo_contablistico());
            stmt.setDouble(2, conta.getSaldo_disponivel());
            stmt.setObject(3, conta.getTipologia_conta().name());
            stmt.setObject(4, conta.getEstado_conta().name());
            stmt.setObject(5, conta.getNumero());
            stmt.executeUpdate();
        }
    }

    private Conta criarConta(ResultSet rs) throws SQLException, Exception {
        UUID numero = (UUID) rs.getObject("numero");
        int fk_cliente = rs.getInt("fk_cliente");
        double saldo_contabilistico = rs.getDouble("saldo_contabilistico");
        double saldo_disponivel = rs.getDouble("saldo_disponivel");
        String tipologia_conta = rs.getString("tipologia_conta");
        String estado_conta = rs.getString("estado_conta");
        
        PublicKey chave_publica = ChaveUtil.bytesParaChavePublica( rs.getBytes("chave_publica"));        
        PrivateKey chave_privada = ChaveUtil.butesParaChavePrivada(rs.getBytes("chave_privada"));
        
        Conta conta = new Conta(fk_cliente, TipologiaConta.valueOf(tipologia_conta));
        conta.setNumero(numero);
        conta.setSaldo_contablistico(saldo_contabilistico);
        conta.setSaldo_disponivel(saldo_disponivel);
        conta.setEstado_conta(EstadoConta.valueOf(estado_conta));
        conta.setChave_privada(chave_privada);
        conta.setChave_publica(chave_publica);

        return conta;
    }
}

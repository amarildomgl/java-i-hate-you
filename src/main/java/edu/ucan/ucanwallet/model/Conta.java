/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ucan.ucanwallet.model;

import edu.ucan.ucanwallet.encirptacao.ChaveUtil;
import edu.ucan.ucanwallet.encirptacao.GeradorDeChave;
import edu.ucan.ucanwallet.util.EstadoConta;
import edu.ucan.ucanwallet.util.TipoMovimento;
import edu.ucan.ucanwallet.util.TipologiaConta;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.KeyPair;

/**
 *
 * @author amari
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Conta {

    private int fk_cliente; 
    private UUID numero; 
    private double saldo_contablistico; 
    private double saldo_disponivel; 
    private TipologiaConta tipologia_conta; 
    private EstadoConta estado_conta; 
    private PublicKey chave_publica; 
    private PrivateKey chave_privada; 
    
    public Conta(int fk_cliente, TipologiaConta tipologia_conta) {
        this.fk_cliente = fk_cliente;
        this.numero = UUID.randomUUID();
        this.saldo_contablistico = 1000.0;
        this.saldo_disponivel = 1000.0;
        this.tipologia_conta = tipologia_conta;
        this.estado_conta = estado_conta.ACTIVA;

    }

    public void criarChaves() {

        KeyPair chaves = GeradorDeChave.gerarChave();
        this.chave_privada = chaves.getPrivate();
        this.chave_publica = chaves.getPublic();

    }

    public void actualizarSaldoDisponivel(Movimento movimento) {
        if (movimento.getTipo_movimento() == TipoMovimento.CREDITO) {
            this.saldo_disponivel += movimento.getValor();
        } else {
            this.saldo_disponivel -= movimento.getValor();
        }
    }

    public void actualizarSaldoContablistico(Movimento movimento) {
        if (movimento.getTipo_movimento() == TipoMovimento.CREDITO) {
            this.saldo_contablistico += movimento.getValor();
        } else {
            this.saldo_contablistico -= movimento.getValor();
        }
    }

}

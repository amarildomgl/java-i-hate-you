/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ucan.ucanwallet.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.ucan.ucanwallet.encirptacao.RSAUtil;
import edu.ucan.ucanwallet.util.EstadoTransacao;
import edu.ucan.ucanwallet.util.TipoMovimento;
import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author amari
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Transacao implements Serializable {
    
    private int pk_transacao;
    private UUID conta;
    private UUID conta_destino;
    private double valor;
    private LocalDateTime data_transacao;
    private TipoMovimento tipo_movimento;
    private EstadoTransacao estado_transacao;
    
    public TransacaoCriptografada criptografarTransacao(PublicKey publicKey) {
        TransacaoCriptografada tc
                = new TransacaoCriptografada();
        
        tc.setPk_transacao(pk_transacao);
        tc.setConta(conta);
        tc.setConta_destino(
                RSAUtil.encripitar(conta_destino,
                        publicKey)
        );
        tc.setValor(
                RSAUtil.encripitar(valor,
                        publicKey)
        );
        tc.setData_transacao(
                RSAUtil.encripitar(data_transacao,
                        publicKey)
        );
        tc.setTipo_movimento(
                RSAUtil.encripitar(tipo_movimento,
                        publicKey)
        );
        tc.setEstado_transacao(
                RSAUtil.encripitar(estado_transacao,
                        publicKey)
        );
        
        return tc;
    }
    
    public Transacao decriptografarTransacao(String json, PrivateKey privateKey) {
        
        Transacao transacao = new Transacao();
        TransacaoCriptografada tc = new TransacaoCriptografada().getObject(json);
        
        transacao.setPk_transacao(tc.getPk_transacao());
        transacao.setConta(tc.getConta());
        
        transacao.setConta_destino((UUID) RSAUtil.decriptar(
                tc.getConta_destino(),
                privateKey)
        );
        transacao.setValor((double) RSAUtil.decriptar(
                tc.getValor(),
                privateKey)
        );
        transacao.setData_transacao((LocalDateTime) RSAUtil.decriptar(
                tc.getData_transacao(),
                privateKey)
        );
        transacao.setTipo_movimento((TipoMovimento) RSAUtil.decriptar(
                tc.getTipo_movimento(),
                privateKey));
        transacao.setEstado_transacao((EstadoTransacao) RSAUtil.decriptar(
                tc.getEstado_transacao(),
                privateKey));
        
        return transacao;
        
    }
    
}

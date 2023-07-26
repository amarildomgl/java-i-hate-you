/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ucan.ucanwallet;

import edu.ucan.ucanwallet.encirptacao.ChaveUtil;
import edu.ucan.ucanwallet.encirptacao.RSAUtil;
import edu.ucan.ucanwallet.model.Conta;
import edu.ucan.ucanwallet.model.Transacao;
import edu.ucan.ucanwallet.model.TransacaoCriptografada;
import edu.ucan.ucanwallet.util.EstadoTransacao;
import edu.ucan.ucanwallet.util.TipoMovimento;
import edu.ucan.ucanwallet.util.TipologiaConta;
import java.time.LocalDateTime;
import java.util.Random;
import javax.jms.JMSException;
import javax.naming.NamingException;
import mensageria.ConsumidorMensagem;
import mensageria.ProdutorMensagem;

/**
 *
 * @author amari
 */
public class Main {

//    public static void main(String[] args) throws JMSException, NamingException {
//        
//
//        Conta conta = new Conta(1, TipologiaConta.DEBITO);
//        conta.criarChaves();
//
//        Transacao transacao
//                = new Transacao(1, conta.getNumero(),
//                        conta.getNumero(), 1000, LocalDateTime.now(),
//                        TipoMovimento.DEBITO, EstadoTransacao.NAO_VALIDADA
//                );
//      
//
//        TransacaoCriptografada tc
//                = transacao.criptografarTransacao(conta.getChave_publica());
//       
//         System.out.println("Transacão original: " + transacao.toString());
//        System.out.println("Transacão criptografada: " + tc.toString()); 
//        
//        Transacao t = transacao.decriptografarTransacao(tc.getGson(), 
//                conta.getChave_privada());
//        
//        System.out.println("Transacão decriptografada: " + t.toString());
     
//        String fila = "transacoes";
//        ProdutorMensagem cliente = new ProdutorMensagem(fila);
//        
//        cliente.enviarMensagem(tc.getGson());

//    }
}

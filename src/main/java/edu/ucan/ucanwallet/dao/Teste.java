/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ucan.ucanwallet.dao;

import edu.ucan.ucanwallet.conection.FabricaConexaoPostgres;
import edu.ucan.ucanwallet.model.Conta;
import edu.ucan.ucanwallet.model.Movimento;
import edu.ucan.ucanwallet.util.TipoMovimento;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

/**
 *
 * @author amari
 */
public class Teste {
    public static void main(String[] args) throws SQLException, Exception {
//         Connection connection = FabricaConexaoPostgres.getInstancia().getConnection();
//           
//         Conta conta = new Conta();
//         conta.criarChaves();
//         ContaDao contaDao = new ContaDao(connection);
//         
//         contaDao.create(conta);
//         
//         conta = contaDao.getByNumero(conta.getNumero());
//         
//        MovimentoDao md = new MovimentoDao(connection);
//        UUID numero = UUID.randomUUID();
//        Movimento m = new Movimento("transferencia", 
//                numero, 100, TipoMovimento.DEBITO);
//        md.create(m);
//        
//        System.out.println(conta);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ucan.ucanwallet.util;
import org.mindrot.jbcrypt.BCrypt;


/**
 *
 * @author amari
 */
public class SenhaHasher {

    public static String gerarHashSenha(String senha) {
        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }

    public static boolean verificarSenha(String senhaInformada, String hashSenhaArmazenado) {
        return BCrypt.checkpw(senhaInformada, hashSenhaArmazenado);
    }
}

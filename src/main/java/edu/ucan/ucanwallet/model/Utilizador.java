/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ucan.ucanwallet.model;

import edu.ucan.ucanwallet.util.NivelAcessoEnum;
import java.io.Serializable;
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
public class Utilizador implements Serializable {

    private int pk_usuario;
    private String login;
    private String senha;
    private int fk_pessoa;
    private NivelAcessoEnum nivel_acesso;

    public Utilizador(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Utilizador(String login, String senha, int fk_pessoa) {
        this.login = login;
        this.senha = senha;
        this.fk_pessoa = fk_pessoa;

    }


}

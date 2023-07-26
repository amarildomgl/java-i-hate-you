/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ucan.ucanwallet.model;

import edu.ucan.ucanwallet.util.SexoEnum;
import java.util.Date;
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
public class Pessoa {

    private int pk_pessoa;
    private String nome;
    private String sobrenome;
    private String nomePai;
    private String nomeMae;
    private String nif;
    private String residencia;
    private Date dataNascimento;
    private SexoEnum sexo;

    public Pessoa(String nome, String sobrenome, String nif) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.nif = nif;

    }

    public Pessoa(int pk_pessoa, String nome, String sobrenome, String nif) {
        this.pk_pessoa = pk_pessoa;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.nif = nif;

    }

}

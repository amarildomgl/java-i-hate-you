/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ucan.ucanwallet.model;

import edu.ucan.ucanwallet.util.TipoCliente;
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
public class Cliente implements Serializable{

    private int pk_cliente;
    private int fk_utilizador;    
    private String nif;
    private TipoCliente tipo_cliente;

    public Cliente(int fk_utilizador, String nif, TipoCliente tipo_cliente) {
        this.fk_utilizador = fk_utilizador;
        this.nif = nif;
        this.tipo_cliente = tipo_cliente;
    }
    
    

}

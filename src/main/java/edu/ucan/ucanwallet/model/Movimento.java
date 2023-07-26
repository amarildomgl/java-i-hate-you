/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ucan.ucanwallet.model;

import edu.ucan.ucanwallet.util.TipoMovimento;
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

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Movimento {
    
    private String descricao;
    private UUID conta;
    private double valor;
    private TipoMovimento tipo_movimento;
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ucan.ucanwallet.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.ucan.ucanwallet.util.EstadoTransacao;
import edu.ucan.ucanwallet.util.TipoMovimento;
import java.io.Serializable;
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
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class TransacaoCriptografada implements Serializable {

    private UUID conta;
    private byte[] conta_destino;
    private byte[] valor;
    private byte[] data_transacao;
    private byte[] tipo_movimento;
    private byte[] estado_transacao;
    private int pk_transacao;

    public String getGson() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        return gson.toJson(this);
    }

    public TransacaoCriptografada getObject(String jsString) {
        Gson gson = new Gson();
        TransacaoCriptografada transacao = gson.fromJson(jsString, TransacaoCriptografada.class);

        return transacao;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ucan.ucanwallet.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author amari
 */
public class DateUtil {
        // Formato padrão de data utilizado na classe
    private static final String FORMATO_DATA = "yyyy-MM-dd";

    // Método para converter java.util.Date para java.sql.Date
    public static java.sql.Date converterParaSQLDate(java.util.Date dataUtil) {
        return new java.sql.Date(dataUtil.getTime());
    }

    // Método para formatar uma data em formato de texto (String)
    public static String formatarData(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_DATA);
        return sdf.format(data);
    }

    // Método para fazer o parse de uma data a partir de uma String
    public static Date parseData(String dataStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_DATA);
        return sdf.parse(dataStr);
    }
}

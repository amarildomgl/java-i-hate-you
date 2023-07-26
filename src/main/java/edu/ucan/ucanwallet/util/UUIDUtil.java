/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ucan.ucanwallet.util;
import java.util.UUID;
/**
 *
 * @author amari
 */
public class UUIDUtil {
    public static boolean isValidUUID(String uuidString) {
        try {
            UUID.fromString(uuidString);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static String cleanUUIDString(String uuidString) {
        return uuidString.trim();
    }

    public static UUID parseUUID(String uuidString) {
        if (isValidUUID(uuidString)) {
            return UUID.fromString(uuidString);
        }
        return null; 
    }
}

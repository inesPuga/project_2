package com.example.database.BLL;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogicDataBase {

    public static String passEncrypt(String originalString) {
        String sha256hex = Hashing.sha256()
                .hashString(originalString, StandardCharsets.UTF_8)
                .toString();

        return sha256hex;
    }

    public static boolean verifyDate(String strDate) {
        if (strDate.trim().equals("")) {
            return true;
        }
        else {
            SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy");
            sdfrmt.setLenient(false);
            try {
                Date javaDate = sdfrmt.parse(strDate);
                //valid date format
            }
            catch (ParseException e) {
                //date format is invalid
                return false;
            }
            return true;
        }
    }

}
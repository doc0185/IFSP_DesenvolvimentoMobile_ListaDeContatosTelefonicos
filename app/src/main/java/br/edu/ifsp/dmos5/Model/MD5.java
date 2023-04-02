package br.edu.ifsp.dmos5.Model;

import java.security.*;
import java.math.*;

public class MD5 {

    public static String getHashMd5(String value) throws NoSuchAlgorithmException {
        MessageDigest md;

            md = MessageDigest.getInstance("MD5");

        BigInteger hash = new BigInteger(1, md.digest(value.getBytes()));
        return hash.toString(16);
    }
}

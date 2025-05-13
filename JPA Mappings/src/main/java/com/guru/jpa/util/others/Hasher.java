package com.guru.jpa.util.others;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Hasher
{
    public static String generateHash(StringBuilder input)
    {
        try
        {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = messageDigest.digest(input.toString().getBytes(StandardCharsets.UTF_8));

            return Base64.getUrlEncoder().encodeToString(hashBytes);
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }

}

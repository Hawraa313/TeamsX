/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project_cs;
import java.util.Scanner;

public class affineCipher {
    public static int gcd(int x, int y) {
        int z = x % y;
        if (z == 0)
            return y;
        else
            return gcd(y, z);
    }

    public static String encrypt(String plainText, int a, int b) {
 
       int length = plainText.length();
        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char ch = plainText.charAt(i);

            if (Character.isUpperCase(ch)) {
                int letter = (a * (ch - 'A') + b) % 26;
                char encryptedLetter = (char) (letter + 'A');
                cipherText.append(encryptedLetter);
            } else if (Character.isLowerCase(ch)) {
                int letter = (a * (ch - 'a') + b) % 26;
                char encryptedLetter = (char) (letter + 'a');
                cipherText.append(encryptedLetter);
            } else {
                cipherText.append(ch); // Keep non-alphabetic characters as is
            }
        }

        return cipherText.toString();
    }

    public static String decrypt(String cipherText, int a, int b) {
      
       int length = cipherText.length();
        StringBuilder plainText = new StringBuilder();
        int m = 26;

        int Inv = 0;
        for (int i = 0; i < m; i++) {
            if ((a * i) % m == 1) {
                Inv = i;
                break;
            }
        }

        for (int i = 0; i < length; i++) {
            char ch = cipherText.charAt(i);

            if (Character.isUpperCase(ch)) {
                int letter = (Inv * (ch - 'A' - b + m)) % m;
                if (letter < 0)
                    letter += m;
                char decryptedText = (char) (letter + 'A');
                plainText.append(decryptedText);
            } else if (Character.isLowerCase(ch)) {
                int letter = (Inv * (ch - 'a' - b + m)) % m;
                if (letter < 0)
                    letter += m;
                char decryptedText = (char) (letter + 'a');
                plainText.append(decryptedText);
            } else {
                plainText.append(ch); 
            }
        }

        return plainText.toString();
       
    }

    public static void main(String[] args) {
        Affine affine = new Affine();
        affine.setVisible(true);
        affine.setDefaultCloseOperation(0);
        affine.pack();
      
    }
}

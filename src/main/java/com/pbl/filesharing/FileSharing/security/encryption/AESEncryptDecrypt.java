package com.pbl.filesharing.FileSharing.security.encryption;

import com.pbl.filesharing.FileSharing.entity.Document;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.SerializationUtils;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.KeySpec;

/**
 * @author Beatrice V.
 * @created 10.12.2020 - 11:48
 * @project FileSharing
 */
public class AESEncryptDecrypt {
    // file to be encrypted
    public static FileInputStream inFile;

    // encrypted file
    public static FileOutputStream outFile;

    // password to encrypt the file
    public static String password = "filesharing";

    // salt for encoding writing it to a file
    public static byte[] salt = new byte[8];

    public static void encrypt(Document doc) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidParameterSpecException, BadPaddingException, IllegalBlockSizeException{
        byte[] data = SerializationUtils.serialize((Serializable) doc);
        inFile.read(data);
        // password, iv and salt should be transferred to the other end in a secure manner
        // salt should be transferred to the recipient securely for decryption
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(salt);
        FileOutputStream saltOutFile = new FileOutputStream("salt.enc");
        saltOutFile.write(salt);
        saltOutFile.close();

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
        SecretKey secretKey = factory.generateSecret(keySpec);
        SecretKey secret = new SecretKeySpec(secretKey.getEncoded(), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secret);
        AlgorithmParameters params = cipher.getParameters();

        // iv adds randomness to the text and just makes the mechanism more secure
        // used while initializing the cipher file to store the iv
        FileOutputStream ivOutFile = new FileOutputStream("iv.enc");
        byte[] iv = params.getParameterSpec(IvParameterSpec.class).getIV();
        ivOutFile.write(iv);
        ivOutFile.close();

        fileToBytes(inFile, outFile, cipher);

        System.out.println("File Encrypted.");
    }

    public void decrypt(FileInputStream inFile, FileOutputStream outFile) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
        // reading the salt user should have secure mechanism to transfer the
        // salt, iv and password to the recipient
        FileInputStream saltInFile = new FileInputStream("salt.enc");
        saltInFile.read(salt);
        saltInFile.close();

        // reading the iv
        FileInputStream ivFis = new FileInputStream("iv.enc");
        byte[] iv = new byte[16];
        ivFis.read(iv);
        ivFis.close();

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
        SecretKey tmp = factory.generateSecret(keySpec);
        SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

        // file decryption
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));

        //FileOutputStream fileOut = new FileOutputStream("B:\\PROG\\PROJECTS\\FileSharing\\FileSharing\\src\\main\\resources\\stuff\\docdec.txt");

        fileToBytes(inFile, outFile, cipher);

        System.out.println("File Decrypted.");
    }

    public static void fileToBytes(FileInputStream inFile, FileOutputStream outFile, Cipher cipher) throws IOException, BadPaddingException, IllegalBlockSizeException {
        byte[] input = new byte[64];
        int bytesRead;

        while ((bytesRead = inFile.read(input)) != -1) {
            byte[] output = cipher.update(input, 0, bytesRead);
            if (output != null)
                outFile.write(output);
        }

        byte[] output = cipher.doFinal();
        if (output != null)
            outFile.write(output);

        inFile.close();
        outFile.flush();
        outFile.close();
    }

    public String getExtensionByApacheCommonLib(String filename) {
        return FilenameUtils.getExtension(filename);
    }
}

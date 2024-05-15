package let_us_book;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;



public class Encrypter {
	private static byte[] KEY;

    // Method to read key from file
    private static void readKeyFromFile(String filePath) throws Exception {
        KEY = Files.readAllBytes(Paths.get(filePath));
    }

    // Method to encrypt a string
    public static String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(KEY, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    // Method to decrypt a string
    public static String decrypt(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(KEY, "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData);
    }

    public Encrypter() {
    	try {
    	    // Read the encryption key from a file
    	    readKeyFromFile("C:/Temp/key.txt");
    	} catch (Exception e) {
    	    e.printStackTrace();
    	}
    }
}

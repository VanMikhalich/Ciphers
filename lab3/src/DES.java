import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.util.Base64;
import java.util.Scanner;

public class DES {

    // Генерируем ключ
    private static SecretKey getKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(56); // задаем размер ключа в битах
        return keyGenerator.generateKey(); //
    }

    // Шифруем данные
    public static byte[] encrypt(byte[] input, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES"); // создаем объект для шифрования
        cipher.init(Cipher.ENCRYPT_MODE, key); // инициализируем объект шифрования с использованием ключа
        return cipher.doFinal(input); // возвращаем зашифрованный массив байтов
    }

    // Дешифруем данные
    public static byte[] decrypt(byte[] input, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES"); // создаем объект для дешифрования
        cipher.init(Cipher.DECRYPT_MODE, key); // инициализируем объект дешифрования с использованием ключа
        return cipher.doFinal(input); // возвращаем дешифрованный массив байтов
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("D:\\Users\\user\\Desktop/file.txt"));
        String message = sc.nextLine();
        SecretKey key = getKey(); // генерируем секретный ключ
        byte[] encryptedBytes = encrypt(message.getBytes(), key);
        System.out.println("Encrypted message: " + Base64.getEncoder().encodeToString(encryptedBytes)); // выводим зашифрованное сообщение в ASCII
        byte[] decryptedBytes = decrypt(encryptedBytes, key);
        String decryptedMessage = new String(decryptedBytes); // преобразуем дешифрованные байты в строку
        System.out.println("Decrypted message: " + decryptedMessage); // выводим дешифрованное сообщение
    }
}
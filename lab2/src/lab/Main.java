package lab;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
public class Main {

    private static String encrypt(String message, String key) { // алгоритм шифрования сообщения
        char[] messageChars = message.toCharArray();
        char[] keyChars = key.toCharArray();

        StringBuilder encryptedBuilder = new StringBuilder();
        for(int i = 0; i < messageChars.length; i++) {
            char encryptedChar = (char) (messageChars[i] ^ keyChars[i % keyChars.length]); // Операция XOR
            encryptedBuilder.append(encryptedChar);
        }
        return encryptedBuilder.toString();
    }

    private static String decrypt(String encryptedMessage, String key) { // алгоритм расшифровываания сообщения
        char[] encryptedChars = encryptedMessage.toCharArray();
        char[] keyChars = key.toCharArray();

        StringBuilder decryptedBuilder = new StringBuilder();
        for(int i = 0; i < encryptedChars.length; i++) {
            char decryptedChar = (char)(encryptedChars[i] ^ keyChars[i % keyChars.length]); // Операция XOR
            decryptedBuilder.append(decryptedChar);
        }
        return decryptedBuilder.toString();
    }

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(new File("D:\\Users\\user\\Desktop/file.txt"))){

            String message = sc.nextLine();

            char[] charsKey = new char[message.length()];
            for (int i = 0; i < message.length(); i++) {  //Генерация ключа с границами русских символов
                charsKey[i] = (char) (int) (Math.random() * 69 + 1040);
            }
            String key = Arrays.toString(charsKey);

            String encryptedMessage = encrypt(message, key);
            System.out.println("Зашифрованное сообщение: " + encryptedMessage);

            String decryptedMessage = decrypt(encryptedMessage, key);
            System.out.println("Расшифрованное сообщение: " + decryptedMessage);

        } catch (Exception e) {
            System.out.println("Ошибка");
        }
    }
}
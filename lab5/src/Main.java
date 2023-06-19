import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {
    public static String md5(String input) throws NoSuchAlgorithmException {
        byte[] inputData = input.getBytes();

        // Создание экземпляра объекта MessageDigest с использованием алгоритма MD5
        MessageDigest md = MessageDigest.getInstance("MD5");

        // Вычисление хэш-значения для входных данных
        byte[] hashBytes = md.digest(inputData);

        // Преобразование хэш-значения в шестнадцатеричную строку
        BigInteger number = new BigInteger(1, hashBytes);

        return number.toString(16);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException {
        Scanner sc = new Scanner(new File("C:\\Users\\ivans\\Desktop\\file.txt"));
        String input = sc.nextLine();
        String hash = md5(input);
        System.out.println("Input: " + input);
        System.out.println("Hash: " + hash);
    }
}


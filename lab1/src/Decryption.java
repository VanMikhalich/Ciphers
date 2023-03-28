import java.util.Scanner;

public class Decryption {
    public static void decryption(String[][] square) { // расшифровывание
        StringBuilder code = new StringBuilder(); // строка, в которую будем вносить расшифровывание

        Scanner sc1 = new Scanner(System.in); // получаем строку от пользователя
        System.out.print("Введите шифр заглавными буквами: ");
        String str = sc1.nextLine();

        for (int i = 0; i < str.length(); i++) { // считываем каждый элемент введённой строки
            String el = String.valueOf(str.charAt(i));
            if (el.equals("А")) {
                code.append("Ю");
            }
            for (int j = 0; j < square.length; j++) {
                for (int k = 0; k < square[0].length; k++) { // проход по всему массиву

                    // если элемент находится в первой строке, то берём элемент из последней строки
                    if (el.equals(square[j][k]) && j == 0) {
                        code.append(square[square.length - 1][k]);
                        break;

                    // берём элемент предыдущей строки того же столбца
                    } else if (el.equals(square[j][k])) {
                        code.append(square[j-1][k]);
                        break;
                    }
                }
            }
        }

        // если кол-во символов в строке с шифром отличается от кол-ва символов во введённой строке,
        // значит ввод был сделан с ошибкой или не по правилу
        if (code.length() != str.length()) {
            System.out.println("Некорректный ввод");
        }

        System.out.println(code); //вывод расшифрованного сообщения
    }
}

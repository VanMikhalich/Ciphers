import java.util.Scanner;

public class Encryption {

    public static void encryption (String[][] square) { // шифрование
        StringBuilder code = new StringBuilder(); // строка, в которую будем вносить шифр

        Scanner sc1 = new Scanner(System.in); // получаем строку от пользователя
        System.out.print("Введите строку заглавными русскими буквами: ");
        String str = sc1.nextLine();

        for (int i = 0; i < str.length(); i++) { // считываем каждый элемент введённой строки
            String el = String.valueOf(str.charAt(i));
            if (el.equals("А")) {
                code.append("Ё");
            }
            for (int j = 0; j < square.length; j++) {
                for (int k = 0; k < square[0].length; k++) { // проход по всему массиву

                    // если элемент находится в последней строке, то берём элемент из первой строки
                    if (el.equals(square[j][k]) && j == square.length - 1) {
                        code.append(square[0][k]);
                        break;

                    // берём элемент следующей строки того же столбца
                    } else if (el.equals(square[j][k])) {//
                        code.append(square[j+1][k]);
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

        System.out.println(code); // вывод зашифрованного сообщения
    }
}

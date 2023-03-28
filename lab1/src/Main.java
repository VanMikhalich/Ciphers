public class Main {
    public static void main(String[] args) {

        String[][] square = {
                {"A", "Б", "В", "Г", "Д", "Е"},
                {"Ё", "Ж", "З", "И", "Й", "К"},
                {"Л", "М", "Н", "О", "П", "Р"},
                {"С", "Т", "У", "Ф", "Х", "Ц"},
                {"Ч", "Ш", "Щ", "Ъ", "Ы", "Ь"},
                {"Э", "Ю", "Я", ".", ",", " "}
        };

        Encryption.encryption(square);
        Decryption.decryption(square);

    }
}

public class Main {
    public static void main(String[] args) {
        DES.bin();//переводит текст в двоичную систему
        DES.blockBin();//распределяет текст по блокам по 64 бита и принимает ключ емкостью 64 бита
        DES.PC();//из 1 ключа 64 бита создает 16 ключей по 48 бит
        DES.encrypt();//шифрование des
        DES.decrypt();//расшифровывание des
    }
}
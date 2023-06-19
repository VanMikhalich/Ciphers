import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DES {
    static int[] IP = {58, 50, 42, 34, 26, 18, 10, 2,
            60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6,
            64, 56, 48, 40, 32, 24, 16, 8,
            57, 49, 41, 33, 25, 17, 9, 1,
            59, 51, 43, 35, 27, 19, 11, 3,
            61, 53, 45, 37, 29, 21, 13, 5,
            63, 55, 47, 39, 31, 23, 15, 7};
    static int[] IP_1 = {40, 8, 48, 16, 56, 24, 64, 32,
            39, 7, 47, 15, 55, 23, 63, 31,
            38, 6, 46, 14, 54, 22, 62, 30,
            37, 5, 45, 13, 53, 21, 61, 29,
            36, 4, 44, 12, 52, 20, 60, 28,
            35, 3, 43, 11, 51, 19, 59, 27,
            34, 2, 42, 10, 50, 18, 58, 26,
            33, 1, 41, 9, 49, 17, 57, 25};
    static int[] EXPANSION = {32, 1, 2, 3, 4, 5,
            4, 5, 6, 7, 8, 9,
            8, 9, 10, 11, 12, 13,
            12, 13, 14, 15, 16, 17,
            16, 17, 18, 19, 20, 21,
            20, 21, 22, 23, 24, 25,
            24, 25, 26, 27, 28, 29,
            28, 29, 30, 31, 32, 1};
    static int[] P = {16, 7, 20, 21, 29, 12, 28, 17,
            1, 15, 23, 26, 5, 18, 31, 10,
            2, 8, 24, 14, 32, 27, 3, 9,
            19, 13, 30, 6, 22, 11, 4, 25};
    static final int[][][] S_Box = {
            {
                    {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
                    {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
                    {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
                    {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}},
            {
                    {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
                    {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
                    {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
                    {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}},
            {
                    {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
                    {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
                    {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
                    {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}},
            {
                    {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
                    {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
                    {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
                    {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}},
            {
                    {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
                    {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
                    {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
                    {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}},
            {
                    {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
                    {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
                    {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
                    {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}},
            {
                    {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
                    {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
                    {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
                    {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}},
            {
                    {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
                    {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
                    {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
                    {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}}
    };
    static int[] PC1 = {57, 49, 41, 33, 25, 17, 9,
            1, 58, 50, 42, 34, 26, 18,
            10, 2, 59, 51, 43, 35, 27,
            19, 11, 3, 60, 52, 44, 36,
            63, 55, 47, 39, 31, 23, 15,
            7, 62, 54, 46, 38, 30, 22,
            14, 6, 61, 53, 45, 37, 29,
            21, 13, 5, 28, 20, 12, 4};
    static int[] PC2 = {14, 17, 11, 24, 1, 5, 3, 28,
            15, 6, 21, 10, 23, 19, 12, 4,
            26, 8, 16, 7, 27, 20, 13, 2,
            41, 52, 31, 37, 47, 55, 30, 40,
            51, 45, 33, 48, 44, 49, 39, 56,
            34, 53, 46, 42, 50, 36, 29, 32};
    private static final int[] LFT = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};
    static Scanner text = new Scanner(System.in);
    static StringBuilder binary = new StringBuilder();
    static StringBuilder binarykey = new StringBuilder();
    static List<String> textb1 = new ArrayList<>();
    static List<String> textb2 = new ArrayList<>();
    static List<String> IP1 = new ArrayList<>();
    static List<String> IPobr = new ArrayList<>();
    static List<String> IP1obr = new ArrayList<>();
    static List<String> xor1 = new ArrayList<>();
    static List<String> xor2 = new ArrayList<>();
    static String xorq = "1011000110100001000100000100100010101000100001010100100101000101";

    static String key;
    static List<String> keys = new ArrayList<>();

    protected static void bin(){
        String g = text.nextLine();
        byte[] textb = g.getBytes();
        for (int b : textb) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
    }
    protected static void blockBin(){
        List<String> ras= new ArrayList<>();
        for (int i=0;i<binary.length();i++){
            ras.add(String.valueOf(binary.charAt(i)));
        }
        StringBuilder a = new StringBuilder();
        for (int i=0; i<ras.size();i++){
            a.append(ras.get(i));
            while (a.length()==64){
                textb1.add(String.valueOf(a));
                a.setLength(0);
            }
            if (i==ras.size()-1 && a.length()!=0){
                int o = a.length();
                for (int k=0;k<64-o;k++){
                    a.append("0");
                }
                textb1.add(String.valueOf(a));
                a.setLength(0);
            }
        }
        String g = text.nextLine();
        byte[] textb= g.getBytes();
        for (int b : textb) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binarykey.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        key = String.valueOf(binarykey);

    }
    protected static void PC(){
        String key1 = "";

        for(int i : PC1){
            key1 += key.charAt(i-1);
        }
        String C0="";
        String D0="";
        for (int j=0;j<key1.length();j++){
            if (j<28){
                C0+=String.valueOf(key1.charAt(j));
            }
            else {
                D0+=key1.charAt(j);
            }
        }
        // создаём 16 ключей
        for (int k =0;k<16;k++){
            C0=ShiftKey(C0,LFT[k]);
            D0=ShiftKey(D0,LFT[k]);
            String key2=C0+D0;
            key1="";
            for(int i : PC2){
                key1+=key2.charAt(i-1);
            }
            keys.add(key1);
        }
    }
    private static String ShiftKey(String key, Integer lft){
        return key.substring(lft-1)+key.substring(0,lft);
    }
    private static String feistel(String R, String key){
        String f1="";
        //перестановка
        for(int i : EXPANSION){
            f1+=R.charAt(i-1);
        }
        //xor
        String xor="";
        for (int i=0;i<f1.length();i++){
            xor+=String.valueOf(Integer.valueOf(String.valueOf(f1.charAt(i)),2)^Integer.valueOf(String.valueOf(key.charAt(i)),2));
        }
        String P1="";
        int p=0;
        // разбиваем на блоки по 6 бит
        for (int i=6;i<=xor.length();i+=6) {
            for (int k=0;k<S_Box.length;k++) {
                String s = xor.substring(i - 6, i);
                String first = (s.charAt(0)) + String.valueOf(s.charAt(5));
                String last = s.substring(1, 5);
                int first1 = Integer.parseInt(first, 2);
                int last1 = Integer.parseInt(last, 2);
                if (k == p) {
                    p++;
                    //берем значение трехмерного массива
                    String hash= Integer.toBinaryString(S_Box[k][first1][last1]);
                    if (hash.length()<4){
                        while (hash.length()<4){
                            hash="0"+hash;
                        }
                    }
                    P1+=hash;
                    break;
                }
            }
        }
        String do48="";
        //перестановка
        for (int i:P){
            do48+=P1.charAt(i-1);
        }
        return do48;
    }
    protected static void encrypt(){
        for (int encrO = 0; encrO <textb1.size(); encrO++) {
            if (encrO ==0) {
                String xor="";
                for (int k = 0; k < textb1.get(encrO).length(); k++) {
                    xor += (String.valueOf(Integer.valueOf(String.valueOf(textb1.get(encrO).charAt(k)), 2) ^ Integer.parseInt(String.valueOf(xorq.charAt(k)))));
                }
                xor1.add(xor);
            }
            if (encrO >0) {
                String xor="";
                for (int k = 0; k < textb1.get(encrO).length(); k++) {
                    xor += (String.valueOf(Integer.valueOf(String.valueOf(textb1.get(encrO).charAt(k)), 2) ^ Integer.parseInt(String.valueOf(textb2.get(encrO -1).charAt(k)))));
                }
                xor1.add(xor);
            }
            String IP3="";
            for(int i : IP){
                IP3 += xor1.get(encrO).charAt(i - 1);
            }
            IP1.add(IP3);
            String[] T = new String[17];
            String[] L = new String[17];
            String[] R = new String[17];
            String L0 = "";
            String R0 = "";
            for (int i = 0; i < 64; i++) {
                if (i < 32) {
                    L0 += IP1.get(encrO).charAt(i);
                } else {
                    R0 += IP1.get(encrO).charAt(i);
                }
            }
            L[0]=L0;
            R[0]=R0;


            for (int i = 1; i < 17; i++) {
                L[i]=(R[i - 1]);
                String feller = feistel(R[i - 1], keys.get(i - 1));
                String R1 = "";
                for (int k = 0; k < L[i - 1].length(); k++) {
                    R1 += (String.valueOf(Integer.valueOf(String.valueOf(L[i - 1].charAt(k)), 2) ^ Integer.valueOf(String.valueOf(feller.charAt(k)), 2)));
                }
                R[i]=R1;
            }
            T[1]=R[16]+L[16];
            String IP2 = "";
            for (int i : IP_1) {
                IP2 += T[1].charAt(i - 1);
            }
            textb2.add(IP2);

        }
        String f = "";
        for (String s : textb2) {
            for (int i = 8; i <= 64; i += 8) {
                String d = s.substring(i - 8, i);
                f += (char) Integer.parseInt(d, 2);
            }
        }
        System.out.println(f);
    }
    protected static void decrypt(){
        for (String value : textb2) {
            String IP2 = "";
            for (int i : IP) {
                IP2 += value.charAt(i - 1);
            }
            IPobr.add(IP2);
        }
        for (int o=0;o<IPobr.size();o++) {
            String[] T = new String[17];
            String[] L = new String[17];
            String[] R = new String[17];
            String L0 = "";
            String R0 = "";
            for (int i = 0; i < 64; i++) {
                if (i < 32) {
                    R0 += IPobr.get(o).charAt(i);
                } else {
                    L0 += IPobr.get(o).charAt(i);
                }
            }
            L[16]=L0;
            R[16]=R0;
            for (int i = 16; i>0; i--) {
                R[i-1]=L[i];
                String feller = feistel(R[i-1], keys.get(i-1));
                String R1 = "";
                for (int k = 0; k < L[i].length(); k++) {
                    R1 += (String.valueOf(Integer.valueOf(String.valueOf(R[i].charAt(k)), 2) ^ Integer.valueOf(String.valueOf(feller.charAt(k)), 2)));
                }
                L[i-1]=R1;
            }
            T[0]=L[0]+R[0];
            String IP2 = "";
            for (int i : IP_1) {
                IP2 += T[0].charAt(i - 1);
            }
            IP1obr.add(IP2);
            if (o==0) {
                String xor="";
                for (int k = 0; k < IP1obr.get(o).length(); k++) {
                    xor += (String.valueOf(Integer.valueOf(String.valueOf(IP1obr.get(o).charAt(k)), 2) ^ Integer.valueOf(String.valueOf(xorq.charAt(k)))));
                }
                xor2.add(xor);
            }
            if (o>0) {
                String xor="";
                for (int k = 0; k < IP1obr.get(o).length(); k++) {
                    xor += (String.valueOf(Integer.valueOf(String.valueOf(IP1obr.get(o).charAt(k)), 2) ^ Integer.valueOf(String.valueOf(textb2.get(o-1).charAt(k)))));
                }
                xor2.add(xor);
            }
        }
        String f = "";
        for (String s : xor2) {
            for (int b = 8; b <= 64; b += 8) {
                String g = s.substring(b - 8, b);
                char c = (char) Integer.parseInt(g, 2);
                f += c;
            }
        }
        System.out.println(f);
    }
}

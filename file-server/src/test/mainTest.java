package test;

/**
 * @author liubinhao
 * @date 2020/8/16
 */

public class mainTest {
    public static void main(String[] args) {
        byte []  a= {72, 111, 115, 116, 58, 32, 108, 111, 99, 97, 108, 104, 111, 115, 116};
        for (byte b : a) {
            System.out.print((char) b);
        }
    }
}

import java.math.BigInteger;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final var scanner = new Scanner(System.in);
        final var m = scanner.nextBigInteger();

        var number = BigInteger.ONE;
        var n = number;
        do {
            n = n.add(BigInteger.ONE);
            number = number.multiply(n);
        } while (number.min(m).equals(number) && !number.equals(m));
        System.out.println(n);
    }
}
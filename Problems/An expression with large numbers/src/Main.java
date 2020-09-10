import java.math.BigInteger;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final var scanner = new Scanner(System.in);
        final var result = scanner.nextBigInteger()
                .multiply(BigInteger.valueOf(-1))
                .multiply(scanner.nextBigInteger())
                .add(scanner.nextBigInteger())
                .subtract(scanner.nextBigInteger());

        System.out.println(result);
    }
}
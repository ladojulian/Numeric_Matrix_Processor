import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final var scanner = new Scanner(System.in);
        var a = scanner.nextDouble();
        var b = scanner.nextDouble();

        System.out.println(Math.pow(a, b));
    }
}
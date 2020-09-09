import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final var scanner = new Scanner(System.in);

        final var a = scanner.nextDouble();
        final var b = scanner.nextDouble();
        final var c = scanner.nextDouble();

        final var calc = Math.sqrt(Math.pow(b, 2) - 4 * a * c);
        final var root1 = (-b - calc) / (2 * a);
        final var root2 = (-b + calc) / (2 * a);

        if (root1 > root2) {
            System.out.println(root2 + " " + root1);
        } else {
            System.out.println(root1 + " " + root2);
        }
    }
}
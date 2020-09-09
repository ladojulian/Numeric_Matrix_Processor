import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final var scanner = new Scanner(System.in);

        final var vectorU = new int[]{scanner.nextInt(), scanner.nextInt()};
        final var vectorV = new int[]{scanner.nextInt(), scanner.nextInt()};

        var scalarProduct = vectorU[0] * vectorV[0] + vectorU[1] * vectorV[1];
        final var vectorULength = Math.sqrt(Math.pow(vectorU[0], 2) + Math.pow(vectorU[1], 2));
        final var vectorVLength = Math.sqrt(Math.pow(vectorV[0], 2) + Math.pow(vectorV[1], 2));


        System.out.println(Math.toDegrees(Math.acos(scalarProduct / (vectorULength * vectorVLength))));
    }
}
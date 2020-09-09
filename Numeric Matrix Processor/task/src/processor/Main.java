package processor;

import java.util.Scanner;

public class Main {

    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        var option = 0;
        do {
            printMenu();
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter size of first matrix: ");
                    var n = scanner.nextInt();
                    var m = scanner.nextInt();

                    System.out.println("Enter first matrix:");
                    var matrix = new double[n][m];
                    sumMatrix(matrix);

                    System.out.print("Enter size of second matrix: ");
                    if (n != scanner.nextInt() ||
                            m != scanner.nextInt()) {
                        System.out.println("ERROR");
                        return;
                    }

                    System.out.println("Enter second matrix:");
                    sumMatrix(matrix);

                    System.out.println("The sum result is:");
                    printMatrix(matrix);
                    break;
                case 2:
                    System.out.print("Enter size of matrix: ");
                    n = scanner.nextInt();
                    m = scanner.nextInt();

                    System.out.println("Enter matrix:");
                    matrix = new double[n][m];
                    sumMatrix(matrix);

                    System.out.print("Enter constant: ");
                    multiplyByScalarMatrix(matrix, scanner.nextInt());

                    System.out.println("The multiplication to a constant result is:");
                    printMatrix(matrix);
                    break;
                case 3:
                    System.out.print("Enter size of first matrix: ");
                    final var n1 = scanner.nextInt();
                    final var m1 = scanner.nextInt();

                    System.out.println("Enter first matrix:");
                    var firstMatrix = new double[n1][m1];
                    sumMatrix(firstMatrix);

                    System.out.print("Enter size of second matrix: ");
                    final var n2 = scanner.nextInt();
                    final var m2 = scanner.nextInt();
                    if (m1 != n2) {
                        System.out.println("ERROR");
                        return;
                    }

                    System.out.println("Enter second matrix:");
                    var secondMatrix = new double[n2][m2];
                    sumMatrix(secondMatrix);

                    System.out.println("The multiplication result is:");
                    var resultMatrix = multiplyMatrices(firstMatrix, secondMatrix, n1, m2, m1);

                    printMatrix(resultMatrix);
                    break;
                case 4:
                    transposeMatrix();
                    break;
                case 5:
                    System.out.print("Enter matrix size: ");
                    n = scanner.nextInt();
                    m = scanner.nextInt();

                    System.out.println("Enter matrix:");
                    matrix = new double[n][m];
                    sumMatrix(matrix);
                    var determinant = calculateDeterminant(matrix);

                    System.out.println("The result is:\n" + determinant);
                    break;
                case 6:
                    System.out.print("Enter matrix size: ");
                    n = scanner.nextInt();
                    m = scanner.nextInt();

                    System.out.println("Enter matrix:");
                    matrix = new double[n][m];
                    sumMatrix(matrix);

                    determinant = calculateDeterminant(matrix);

                    if (determinant == 0) {
                        System.out.println("ERROR");
                        return;
                    }

                    matrix = inverseMatrix(matrix, determinant);
                    printMatrix(matrix);
                    break;
                default:
                    return;
            }
        } while (true);
    }

    private static void printMenu() {
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix to a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("4. Transpose matrix");
        System.out.println("5. Calculate a determinant");
        System.out.println("6. Inverse matrix");
        System.out.println("0. Exit");
        System.out.print("Your choice: ");
    }

    private static void transposeMatrix() {
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
        System.out.print("Your choice: ");

        var option = scanner.nextInt();

        System.out.print("Enter matrix size: ");
        var n = scanner.nextInt();
        var m = scanner.nextInt();

        System.out.println("Enter matrix:");
        var matrix = new double[n][m];
        sumMatrix(matrix);

        System.out.println("The result is:");

        switch (option) {
            case 1:
                matrix = transposeMainDiagonal(matrix);
                printMatrix(matrix);
                break;
            case 2:
                transposeSideDiagonal(matrix);
                break;
            case 3:
                transposeVerticalLine(matrix);
                break;
            case 4:
                transposeHorizontalLine(matrix);
                break;
            default:
                break;
        }
    }

    private static double calculateDeterminant(double[][] matrix) {
        double[][] temporary;
        double result = 0;

        if (matrix.length == 1) {
            return matrix[0][0];
        }

        if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        for (int i = 0; i < matrix[0].length; i++) {
            temporary = new double[matrix.length - 1][matrix[0].length - 1];

            for (int j = 1; j < matrix.length; j++) {
                for (int k = 0; k < matrix[0].length; k++) {
                    if (k < i) {
                        temporary[j - 1][k] = matrix[j][k];
                    } else if (k > i) {
                        temporary[j - 1][k - 1] = matrix[j][k];
                    }
                }
            }

            result += matrix[0][i] * Math.pow(-1, i) * calculateDeterminant(temporary);
        }
        return result;
    }

    private static double[][] inverseMatrix(double[][] matrix, double determinant) {
        var adjoinMatrix = adjoinMatrix(matrix);
        multiplyByScalarMatrix(adjoinMatrix, 1.0 / determinant);
        return adjoinMatrix;
    }

    private static double[][] adjoinMatrix(double[][] matrix) {
        double[][] adjoin = new double[matrix.length][matrix.length];
        for (int j = 0; j < matrix.length; j++) {
            for (int i = 0; i < matrix[0].length; i++) {
                adjoin[j][i] = calculateCofactor(matrix, i, j);
            }
        }
        return transposeMainDiagonal(adjoin);
    }

    public static double calculateCofactor(double[][] matrix, int i, int j) {
        var minorSize = matrix.length - 1;
        var minor = new double[minorSize][minorSize];

        int skipRow;
        int skipColumn;
        for (int jMinor = 0; jMinor < minorSize; jMinor++) {
            if (jMinor < j) {
                skipRow = 0;
            } else {
                skipRow = 1;
            }
            for (int iMinor = 0; iMinor < minorSize; iMinor++) {
                if (iMinor < i) {
                    skipColumn = 0;
                } else {
                    skipColumn = 1;
                }
                minor[jMinor][iMinor] = matrix[jMinor + skipRow][iMinor + skipColumn];
            }
        }
        return Math.pow(-1, 2 + j + i) * calculateDeterminant(minor);
    }

    private static void transposeHorizontalLine(double[][] matrix) {
        for (var i = 0; i < matrix.length / 2; i++) {
            for (var j = 0; j < matrix.length; j++) {
                double temp = matrix[matrix.length - 1 - i][j];
                matrix[matrix.length - 1 - i][j] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
        printMatrix(matrix);
    }

    private static void transposeVerticalLine(double[][] matrix) {
        for (var i = 0; i < matrix.length; i++) {
            for (var j = 0; j < matrix.length / 2; j++) {
                double temp = matrix[i][matrix.length - 1 - j];
                matrix[i][matrix.length - 1 - j] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
        printMatrix(matrix);
    }

    private static void transposeSideDiagonal(double[][] matrix) {
        for (var i = 0; i < matrix.length; i++) {
            for (var j = 0; j < matrix.length - i; j++) {
                if (i + j != matrix.length - 1) {
                    double temp = matrix[matrix.length - 1 - j][matrix.length - 1 - i];
                    matrix[matrix.length - 1 - j][matrix.length - 1 - i] = matrix[i][j];
                    matrix[i][j] = temp;
                }
            }
        }
        printMatrix(matrix);
    }

    private static double[][] transposeMainDiagonal(double[][] matrix) {
        var tempMatrix = matrix.clone();
        for (var i = 0; i < tempMatrix.length; i++) {
            for (var j = 0; j < i; j++) {
                double temp = tempMatrix[j][i];
                tempMatrix[j][i] = tempMatrix[i][j];
                tempMatrix[i][j] = temp;

            }
        }
        return tempMatrix;
    }

    private static double[][] multiplyMatrices(double[][] firstMatrix, double[][] secondMatrix, int n, int m, int o) {
        var resultMatrix = new double[n][m];

        for (var i = 0; i < n; i++) {
            for (var j = 0; j < m; j++) {
                for (var k = 0; k < o; k++) {
                    resultMatrix[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
                }
            }
        }
        return resultMatrix;
    }

    private static void multiplyByScalarMatrix(double[][] matrix, double s) {
        for (var i = 0; i < matrix.length; i++) {
            for (var j = 0; j < matrix[i].length; j++) {
                matrix[i][j] *= s;
            }
        }
    }

    private static void sumMatrix(double[][] matrix) {
        for (var i = 0; i < matrix.length; i++) {
            for (var j = 0; j < matrix[i].length; j++) {
                matrix[i][j] += scanner.nextDouble();
            }
        }
    }

    private static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double element : row) {
                System.out.printf("%.2f ", element);
            }
            System.out.println();
        }
    }
}

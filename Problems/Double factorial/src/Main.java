import java.math.BigInteger;

class DoubleFactorial {
    public static BigInteger calcDoubleFactorial(int n) {
        var number = n;
        var result = BigInteger.ONE;
        while (number > 1) {
            result = result.multiply(BigInteger.valueOf(number));
            number -= 2;
        }
        return result;
    }
}
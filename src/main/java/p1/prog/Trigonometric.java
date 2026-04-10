package p1.prog;

public class Trigonometric {
    private static final int col_shagov = 80;

     // делаем [-pi, pi] для сходимости

    public static double normalize(double x) {
        x %= Math.PI;
        if (x > Math.PI / 2) x -= Math.PI;
        if (x < -Math.PI / 2) x += Math.PI;
        return x;
    }
/*
     // sin(x) через степенной тейлора

    public static double sin(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x)) return Double.NaN;

        x = normalize(x);

        double term = x;
        double sum = x;

        for (int k = 1; k < col_shagov; k++) {
            term *= -1 * x * x / ((2 * k) * (2 * k + 1));
            double old = sum;
            sum += term;

            if (Math.abs(sum - old) < Double.MIN_VALUE) break;
        }

        return sum;
    }

    // cos(x) через степенной тейлора
    public static double cos(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x)) return Double.NaN;

        x = normalize(x);

        double term = 1.0;
        double sum = 1.0;

        for (int k = 1; k < col_shagov; k++) {
            term *= -1 * x * x / ((2 * k - 1) * (2 * k));
            double old = sum;
            sum += term;

            if (Math.abs(sum - old) < Double.MIN_VALUE) break;
        }

        return sum;
    }

    // tg(x)
    public static double tg(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x)) return Double.NaN;

        double cosX = cos(x);
        if (Math.abs(cosX) < 1e-15) return Double.NaN; // тангенс не определен

        return sin(x) / cosX;
    }*/

    // cos(x) через степенной тейлора
    public static double cos(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x)) return Double.NaN;

        x = normalize(x);

        double term = 1.0;
        double sum = 1.0;

        for (int k = 1; k < col_shagov; k++) {
            term *= -1 * x * x / ((2 * k - 1) * (2 * k));
            double old = sum;
            sum += term;

            if (Math.abs(sum - old) < Double.MIN_VALUE) break;
        }

        return sum;
    }



    private static double factorial(int n) {
        double f = 1.0;
        for (int i = 2; i <= n; i++) f *= i;
        return f;
    }

    // Бином C(n, k)
    private static double binomial(int n, int k) {
        double r = 1.0;
        for (int i = 1; i <= k; i++) {
            r = r * (n - (k - i)) / i;
        }
        return r;
    }

    // Бернулли
    public static double[] bernoulli(int max) {
        double[] B = new double[max + 1];
        B[0] = 1.0;

        for (int n = 1; n <= max; n++) {
            double sum = 0.0;

            for (int k = 0; k < n; k++) {
                sum += binomial(n + 1, k) * B[k];
            }

            B[n] = -sum / (n + 1);
        }

        return B;
    }

    // коэффициент ряда
    private static double tanCoeff(int n, double[] B) {
        int k = 2 * n;

        double numerator =
                Math.pow(-1, n - 1) *
                        Math.pow(2, k) *
                        (Math.pow(2, k) - 1) *
                        B[k];

        double denominator = factorial(k);

        return numerator / denominator;
    }

    // tg(x)
    public static double tg(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x)) return Double.NaN;

        x = normalize(x);
        double cosX = cos(x);
        if (Math.abs(cosX) < 1e-12) return Double.NaN;

        if (Math.abs(x) > 0.8) return Double.NaN;


        int N = col_shagov;
        double[] B = bernoulli(2 * N);

        double sum = 0.0;
        double xpow = x;
        double x2 = x * x;

        for (int n = 1; n <= N; n++) {
            double c = tanCoeff(n, B);
            sum += c * xpow;
            xpow *= x2;
        }

        return sum;
    }


}

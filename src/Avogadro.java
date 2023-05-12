import java.util.Scanner;

public class Avogadro {

    public static void main(String[] args) {
        int n = 0;
        double D = 0;
        double r;

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            r = Double.parseDouble(scanner.next()) * (0.175 * 10e-6);
            D += r * r;
            n++;
        }

        D /= (2 * n);

        // k = 6 * D * π * η * ρ / T
        double k = 6 * D * Math.PI * (9.135 * 10e-8) * (0.5 * 10e-6) / 297;
        // NA = R / K
        double NA = 8.31446 / k;

        System.out.println("Boltzmann = " + String.format("%.4e", k));
        System.out.println("Avogadro = " + String.format("%.4e", NA));
    }

}
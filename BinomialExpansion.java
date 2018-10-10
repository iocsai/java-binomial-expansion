package binomialexpansion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * https://www.sololearn.com/Discuss/975880/challenge-binomial-expansion
 * 
 * Now, let's code the following little programming challenge: 
 * Your task is to compute and print the expansion of the binomial (X+Y)^N. 
 * Here X and Y are variables of the binomial and N is the power of the binomial
 * and you have to receive N from the user. 
 * Example: For given N=2, the output must be X^2+2XY+Y^2 
 * (here "^" is short for "to the power of"). 
 * For N>2, you can see the required output in my following code. 
 * Please google "binomial expansion", for the general formula.
 */
public class BinomialExpansion {
    
    private final int exponential;
    private final List<List<Integer>> coefficients;

    public static void main(String[] args) {
        BinomialExpansion be = new BinomialExpansion(input());
        be.makePascalTriangle(be.getExponential());
        System.out.println(be);
    }
    
    private BinomialExpansion(int number) {
        this.exponential = number;
        List<Integer> firstRow = new ArrayList<Integer>() {{add(1);}};
        List<Integer> secRow = new ArrayList<Integer>() {{add(1);add(1);}};
        this.coefficients = new ArrayList<List<Integer>>() {
            {
                add(firstRow);
                add(secRow);
            }
        };
    }

    public int getExponential() {return exponential;}

    public List<List<Integer>> getCoefficients() {return coefficients;}
    
    private static int input() {
        Scanner sc = new Scanner(System.in);
        int number = 0;
        while (number < 1) {
            System.out.print("Enter a positive whole number: ");
            try {
                String nextLine = sc.nextLine();
                number = Integer.parseInt(nextLine);
            } catch(NumberFormatException e) {
                System.out.println("You have to enter a positive whole number!");
            }
        }
        return number;
    }

    private List<Integer> makePascalTriangle(int rows) {
        List<Integer> lastRow = new ArrayList<>();
        while (--rows > 0) {
            lastRow = this.getCoefficients().get(
                    this.getCoefficients().size() - 1);
            List<Integer> nextRow = new ArrayList<>();
            nextRow.add(1);
            for (int i = 0; i < lastRow.size() - 1; i++) {
                nextRow.add(lastRow.get(i) + lastRow.get(i + 1));
            }
            nextRow.add(1);
            this.coefficients.add(nextRow);
        }
        return lastRow;
    }
    
    public static String superscript(int number) {
        String result = "";
        String numStr = String.valueOf(number);
        for (int i = 0; i < numStr.length(); i++) {
            switch (numStr.charAt(i)) {
                case '1':
                    result += "¹";
                    break;
                case '2':
                    result += "²";
                    break;
                case '3':
                    result += "³";
                    break;
                case '4':
                    result += "⁴";
                    break;
                case '5':
                    result += "⁵";
                    break;
                case '6':
                    result += "⁶";
                    break;
                case '7':
                    result += "⁷";
                    break;
                case '8':
                    result += "⁸";
                    break;
                case '9':
                    result += "⁹";
                    break;
                case '0':
                    result += "⁰";
                    break;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        int expX = this.getExponential();
        String result = "(x + y)" + superscript(this.getExponential()) + 
                " = x" + superscript(expX--) + " + ";
        List<Integer> lastRow = this.getCoefficients().get(
                    this.getCoefficients().size() - 1);
        for (int i = 1; i < lastRow.size() - 1; i++) {
            int expY = this.getExponential() - expX;
            result += lastRow.get(i) + "x";
            result += expX > 1 ? superscript(expX--) : "";
            result += expY > 1 ? "y" + superscript(expY) + " + " : "y + ";
        }
        return result + "y" + superscript(this.getExponential());
    }
}
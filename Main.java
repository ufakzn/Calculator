import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println(calc(scanner.nextLine()));
    }

    public static String calc(String input) throws Exception {
        String[] operators = input.split(" ");
        boolean isRoman = validate(operators[0], operators[2]);
        int operator1 = isRoman ? romanToArabic(operators[0]) : Integer.parseInt(operators[0]);
        int operator2 = isRoman ? romanToArabic(operators[2]) : Integer.parseInt(operators[2]);
        if (operator1 > 10 || operator2 > 10) {
            throw new Exception();
        }
        if (operators[1].equals("+")) {
            return isRoman ? arabicToRoman(operator1 + operator2) : String.valueOf(operator1 + operator2);

        } else if (operators[1].equals("-")) {
            return isRoman ? arabicToRoman(operator1 - operator2) : String.valueOf(operator1 - operator2);

        } else if (operators[1].equals("*")) {
            return isRoman ? arabicToRoman(operator1 * operator2) : String.valueOf(operator1 * operator2);

        } else if (operators[1].equals("/")) {
            return isRoman ? arabicToRoman(operator1 / operator2) : String.valueOf(operator1 / operator2);
        }
        throw new Exception();
    }

    public static int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }
        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(input + " cannot be converted to a Roman Numeral");
        }

        return result;
    }

    public static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException(number + " is not in range (0,4000]");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

    public static boolean validate(String operator1, String operator2) throws Exception {
        Integer a;
        Integer b;
        try {
            a = romanToArabic(operator1);
            b = romanToArabic(operator2);
            return true;

        } catch (IllegalArgumentException exception) {
            try {
                a = Integer.parseInt(operator1);
                b = Integer.parseInt(operator2);
                if (a % 1 != 0 || b % 1 != 0) {
                    throw new Exception();
                }
                return false;
            } catch (NumberFormatException exception1) {
                throw new Exception();
            }
        }


    }
}

package br.com.luis.caclulator.converter;

public class NumberConverter {
    public static Double converToDouble(String strNumber) {

        String number = strNumber.replaceAll(",", ".");

        return Double.parseDouble(number);
    }

    public static boolean isNumeric(String strNumber) {
        if (strNumber == null) return true;

        String number = strNumber.replaceAll(",", ".");

        return !number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}

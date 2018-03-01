package lt.seb.ibannumber;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static lt.seb.ibannumber.ScannerUtils.*;

public class IbanNumber {
    private static final List<String> COUNTRY_CODES = Arrays.asList(Locale.getISOCountries());

    public static void main(String args[]){
        while (true) {
            switch (actionQuestion()) {
                case "1":
                    isIbanNumberValid(enterIbanNumber());
                    break;
                case "2":
                    scanFile(scanFilePath(), scanFileName());
                    break;
                default:
                    System.out.println("Choose between 1 or 2.");
            }
        }
    }

    private static void scanFile(String path, String fileName) {
        try (
                BufferedReader bufferreader =
                        new BufferedReader(new FileReader(path+"\\" + fileName));
                BufferedWriter buffetWritter =
                        new BufferedWriter(new FileWriter(fileName.replaceFirst("[.][^.]+$", "") + ".out"))
        ) {

            String line = bufferreader.readLine();
            while (line != null) {
                buffetWritter.write(line + ";" + isIbanNumberValid(line));
                buffetWritter.newLine();
                line = bufferreader.readLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    private static boolean isIbanNumberValid(String ibanNumber) {
        if (isCountryCodeValid(ibanNumber) && isIbanNumberCorrect(ibanNumber)) {
            System.out.println("true");
            return true;
        } else {
            System.out.println("false");
            return false;
        }
    }

    private static boolean isIbanNumberCorrect(String ibanString) {
        String swappedIbanString = ibanString.substring(4, ibanString.length()).concat(ibanString.substring(0, 4));
        StringBuilder ibanNumericBuilder = new StringBuilder();
        for (int i = 0; i < swappedIbanString.length(); i++) {
            ibanNumericBuilder.append(Character.getNumericValue(swappedIbanString.charAt(i)));
        }
        
        BigInteger ibanInt = new BigInteger(ibanNumericBuilder.toString());
        BigInteger module = BigInteger.valueOf(97);
        return ibanInt.remainder(module).equals(BigInteger.ONE);
    }
    
    private static boolean isCountryCodeValid(String ibanNumber) {
        String countryCode = ibanNumber.substring(0,2);
        return COUNTRY_CODES.contains(countryCode);
    }
}

package lt.seb.ibannumber;

import java.util.Scanner;

class ScannerUtils {
    private static Scanner scanner = new Scanner(System.in);

    static String actionQuestion() {
        System.out.println("Choose action:\n 1 - enter IBAN number\n 2 - read IBAN numbers from file");
        return scanner.nextLine();
    }
    static String enterIbanNumber() {
        System.out.println("Enter IBAN number:\n");
        String ibanNumber = scanner.nextLine();
        while (ibanNumber.length() > 30 || !ibanNumber.matches("[A-Z0-9]")) {
            System.out.println("IBAN number is not valid, reenter:\n");
            ibanNumber = scanner.nextLine();
        }
        return ibanNumber;
    }

    static String scanFilePath() {
        System.out.println("Enter file path:\n");
        return scanner.nextLine();
    }

    static String scanFileName() {
        System.out.println("Enter file name:\n");
        return scanner.nextLine();
    }
}

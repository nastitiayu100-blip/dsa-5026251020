package lw03.unguided;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Map<String, Integer> enrollment = new LinkedHashMap<>();
        List<String> checkResults = new ArrayList<>();

        int rejectedOperations = 0;

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("enrollment.txt"));
        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();
            String[] parts = line.split(" ");

            String operation = parts[0];
            String course = parts[1];

            if (operation.equals("REGISTER")) {
                int count = Integer.parseInt(parts[2]);
                if (count <= 0) {
                    rejectedOperations++;
                } else {
                    if (enrollment.containsKey(course)) {
                        int currentEnrollment = enrollment.get(course);
                        enrollment.put(course, currentEnrollment + count);
                    } else {
                        enrollment.put(course, count);
                    }
                }
            } else if (operation.equals("WITHDRAW")) {
                int count = Integer.parseInt(parts[2]);
                if (count <= 0) {
                    rejectedOperations++;
                } else {
                    if (enrollment.containsKey(course)) {
                        int currentEnrollment = enrollment.get(course);
                        if (currentEnrollment >= count) {
                            enrollment.put(course, currentEnrollment - count);
                        } else {
                            rejectedOperations++;
                        }
                    } else {
                        rejectedOperations++;
                    }
                }
            } else if (operation.equals("CHECK")) {
                if (enrollment.containsKey(course)) {
                    int currentEnrollment = enrollment.get(course);
                    checkResults.add(course + ": " + currentEnrollment + " students");
                } else {
                    checkResults.add(course + ": Not Found");
                }
            }
        }
        scanner.close();

        System.out.println("===== Enrollment Checks =====");
        for (String result : checkResults) {
            System.out.println(result);
        }

        System.out.println(" ===== Final Enrollment =====");
        for (String course : enrollment.keySet()) {
            int finalEnrollment = enrollment.get(course);
            System.out.println(course + ": " + finalEnrollment + " students");
        }

        System.out.println("Rejected operations: " + rejectedOperations);
    }  
}
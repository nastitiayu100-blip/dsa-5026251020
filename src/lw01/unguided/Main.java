package lw01.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Rental[] rentals;

        try (Scanner scanner = new Scanner(new File("rentals.txt"))) {
            int total = scanner.nextInt();
            rentals = new Rental[total];

            for (int i = 0; i < total; i++) {
                String type = scanner.next();
                String id = scanner.next();
                int days = scanner.nextInt();
                int units = scanner.nextInt();

                if (type.equals("LAPTOP")) {
                    rentals[i] = new LaptopRental(id, days, units);
                } else {
                    rentals[i] = new ProjectorRental(id, days, units);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("rentals.txt not found: " + e.getMessage());
            return;
        }

        for (Rental r : rentals) {
            System.out.println(r.summary());
        }
    }
}
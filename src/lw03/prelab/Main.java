package lw03.prelab;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        problem1();
        problem2();
        problem3();
    }

    // ========================================
    // PROBLEM 1 - PLAYLIST
    // ========================================
    public static void problem1() throws FileNotFoundException {

        List<String> playlist = new ArrayList<>();

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("playlist.txt"));
        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();
            String[] parts = line.split(" ", 2);

            String operation = parts[0];
            String song = parts[1];

            if (operation.equals("ADD")) {

                playlist.add(song);

            } else if (operation.equals("INSERT")) {

                String[] insertData = parts[1].split(" ", 2);

                int index = Integer.parseInt(insertData[0]);
                String songName = insertData[1];

                playlist.add(index, songName);

            } else if (operation.equals("REMOVE")) {

                playlist.remove(song);
            }
        }

        scanner.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());

        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
    }


    // =========================
    // PROBLEM 2 - SET
    // =========================
    public static void problem2() throws FileNotFoundException {

        Set<String> participants = new LinkedHashSet<>();

        int duplicateRegistrations = 0;

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("participants.txt"));

        while (scanner.hasNextLine()) {

            String name = scanner.nextLine();

            if (participants.contains(name)) {

                duplicateRegistrations++;

            } else {

                participants.add(name);
            }
        }

        scanner.close();

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());

        int number = 1;

        for (String participant : participants) {

            System.out.println(number + ". " + participant);

            number++;
        }

        System.out.println("Duplicate registrations: " + duplicateRegistrations);
    }


    // =========================
    // PROBLEM 3 - MAP
    // =========================
    public static void problem3() throws FileNotFoundException {

        Map<String, Integer> inventory = new LinkedHashMap<>();

        int failedSales = 0;

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("inventory.txt"));

        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();

            String[] parts = line.split(" ");

            String type = parts[0];
            String product = parts[1];
            int quantity = Integer.parseInt(parts[2]);

            if (type.equals("ADD")) {

                if (inventory.containsKey(product)) {

                    int currentStock = inventory.get(product);

                    inventory.put(product, currentStock + quantity);

                } else {

                    inventory.put(product, quantity);
                }

            } else if (type.equals("SELL")) {

                if (inventory.containsKey(product)) {

                    int currentStock = inventory.get(product);

                    if (currentStock >= quantity) {

                        inventory.put(product, currentStock - quantity);

                    } else {

                        failedSales++;
                    }

                } else {

                    failedSales++;
                }
            }
        }

        scanner.close();

        System.out.println("===== Problem 3 =====");

        for (String product : inventory.keySet()) {

            int stock = inventory.get(product);

            System.out.println(product + ": " + stock);
        }

        System.out.println("Failed sales: " + failedSales);
    }
}
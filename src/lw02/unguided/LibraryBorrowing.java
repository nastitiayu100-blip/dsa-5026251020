package lw02.unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class LibraryBorrowing {

    static final int MAX_BORROW = 2;
    public static void main(String[] args) {

        LinkedList<String[]> requests = new LinkedList<>();
        LinkedList<String[]> books = new LinkedList<>();
        LinkedList<String[]> members = new LinkedList<>();
        LinkedList<String[]> successRequests = new LinkedList<>();

        Queue<String[]> requestQueue = new LinkedList<>();
        Stack<String[]> requestStack = new Stack<>();

        Scanner scanner = new Scanner(LibraryBorrowing.class.getResourceAsStream("borrowing.txt"));

        while (scanner.hasNext()) {
            String[] request = new String[2];
            request[0] = scanner.next();
            request[1] = scanner.next();
            requests.add(request);
        }
        scanner.close();

        books.add(new String[] { "Kalkulus", "2" });
        books.add(new String[] { "Fisika", "1" });
        books.add(new String[] { "Statistika", "2" });

        for (String[] request : requests) {
            boolean exists = false;
            for (String[] data : members) {
                if (data[0].equals(request[0])) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                members.add(new String[] { request[0], "0" });
            }
        }

        for (String[] request : requests) {
            requestQueue.add(request);
        }

        while (!requestQueue.isEmpty()) {
            String[] request = requestQueue.poll();

            String name = request[0];
            String title = request[1];

            String[] member = null;
            String[] book = null;

            for (String[] data : members) {
                if (data[0].equals(name)) {
                    member = data;
                    break;
                }
            }

            for (String[] data : books) {
                if (data[0].equals(title)) {
                    book = data;
                    break;
                }
            }

            int stock = Integer.parseInt(book[1]);
            int borrowed = Integer.parseInt(member[1]);

            if (stock > 0 && borrowed < MAX_BORROW) {
                book[1] = String.valueOf(stock - 1);
                member[1] = String.valueOf(borrowed + 1);
                successRequests.add(request);
            } else {
                requestStack.push(request);
            }
        }

        System.out.println("=== Successfully Processed Requests ===");
        for (String[] request : successRequests) {
            System.out.println(request[0] + " " + request[1]);
        }

        System.out.println();
        System.out.println("=== Remaining Book Stock ===");
        for (String[] data : books) {
            System.out.println(data[0] + " : " + data[1]);
        }

        System.out.println();
        System.out.println("=== Failed Requests ===");
        while (!requestStack.isEmpty()) {
            String[] request = requestStack.pop();
            System.out.println(request[0] + " " + request[1]);
        }
    }
}

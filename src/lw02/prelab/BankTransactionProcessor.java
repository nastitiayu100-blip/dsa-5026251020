package lw02.prelab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BankTransactionProcessor {

    public static void main(String[] args) {

        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();

        Queue<String[]> transactionQueue = new LinkedList<>();
        Stack<String[]> transactionStack = new Stack<>();

        Scanner scanner = new Scanner(BankTransactionProcessor.class.getResourceAsStream("transactions.txt"));

        while (scanner.hasNext()) {
            String[] transaction = new String[3];
            transaction[0] = scanner.next();
            transaction[1] = scanner.next();
            transaction[2] = scanner.next();
            transactions.add(transaction);
        }

        scanner.close();

        for (String[] transaction : transactions) {
            boolean exists = false;
            for (String[] data : customers) {
                if (data[0].equals(transaction[0])) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                customers.add(new String[] { transaction[0], "0" });
            }
        }

        transactionQueue.addAll(transactions);

        while (!transactionQueue.isEmpty()) {
            String[] transaction = transactionQueue.poll();

            String name = transaction[0];
            String type = transaction[1];
            int amount = Integer.parseInt(transaction[2]);

            String[] customer = null;

            for (String[] data : customers) {
                if (data[0].equals(name)) {
                    customer = data;
                    break;
                }
            }

            int balance = Integer.parseInt(customer[1]);

            if (type.equals("DEPOSIT")) {
                customer[1] = String.valueOf(balance + amount);
            } else if (type.equals("WITHDRAW")) {
                if (amount > balance) {
                    transactionStack.push(transaction);
                } else {
                    customer[1] = String.valueOf(balance - amount);
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] data : customers) {
            System.out.println(data[0] + " : " + data[1]);
        }

        System.out.println();
        System.out.println("=== Failed Transactions ===");
        while (!transactionStack.isEmpty()) {
            String[] transaction = transactionStack.pop();
            System.out.println(transaction[0] + " " + transaction[1] + " " + transaction[2]);
        }
    }
}
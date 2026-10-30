package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class InventorySearchApp {
    public static void main(String[] args) {

        ArrayList<Product> inventory = InventorySearchApp.getInventory();

        String fileName = "src/main/resources/inventory.csv";
        BufferedReader bufReader = null;
        System.out.println("our inventory is: ");
        try {
            FileReader fileReader = new FileReader(fileName);
            bufReader = new BufferedReader(fileReader);

            String line = bufReader.readLine();
            while (line != null) {
                Product product = parseProduct(line);
                inventory.add(product);
                System.out.printf("%d %s $%.2f%n", product.getId(), product.getName(), product.getPrice());

                line = bufReader.readLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("erm, sorry this does not exist");
            ;
        } catch (IOException e) {
            System.out.println("erm, there is a problem right now");
            ;
        } finally {
            // Best practice: Close the reader if it was opened
            if (bufReader != null) {
                try {
                    bufReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }
    }

    private static Product parseProduct(String line) {
        String[] lineParts = line.split(Pattern.quote("|"));
        int productId = Integer.parseInt(lineParts[0]);
        String name = lineParts[1];
        double price = Double.parseDouble(lineParts[2]);

        Product product = new Product(productId, name, price);
        return product;
    }

    private static ArrayList<Product> getInventory() {
        ArrayList<Product> inventory = new ArrayList<Product>();
        return inventory;
    }
}

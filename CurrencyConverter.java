package Task_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {

    // Fetch exchange rate using open.er-api.com
    static double getExchangeRate(String base, String target) {
        try {
            String apiURL = "https://open.er-api.com/v6/latest/" + base;

            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader br =
                new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line, response = "";
            while ((line = br.readLine()) != null) {
                response += line;
            }
            br.close();

            // Find target currency rate manually
            String search = "\"" + target + "\":";
            int index = response.indexOf(search);

            if (index == -1) return -1;

            int start = index + search.length();
            int end = response.indexOf(",", start);

            return Double.parseDouble(response.substring(start, end));

        } catch (Exception e) {
            return -1;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("======================================");
        System.out.println("        REAL-TIME CURRENCY CONVERTER");
        System.out.println("======================================");

        while (true) {
            System.out.println("\nMENU:");
            System.out.println("1. Convert Currency");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            if (choice == 2) {
                System.out.println("\nThank you for using Currency Converter!");
                break;
            }

            System.out.print("Enter Base Currency Code (USD, EUR, INR): ");
            String base = sc.next().toUpperCase();

            System.out.print("Enter Target Currency Code (USD, EUR, INR): ");
            String target = sc.next().toUpperCase();

            System.out.print("Enter amount in " + base + ": ");
            double amount = sc.nextDouble();

            System.out.println("\nFetching exchange rate... Please wait.");

            double rate = getExchangeRate(base, target);

            if (rate == -1) {
                System.out.println("Conversion failed.");
                System.out.println("Invalid currency code or internet issue.");
            } else {
                double result = amount * rate;
                System.out.println("--------------------------------------");
                System.out.println("Converted Amount: " + result + " " + target);
                System.out.println("--------------------------------------");
            }
        }

        sc.close();
    }
}

package FinancialForecasting;

import java.util.Arrays;
import java.util.Scanner;

public class FinancialForecastingDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FinancialData data = new FinancialData(12);  
        FinancialForecastingEngine engine = new FinancialForecastingEngine(data);

        System.out.println("📊 FINANCIAL FORECASTING SYSTEM");

        
        while (data.getCurrentMonth() < 12) {
            System.out.print("Enter revenue for " + data.getMonths()[data.getCurrentMonth()] + ": ");
            String input = scanner.nextLine();

            if (input.isEmpty()) break;

            try {
                double revenue = Double.parseDouble(input);
                data.addMonthlyRevenue(revenue);
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Try again.");
            }
        }

        
        boolean running = true;
        while (running) {
            System.out.println("\n=== FORECAST MENU ===");
            System.out.println("1. Simple Moving Average Forecast");
            System.out.println("2. Linear Growth Forecast");
            System.out.println("3. Exponential Smoothing Forecast");
            System.out.println("4. Seasonal Forecast");
            System.out.println("5. Show Historical Data");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter forecast periods: ");
                    int smaPeriods = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter window size: ");
                    int window = Integer.parseInt(scanner.nextLine());
                    double[] sma = engine.simpleMovingAverageForcast(smaPeriods, window);
                    printForecast(sma);
                    break;

                case "2":
                    System.out.print("Enter forecast periods: ");
                    int lgPeriods = Integer.parseInt(scanner.nextLine());
                    double[] lg = engine.linearGrowthForecast(lgPeriods);
                    printForecast(lg);
                    break;

                case "3":
                    System.out.print("Enter forecast periods: ");
                    int esPeriods = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter alpha (0–1): ");
                    double alpha = Double.parseDouble(scanner.nextLine());
                    double[] es = engine.exponentialSmoothingForecast(esPeriods, alpha);
                    printForecast(es);
                    break;

                case "4":
                    System.out.print("Enter forecast periods: ");
                    int sfPeriods = Integer.parseInt(scanner.nextLine());
                    double[] sf = engine.seasonalForecast(sfPeriods);
                    printForecast(sf);
                    break;

                case "5":
                    engine.displayHistoricalData();
                    break;

                case "6":
                    running = false;
                    System.out.println("👋 Exiting...");
                    break;

                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }

        scanner.close();
    }

    private static void printForecast(double[] forecast) {
        System.out.println("📈 Forecast:");
        for (int i = 0; i < forecast.length; i++) {
            System.out.printf("Month %d: $%.2f\n", i + 1, forecast[i]);
        }
    }
}

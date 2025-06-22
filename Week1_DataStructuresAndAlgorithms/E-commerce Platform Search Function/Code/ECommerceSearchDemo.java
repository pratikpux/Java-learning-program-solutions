package ecommerce;

import java.util.List;
import java.util.Scanner;

public class ECommerceSearchDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ECommerceSearchEngine engine = new ECommerceSearchEngine();
        boolean running = true;

        System.out.println("=== 🛒 E-COMMERCE PRODUCT SEARCH SYSTEM ===");

        while (running) {
            showMenu();
            System.out.print("Enter your choice (1–7): ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    engine.displayAllProducts();
                    break;

                case "2":
                    System.out.print("Enter product keyword: ");
                    String keyword = scanner.nextLine();
                    List<Product> keywordResults = engine.linearSearchByName(keyword);
                    printResults(keywordResults);
                    break;

                case "3":
                    System.out.print("Enter exact product name: ");
                    String exactName = scanner.nextLine();
                    Product exactMatch = engine.binarySearchByName(exactName);
                    if (exactMatch != null) {
                        System.out.println("✅ Product Found:");
                        System.out.println(exactMatch);
                    } else {
                        System.out.println("❌ No product found with that exact name.");
                    }
                    break;

                case "4":
                    System.out.print("Enter category (e.g., 'Electronics'): ");
                    String category = scanner.nextLine();
                    List<Product> catResults = engine.filterByCategory(category);
                    printResults(catResults);
                    break;

                case "5":
                    try {
                        System.out.print("Enter minimum price: ");
                        double min = Double.parseDouble(scanner.nextLine());

                        System.out.print("Enter maximum price: ");
                        double max = Double.parseDouble(scanner.nextLine());

                        List<Product> priceResults = engine.filterByPriceRange(min, max);
                        printResults(priceResults);
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Invalid price input.");
                    }
                    break;

                case "6":
                    try {
                        System.out.print("Enter name keyword (or leave blank): ");
                        String name = scanner.nextLine();

                        System.out.print("Enter category (or leave blank): ");
                        String cat = scanner.nextLine();

                        System.out.print("Enter minimum price: ");
                        double minP = Double.parseDouble(scanner.nextLine());

                        System.out.print("Enter maximum price: ");
                        double maxP = Double.parseDouble(scanner.nextLine());

                        List<Product> advResults = engine.advancedSearch(name, cat, minP, maxP);
                        printResults(advResults);
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Invalid input. Please enter valid numbers for price.");
                    }
                    break;

                case "7":
                    running = false;
                    System.out.println("👋 Exiting the system. Thank you!");
                    break;

                default:
                    System.out.println("❌ Invalid choice. Please enter a number between 1 and 7.");
            }
        }

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\n📋 MENU:");
        System.out.println("1. View All Products");
        System.out.println("2. Search by Keyword (Partial Match)");
        System.out.println("3. Search by Exact Name (Binary Search)");
        System.out.println("4. Filter by Category");
        System.out.println("5. Filter by Price Range");
        System.out.println("6. Advanced Search (Name + Category + Price)");
        System.out.println("7. Exit");
    }

    private static void printResults(List<Product> results) {
        if (results.isEmpty()) {
            System.out.println("⚠️ No matching products found.");
        } else {
            System.out.println("✅ Search Results:");
            for (Product p : results) {
                System.out.println(p);
            }
        }
    }
}

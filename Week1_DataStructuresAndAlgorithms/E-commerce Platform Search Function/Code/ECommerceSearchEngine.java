package ecommerce;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//  E-commerce Search Engine with multiple search algorithms
 
class ECommerceSearchEngine {
    private List<Product> products;
    private List<Product> sortedByName;
    private List<Product> sortedByPrice;

    public ECommerceSearchEngine() {
        this.products = new ArrayList<>();
        initializeProducts();
        createSortedLists();
    }

    private void initializeProducts() {
        products.add(new Product(1, "iPhone 14", "Electronics", 999.99, 50));
        products.add(new Product(2, "Samsung Galaxy", "Electronics", 899.99, 30));
        products.add(new Product(3, "MacBook Pro", "Electronics", 1999.99, 20));
        products.add(new Product(4, "Nike Air Max", "Fashion", 149.99, 100));
        products.add(new Product(5, "Adidas Ultraboost", "Fashion", 179.99, 75));
        products.add(new Product(6, "Coffee Maker", "Home", 89.99, 40));
        products.add(new Product(7, "Blender", "Home", 59.99, 60));
        products.add(new Product(8, "Gaming Chair", "Furniture", 299.99, 25));
        products.add(new Product(9, "Desk Lamp", "Furniture", 39.99, 80));
        products.add(new Product(10, "Wireless Mouse", "Electronics", 29.99, 120));
    }

    private void createSortedLists() {
        
        sortedByName = new ArrayList<>(products);
        sortedByPrice = new ArrayList<>(products);

        sortedByName.sort(Comparator.comparing(Product::getName));
        sortedByPrice.sort(Comparator.comparing(Product::getPrice));
    }

    
    public List<Product> linearSearchByName(String searchTerm) {
        List<Product> results = new ArrayList<>();
        String lowerSearchTerm = searchTerm.toLowerCase();

        for (Product product : products) {
            if (product.getName().toLowerCase().contains(lowerSearchTerm)) {
                results.add(product);
            }
        }
        return results;
    }

    
    public Product binarySearchByName(String name) {
        int left = 0, right = sortedByName.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = sortedByName.get(mid).getName().compareToIgnoreCase(name);

            if (comparison == 0) {
                return sortedByName.get(mid);
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    
    public List<Product> filterByCategory(String category) {
        List<Product> results = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                results.add(product);
            }
        }
        return results;
    }

    
    public List<Product> filterByPriceRange(double minPrice, double maxPrice) {
        List<Product> results = new ArrayList<>();
        for (Product product : products) {
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                results.add(product);
            }
        }
        return results;
    }

   

    public List<Product> advancedSearch(String name, String category, double minPrice, double maxPrice) {
        List<Product> results = new ArrayList<>();

        for (Product product : products) {
            boolean matchesName = name == null || name.isEmpty() ||
                    product.getName().toLowerCase().contains(name.toLowerCase());
            boolean matchesCategory = category == null || category.isEmpty() ||
                    product.getCategory().equalsIgnoreCase(category);
            boolean matchesPrice = product.getPrice() >= minPrice && product.getPrice() <= maxPrice;

            if (matchesName && matchesCategory && matchesPrice) {
                results.add(product);
            }
        }
        return results;
    }

    public void displayAllProducts() {
        System.out.println("All Products:");
        for (Product product : products) {
            System.out.println(product);
        }
        System.out.println();
    }
}

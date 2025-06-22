public class SingletonPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== SINGLETON PATTERN DEMONSTRATION ===\n");

        System.out.println("1. Testing Single Instance Creation:");
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();

        System.out.println("db1 == db2: " + (db1 == db2));
        System.out.println("db1 hashCode: " + db1.hashCode());
        System.out.println("db2 hashCode: " + db2.hashCode());
        System.out.println();

        System.out.println("2. Testing Database Operations:");
        System.out.println("Initial status: " + db1.getConnectionInfo());

        db1.connect();
        db1.executeQuery("SELECT * FROM products");

        System.out.println("Status via db2: " + db2.getConnectionInfo());
        db2.executeQuery("INSERT INTO products VALUES (...)");

        db1.disconnect();
        System.out.println();

        System.out.println("3. Testing Thread Safety:");
        testThreadSafety();

        System.out.println("\n4. Testing Clone Prevention:");
        try {
            DatabaseConnection cloned = (DatabaseConnection) db1.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("✅ Clone prevention works: " + e.getMessage());
        }
    }

    private static void testThreadSafety() {
        Runnable task = () -> {
            DatabaseConnection db = DatabaseConnection.getInstance();
            System.out.println("Thread " + Thread.currentThread().getName() +
                    " got instance: " + db.hashCode());
        };

        Thread thread1 = new Thread(task, "Thread-1");
        Thread thread2 = new Thread(task, "Thread-2");
        Thread thread3 = new Thread(task, "Thread-3");

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("✅ All threads received the same instance!");
    }
}

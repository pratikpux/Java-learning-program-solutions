class DatabaseConnection {
    private static volatile DatabaseConnection instance;
    private String connectionUrl;
    private String username;
    private boolean isConnected;
    private int connectionCount;

    private DatabaseConnection() {
        this.connectionUrl = "jdbc:mysql://localhost:3306/ecommerce_db";
        this.username = "admin";
        this.isConnected = false;
        this.connectionCount = 0;

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("DatabaseConnection instance created successfully!");
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public void connect() {
        if (!isConnected) {
            isConnected = true;
            connectionCount++;
            System.out.println("✅ Connected to database: " + connectionUrl);
            System.out.println("Connection #" + connectionCount);
        } else {
            System.out.println("⚠️ Already connected to database!");
        }
    }

    public void disconnect() {
        if (isConnected) {
            isConnected = false;
            System.out.println("❌ Disconnected from database");
        } else {
            System.out.println("⚠️ No active connection to disconnect!");
        }
    }

    public void executeQuery(String query) {
        if (isConnected) {
            System.out.println("🔍 Executing query: " + query);
            System.out.println("✅ Query executed successfully!");
        } else {
            System.out.println("❌ Cannot execute query: No database connection!");
        }
    }

    public String getConnectionInfo() {
        return String.format("URL: %s | User: %s | Connected: %s | Total Connections: %d",
                connectionUrl, username, isConnected, connectionCount);
    }

    public boolean isConnected() {
        return isConnected;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Singleton instance cannot be cloned");
    }
}

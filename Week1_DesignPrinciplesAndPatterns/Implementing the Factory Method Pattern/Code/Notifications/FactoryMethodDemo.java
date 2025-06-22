package Notifications;

import java.util.Scanner;

public class FactoryMethodDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== FACTORY METHOD DEMO ===");
        System.out.print("Enter notification type (EMAIL/SMS/PUSH): ");
        String type = scanner.nextLine().trim().toUpperCase();

        System.out.print("Enter recipient: ");
        String recipient = scanner.nextLine();

        System.out.print("Enter message: ");
        String message = scanner.nextLine();

        NotificationCreator creator = null;

        switch (type) {
            case "EMAIL":
                creator = new EmailNotificationCreator();
                break;
            case "SMS":
                creator = new SMSNotificationCreator();
                break;
            case "PUSH":
                creator = new PushNotificationCreator();
                break;
            default:
                System.out.println("❌ Invalid type! Use EMAIL, SMS, or PUSH.");
                scanner.close();
                return;
        }

        creator.sendNotification(message, recipient);
        scanner.close();
    }
}

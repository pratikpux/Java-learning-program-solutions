package Notifications;


class PushNotification extends Notification {
    public PushNotification(String message, String recipient) {
        super(message, recipient);
    }

    @Override
    public void send() {
        System.out.println("Sending PUSH notification to: " + recipient);
        System.out.println("Message: " + message);
        System.out.println("Push notification sent successfully!\n");
    }

    @Override
    public String getType() {
        return "PUSH";
    }
}
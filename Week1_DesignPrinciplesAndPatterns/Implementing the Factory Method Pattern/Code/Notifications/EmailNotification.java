package Notifications;


class EmailNotification extends Notification {
    public EmailNotification(String message, String recipient) {
        super(message, recipient);
    }

    @Override
    public void send() {
        System.out.println("Sending EMAIL to: " + recipient);
        System.out.println("Message: " + message);
        System.out.println("Email sent successfully!\n");
    }

    @Override
    public String getType() {
        return "EMAIL";
    }
}
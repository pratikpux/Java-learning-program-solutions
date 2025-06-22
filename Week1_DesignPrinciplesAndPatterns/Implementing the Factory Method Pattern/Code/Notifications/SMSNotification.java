package Notifications;



class SMSNotification extends Notification {
    public SMSNotification(String message, String recipient) {
        super(message, recipient);
    }

    @Override
    public void send() {
        System.out.println("Sending SMS to: " + recipient);
        System.out.println("Message: " + message);
        System.out.println("SMS sent successfully!\n");
    }

    @Override
    public String getType() {
        return "SMS";
    }
}

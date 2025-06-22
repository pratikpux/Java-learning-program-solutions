package Notifications;

class SMSNotificationCreator extends NotificationCreator {
    @Override
    public Notification createNotification(String message, String recipient) {
        return new SMSNotification(message, recipient);
    }
}
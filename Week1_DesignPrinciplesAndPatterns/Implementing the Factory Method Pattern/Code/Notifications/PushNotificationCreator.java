package Notifications;

class PushNotificationCreator extends NotificationCreator {
    @Override
    public Notification createNotification(String message, String recipient) {
        return new PushNotification(message, recipient);
    }
}

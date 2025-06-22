package Notifications;


class EmailNotificationCreator extends NotificationCreator {
    @Override
    public Notification createNotification(String message, String recipient) {
        return new EmailNotification(message, recipient);
    }
}

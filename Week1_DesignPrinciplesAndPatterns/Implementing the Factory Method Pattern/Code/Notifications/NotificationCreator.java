package Notifications;



abstract class NotificationCreator {
    
    public abstract Notification createNotification(String message, String recipient);

    public void sendNotification(String message, String recipient) {
        Notification notification = createNotification(message, recipient);
        notification.send();
    }
}
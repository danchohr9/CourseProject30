package bg.tu_varna.sit.courseproject30.business.services;


import bg.tu_varna.sit.courseproject30.data.entities.*;
import bg.tu_varna.sit.courseproject30.data.repositorities.NotificationRepository;
import bg.tu_varna.sit.courseproject30.data.repositorities.UserNotificationRepository;
import bg.tu_varna.sit.courseproject30.data.repositorities.UserRepository;
import bg.tu_varna.sit.courseproject30.presentation.models.NotificationViewModel;
import bg.tu_varna.sit.courseproject30.presentation.models.UserViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class NotificationService {
    public static NotificationService getInstance() {
        return NotificationService.NotificationServiceHolder.INSTANCE;
    }
    public final NotificationRepository notificationRepository = NotificationRepository.getInstance();
    public final UserNotificationRepository userNotificationRepository = UserNotificationRepository.getInstance();
    public final UserRepository userRepository = UserRepository.getInstance();

    private static class NotificationServiceHolder {
        public static final NotificationService INSTANCE = new NotificationService();
    }

    public Notification createNotification(String title, String message, Date date){
        Notification notification = new Notification(title, message, date);
        notificationRepository.save(notification);
        return  notificationRepository.getLastInserted();
    }

    public void sendNotifications(Notification notification){
        List<User> userList = userRepository.getAll();
        UserNotification userNotification = new UserNotification();
        userNotification.setNotification(notification);
        for(User u:userList){
            userNotification.setUser(u);
            userNotificationRepository.save(userNotification);
        }
    }

    public ObservableList<NotificationViewModel> getAllOfUser(UserViewModel user) {
        System.out.println(user.getUsername());
        List<UserNotification> userNotifications = userNotificationRepository.getAllOfUser(user.getUsername());
        System.out.println(userNotifications.toString());

        return FXCollections.observableList(
                userNotifications
                        .stream()
                        .map(u -> new NotificationViewModel(
                                Math.toIntExact(u.getId()),
                                u.getNotification().getTitle(),
                                u.getNotification().getMessage()
                        )).collect(Collectors.toList()));
    }

    public void deleteUserNotification(NotificationViewModel toRemove) {
        UserNotification userNotification = new UserNotification(toRemove.getId());
        userNotificationRepository.delete(userNotification);
    }

    public void deleteAllNotificationsOfUser(UserViewModel userViewModel){
        User user = userRepository.getUserByUsername(userViewModel.getUsername());
        userNotificationRepository.deleteAllOfUser(user);
    }
}

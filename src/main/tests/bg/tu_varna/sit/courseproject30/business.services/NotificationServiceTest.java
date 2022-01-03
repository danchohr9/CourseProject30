package bg.tu_varna.sit.courseproject30.business.services;

import bg.tu_varna.sit.courseproject30.application.HelloApplication;
import bg.tu_varna.sit.courseproject30.common.Constants;
import bg.tu_varna.sit.courseproject30.data.entities.Notification;
import bg.tu_varna.sit.courseproject30.data.entities.User;
import bg.tu_varna.sit.courseproject30.data.entities.UserNotification;
import bg.tu_varna.sit.courseproject30.data.repositorities.NotificationRepository;
import bg.tu_varna.sit.courseproject30.data.repositorities.UserNotificationRepository;
import bg.tu_varna.sit.courseproject30.data.repositorities.UserRepository;
import bg.tu_varna.sit.courseproject30.presentation.models.NotificationViewModel;
import bg.tu_varna.sit.courseproject30.presentation.models.UserViewModel;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.*;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NotificationServiceTest {

    private NotificationService service;
    private NotificationRepository repository;
    UserNotificationRepository userNotificationRepository;
    @BeforeEach
    void setUp() {
        PropertyConfigurator.configure(HelloApplication.class.getResource(Constants.Configurations.LOG4J_PROPERTIES));
        service = NotificationService.getInstance();
        repository = NotificationRepository.getInstance();
        userNotificationRepository = UserNotificationRepository.getInstance();
    }

    @Test
    @Order(1)
    void createNotification() {
        Date date=new Date();
        int id = service.createNotification("test","test",date).getId();
        assertEquals(repository.getLastInserted().getId(),id);
    }

    @Test
    @Order(2)
    void sendNotifications() {
        UserRepository userRepository = UserRepository.getInstance();
        User user = userRepository.getLastInserted();
        UserNotification userNotification = userNotificationRepository.getLastInserted();
        List<UserNotification> userNotifications = userNotificationRepository.getAllOfUser(user.getUsername());
        Notification notification = repository.getLastInserted();
        service.sendNotifications(notification);
        assertNotEquals(userNotifications.size(), userNotificationRepository.getAllOfUser(user.getUsername()).size());
    }

    @Test
    @Order(4)
    void getAllOfUser() {
        UserNotification userNotification = userNotificationRepository.getLastInserted();
        UserViewModel user = new UserViewModel(Math.toIntExact(userNotification.getUser().getId()),userNotification.getUser().getUsername(),
                userNotification.getUser().getPassword(),userNotification.getUser().getEmail(),1);
        List<NotificationViewModel> list = service.getAllOfUser(user);
        assertEquals(list.size(),service.getAllOfUser(user).size());
    }

    @Test
    @Order(3)
    void deleteUserNotification() {
        int id = repository.getLastInserted().getId();
        NotificationViewModel notificationViewModel = new NotificationViewModel(id,"","");
        service.deleteUserNotification(notificationViewModel);
        assertNull(repository.getById(id));
    }

    @Test
    @Order(5)
    void deleteAllNotificationsOfUser() {
        UserNotification userNotification = userNotificationRepository.getLastInserted();
        UserViewModel user = new UserViewModel(Math.toIntExact(userNotification.getUser().getId()),userNotification.getUser().getUsername(),
                userNotification.getUser().getPassword(),userNotification.getUser().getEmail(),1);
        service.deleteAllNotificationsOfUser(user);
        assertEquals("[]",service.getAllOfUser(user).toString());
        repository.delete(repository.getLastInserted());
    }

}
package bg.tu_varna.sit.courseproject30.business.services;

import bg.tu_varna.sit.courseproject30.application.HelloApplication;
import bg.tu_varna.sit.courseproject30.common.Constants;
import bg.tu_varna.sit.courseproject30.data.entities.Notification;
import bg.tu_varna.sit.courseproject30.data.entities.UserNotification;
import bg.tu_varna.sit.courseproject30.data.repositorities.NotificationRepository;
import bg.tu_varna.sit.courseproject30.data.repositorities.UserNotificationRepository;
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
        List<UserNotification> userNotifications = userNotificationRepository.getAllOfUser("a");
        Notification notification = repository.getLastInserted();
        service.sendNotifications(notification);
        assertNotEquals(userNotifications, userNotificationRepository.getAllOfUser("a"));
    }

    @Test
    @Order(4)
    void getAllOfUser() {
        UserViewModel user = new UserViewModel(1,"admin","test","admin@gmail.com",1);
        List<NotificationViewModel> list = service.getAllOfUser(user);
        assertEquals(list.size(),service.getAllOfUser(user).size());
    }

    @Test
    @Order(3)
    void deleteUserNotification() {
        NotificationViewModel notificationViewModel = new NotificationViewModel(repository.getLastInserted().getId(),"","");
        service.deleteUserNotification(notificationViewModel);
        assertNull(repository.getById(48));
    }

    @Test
    @Order(5)
    void deleteAllNotificationsOfUser() {
        UserViewModel user = new UserViewModel(14,"mol5","test","test",1);
        service.deleteAllNotificationsOfUser(user);
        assertEquals("[]",service.getAllOfUser(user).toString());
        repository.delete(repository.getLastInserted());
    }

}
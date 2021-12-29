package bg.tu_varna.sit.courseproject30.data.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table(name = "javaproject.notification")
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id", nullable = false)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "date_created", nullable = false)
    private Date date_created;

    @OneToMany(mappedBy = "notification")
    private Set<UserNotification> userNotificationSet;

    public Notification(){}

    public Notification(String title, String message, Date date_created) {
        this.title = title;
        this.message = message;
        this.date_created = date_created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public Set<UserNotification> getUserNotificationSet() {
        return userNotificationSet;
    }

    public void setUserNotificationSet(Set<UserNotification> userNotificationSet) {
        this.userNotificationSet = userNotificationSet;
    }
}

package seniorproject.commercebank2;



import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "mic_credentials")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "group")
    private String groupName;

    @Column(name = "account")
    private String accountName;

    @Column(name = "password")
    private String password;

    @Column(name = "salt")
    private String salt;

    @Column(name = "create_user")
    private String userName;

    /*
    @Column(name = "create_timestamp")
    @CreationTimestamp
    private Timestamp timestamp;*/

    public User(String groupName, String accountName, String password, String salt, String userName) {
        this.groupName = groupName;
        this.accountName = accountName;
        this.password = password;
        this.salt = salt;
        this.userName = userName;
        //this.timestamp = CURRENT.TIM;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

//    public Date getCreateDate() {
//        return createDate;
//    }
//
//    public void setCreateDate(Date createDate) {
//        this.createDate = createDate;
//    }
}

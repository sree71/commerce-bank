package seniorproject.commercebank2;



import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "mic_credentials")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_group")
    private String groupName;

    @Column(name = "user_account")
    private String accountName;

    @Column(name = "password")
    private String password;

    @Column(name = "salt")
    private String salt;

    @Column(name = "create_user")
    private String userName;


    public void updateUser(String groupName, String accountName, String password, String salt, String userName) {
        this.groupName = groupName;
        this.accountName = accountName;
        this.password = password;
        this.salt = salt;
        this.userName = userName;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", accountName='" + accountName + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}

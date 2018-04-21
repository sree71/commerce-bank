package seniorproject.commercebank2;



import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "mic_credentials_ul")
public class Login implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "password")
    private String password;


    public void updateUser(String groupName, String name, String password) {
        this.groupName = groupName;
        this.name = name;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

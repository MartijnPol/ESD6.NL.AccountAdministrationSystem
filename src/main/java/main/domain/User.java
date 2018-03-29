package main.domain;

import main.utils.EncryptionHelper;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martijn van der Pol on 22-03-18
 **/

@XmlRootElement
@Entity
@NamedQueries({
        @NamedQuery(name = "user.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username")
})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    @Email
    private String mailAddress;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "users")
    private List<UserGroup> userGroups;

    /**
     * Empty constructor
     */
    public User() {
        this.userGroups = new ArrayList<UserGroup>();
    }

    /**
     * Constructor to create a new user object
     *
     * @param username
     * @param password
     * @param mailAddress
     */
    public User(String username, @Size(min = 6, max = 20) String password, @Email String mailAddress) {
        this();
        this.username = username;
        this.mailAddress = mailAddress;

        try {
            this.password = EncryptionHelper.encryptData(password);
        } catch (Exception ex) {
            System.out.println("exception message = " + ex.getMessage());
        }
    }

    public void addUserGroup(UserGroup userGroup) {
        this.userGroups.add(userGroup);
    }

    //<editor-fold defaultState=collapsed desc="Getters/Setters">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        try {
            this.password = EncryptionHelper.encryptData(password);
        } catch (Exception ex) {
            System.out.println("exception message = " + ex.getMessage());
        }
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    //</editor-fold>
}

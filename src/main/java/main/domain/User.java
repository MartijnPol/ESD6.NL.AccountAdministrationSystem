package main.domain;

import main.utils.EncryptionHelper;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Martijn van der Pol on 22-03-18
 **/

@Entity
@NamedQueries({
        @NamedQuery(name = "user.findByCredentials", query = "SELECT u FROM User u " +
                "WHERE u.username = :username AND u.password = :password")
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

    @JoinTable(name = "USERS_GROUPS",
            joinColumns
                    = @JoinColumn(name = "USERNAME", referencedColumnName = "username"),
            inverseJoinColumns
                    = @JoinColumn(name = "GROUPNAME", referencedColumnName = "groupName")
    )

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Collection<Group> group = new ArrayList<Group>();

    /**
     * Empty constructor
     */
    public User() {

    }

    /**
     * Constructor to create a new user object
     * @param username
     * @param password
     * @param mailAddress
     */
    public User(String username, @NotNull String password, @Email String mailAddress) {
        this.username = username;
        this.mailAddress = mailAddress;

        try {
            this.password = EncryptionHelper.encryptPassword(username, password);
        }
        catch (Exception ex){
            System.out.println("exception message = " + ex.getMessage());
        }

    }

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
        this.password = password;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
}

package main.domain;

import javax.persistence.*;
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
        @NamedQuery(name = "userGroup.findByGroupName", query = "SELECT ug FROM UserGroup ug WHERE ug.groupName = :groupName"),
        @NamedQuery(name = "userGroup.getRegularUserGroup", query = "SELECT ug FROM UserGroup ug WHERE ug.groupName = 'Regular'")
})
public class UserGroup implements Serializable {

    @Id
    private String groupName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usergroup_user",
            joinColumns = @JoinColumn(name = "groupName",
                    referencedColumnName = "groupName"),
            inverseJoinColumns = @JoinColumn(name = "username",
                    referencedColumnName = "username"))
    private List<User> users;

    public UserGroup() {
        this.users = new ArrayList<>();
    }

    public UserGroup(String groupName) {
        this();
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void addMultipleUsers(List<User> users) {
        this.users.addAll(users);
    }
}

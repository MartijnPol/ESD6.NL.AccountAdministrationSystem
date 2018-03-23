package main.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Martijn van der Pol on 22-03-18
 **/

@Entity(name = "GROUPS")
public class Group implements Serializable {

    @Id
    private String groupName;

    public Group() {}

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}

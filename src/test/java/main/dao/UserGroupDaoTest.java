package main.dao;

import main.domain.User;
import main.domain.UserGroup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalMatchers;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserGroupDaoTest {

    private UserGroup userGroup;
    private List<UserGroup> userGroups;

    @Mock
    private UserGroupDao userGroupDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        userGroup = new UserGroup("Admin");
        User userFirst = new User();
        User userSecond = new User();

        userFirst.setUsername("DuckDuckGo");
        userFirst.setMailAddress("duckduck@duckstad.nl");
        userFirst.setId(1L);

        userSecond.setUsername("Google");
        userSecond.setMailAddress("google@gmail.com");
        userSecond.setId(2L);

        userGroup.addUser(userFirst);
        userGroup.addUser(userSecond);

        userGroups = new ArrayList<UserGroup>();
        userGroups.add(userGroup);
    }

    @Test
    public void createUserGroupTest() {
        userGroupDao.create(userGroup);

        when(userGroupDao.findAll()).thenReturn(userGroups);

        List<UserGroup> allGroups = userGroupDao.findAll();

        assertThat(allGroups.get(0).getGroupName(), is("Admin"));
        assertThat(allGroups.get(0).getUsers().size(), is(2));
        assertThat(allGroups.get(0).getUsers().get(0).getUsername(), is("DuckDuckGo"));
        assertThat(allGroups.get(0).getUsers().get(1).getUsername(), is("Google"));
        assertThat(allGroups.size(), is(1));
    }

    @Test
    public void updateUserGroupTest() {
        userGroupDao.create(userGroup);

        when(userGroupDao.findAll()).thenReturn(userGroups);

        List<UserGroup> allGroups = userGroupDao.findAll();

        assertThat(allGroups.get(0).getGroupName(), is("Admin"));
        assertThat(allGroups.get(0).getUsers().size(), is(2));

        userGroup.setGroupName("Moderator");
        userGroupDao.update(userGroup);

        when(userGroupDao.findAll()).thenReturn(userGroups);

        List<UserGroup> allUpdatedGroups = userGroupDao.findAll();

        assertThat(allUpdatedGroups.get(0).getGroupName(), is("Moderator"));
        assertThat(allUpdatedGroups.get(0).getUsers().size(), is(2));
    }

    @Test
    public void findByGroupNameTest() {
        userGroupDao.create(userGroup);

        when(userGroupDao.findByGroupName(Matchers.eq("Admin"))).thenReturn(userGroup);
        when(userGroupDao.findByGroupName(AdditionalMatchers.not(Matchers.eq("Admin")))).thenReturn(null);

        UserGroup adminUserGroup = userGroupDao.findByGroupName("Admin");
        UserGroup moderatorUserGroup = userGroupDao.findByGroupName("Moderator");

        assertThat(adminUserGroup.getGroupName(), is("Admin"));
        assertThat(adminUserGroup.getUsers().size(), is(2));
        assertThat(adminUserGroup.getUsers().get(0).getUsername(), is("DuckDuckGo"));
        assertThat(adminUserGroup.getUsers().get(1).getUsername(), is("Google"));
        assertThat(moderatorUserGroup, is(nullValue()));
    }

    @Test
    public void getRegularUserGroup() {
        userGroupDao.create(userGroup);

        userGroup.setGroupName("Regular");
        userGroupDao.update(userGroup);

        when(userGroupDao.getRegularUserGroup()).thenReturn(userGroup);

        UserGroup regularUserGroup = userGroupDao.getRegularUserGroup();

        assertThat(regularUserGroup.getGroupName(), is("Regular"));
        assertThat(regularUserGroup.getGroupName(), not("Admin"));
        assertThat(regularUserGroup.getUsers().size(), is(2));
        assertThat(regularUserGroup.getUsers().get(0).getUsername(), is("DuckDuckGo"));
        assertThat(regularUserGroup.getUsers().get(1).getUsername(), is("Google"));
    }
}

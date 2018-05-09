package main.dao;

import main.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalMatchers;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoTest {

    private User user;

    @Mock
    private UserDao userDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        user = new User();

        user.setUsername("DuckDuckGo");
        user.setMailAddress("duckduck@duckstad.nl");
        user.setId(1L);
    }

    @Test
    public void createUserTest() {
        userDao.createOrUpdate(user);

        when(userDao.findById(Matchers.eq(1L))).thenReturn(user);
        when(userDao.findById(AdditionalMatchers.not(Matchers.eq(1L)))).thenReturn(null);

        User userById = userDao.findById(1L);
        User emptyUserById = userDao.findById(2L);

        assertThat(userById.getUsername(), is("DuckDuckGo"));
        assertThat(userById.getMailAddress(), is("duckduck@duckstad.nl"));
        assertThat(userById.getId(), is(1L));
        assertThat(emptyUserById, is(nullValue()));
    }

    @Test
    public void findByUsernameTest() {
        userDao.createOrUpdate(user);

        when(userDao.findByUsername(Matchers.eq("DuckDuckGo"))).thenReturn(user);
        when(userDao.findByUsername(AdditionalMatchers.not(Matchers.eq("DuckDuckGo")))).thenReturn(null);

        User userByUsername = userDao.findByUsername("DuckDuckGo");
        User emptyUserByUsername = userDao.findByUsername("Google");

        assertThat(userByUsername.getUsername(), is("DuckDuckGo"));
        assertThat(userByUsername.getMailAddress(), is("duckduck@duckstad.nl"));
        assertThat(userByUsername.getId(), is(1L));
        assertThat(emptyUserByUsername, is(nullValue()));
    }
}

package util;

import main.utils.StringHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringHelperTest {

    private String licensePlate;

    @Before
    public void init() {
        this.licensePlate = "1-ZGN-23";
    }

    @Test
    public void replaceCharactersEmptyTest() {
        String expectedResult = "1ZGN23";
        String replacedResult = StringHelper.replace(licensePlate, "-", "");

        Assert.assertEquals(expectedResult, replacedResult);
    }

    @Test
    public void replaceCharactersTest() {
        String expectedResult = "1-***-23";
        String replacedResult = StringHelper.replace(licensePlate, "ZGN", "***");

        Assert.assertEquals(expectedResult, replacedResult);
    }

    @Test
    public void replaceCharactersWrongTest() {
        String expectedResult = "1*ZGN*23";
        String replacedResult = StringHelper.replace(licensePlate, "-", "**");

        Assert.assertNotEquals(expectedResult, replacedResult);
    }
}

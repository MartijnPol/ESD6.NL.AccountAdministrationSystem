package util;

import main.utils.StringHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringHelperTest {

    private String licensePlate;
    private String input;
    private String emptyInput;
    private String nullInput;

    @Before
    public void init() {
        this.licensePlate = "1-ZGN-23";
        this.input = "DuckDuckGo";
        this.emptyInput = "";
        this.nullInput = null;
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

    @Test
    public void emptyStringTest() {
        boolean expectedResult = true;
        boolean result = StringHelper.isEmpty(emptyInput);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void nullStringTest() {
        boolean expectedResult = true;
        boolean result = StringHelper.isEmpty(nullInput);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void notEmptyStringTest() {
        boolean expectedResult = false;
        boolean result = StringHelper.isEmpty(input);

        Assert.assertEquals(expectedResult, result);
    }
}

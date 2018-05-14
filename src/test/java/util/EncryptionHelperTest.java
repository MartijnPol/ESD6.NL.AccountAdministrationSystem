package util;

import main.utils.EncryptionHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EncryptionHelperTest {

    private String input;
    private String emptyInput;
    private String nullInput;

    @Before
    public void init() {
        this.input = "DuckDuckGo";
        this.emptyInput = "";
        this.nullInput = null;
    }

    @Test
    public void encryptDataTest() {
        String expectedResult = "1f571f93ab710c233fb1d052c4e8e15ccba29e6d8238cf4f8b1e77aabb4eaaa8";
        String unexpectedResult = "DuckDuckGo";

        String encryptedData = EncryptionHelper.encryptData(input);

        Assert.assertEquals(expectedResult, encryptedData);
        Assert.assertNotEquals(unexpectedResult, encryptedData);
    }

    @Test
    public void encryptReversibleTest() {
        String expectedResult = "DuckDuckGo";
        String unexpectedResult = "DuckDuckDuckGo";

        String encryptedDataReversible = EncryptionHelper.encryptReversible(input);
        String decryptedDataReversible = EncryptionHelper.decryptReversible(encryptedDataReversible);

        Assert.assertEquals(expectedResult, decryptedDataReversible);
        Assert.assertNotEquals(unexpectedResult, decryptedDataReversible);
    }
}

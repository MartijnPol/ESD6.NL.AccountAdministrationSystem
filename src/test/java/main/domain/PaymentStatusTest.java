package main.domain;

import main.domain.enums.PaymentStatus;
import org.junit.Assert;
import org.junit.Test;

public class PaymentStatusTest {

    @Test
    public void paymentStatusValueTest() {
        Assert.assertEquals(PaymentStatus.PAID, PaymentStatus.valueOf("PAID"));
        Assert.assertEquals(PaymentStatus.OPEN, PaymentStatus.valueOf("OPEN"));
        Assert.assertEquals(PaymentStatus.CANCELLED, PaymentStatus.valueOf("CANCELLED"));
    }

    @Test
    public void paymentStatusSecondValueTest() {
        PaymentStatus[] values = PaymentStatus.values();

        Assert.assertEquals(PaymentStatus.PAID, values[0]);
        Assert.assertEquals(PaymentStatus.OPEN, values[1]);
        Assert.assertEquals(PaymentStatus.CANCELLED, values[2]);
    }
}

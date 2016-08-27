package tw.com.mitake.sms;

import org.junit.Before;
import org.junit.Test;
import tw.com.mitake.sms.result.MitakeSmsSendResult;

import static org.junit.Assert.assertNotNull;

public class SmsOptionsTest {
    @Before
    public void setUp() {
        MitakeSms.init(System.getenv("MITAKE_SMS_USERNAME"), System.getenv("MITAKE_SMS_PASSWORD"));
    }

    @Test
    public void testSms() {
        SendOptions opts = new SendOptions().addDestination("phone1").addDestination("phone2").setMessage("Hello World");

        MitakeSmsSendResult result = MitakeSms.send(opts);

        System.out.println("result: " + result.toString());

        assertNotNull(result);
    }

    @Test
    public void testDeliveryTimeSms() {
        SendOptions opts = new SendOptions().addDestination(System.getenv("MITAKE_SMS_TO")).setMessage("Hello World")
                .setDeliveryTime(5000);

        MitakeSmsSendResult result = MitakeSms.send(opts);

        System.out.println("result: " + result.toString());

        assertNotNull(result);
    }

    @Test
    public void testExpiredTimeSms() {
        SendOptions opts = new SendOptions().addDestination(System.getenv("MITAKE_SMS_TO")).setMessage("Hello World, Expired Time")
                .setExpiredTime(10);

        MitakeSmsSendResult result = MitakeSms.send(opts);

        System.out.println("result: " + result.toString());

        assertNotNull(result);
    }
}
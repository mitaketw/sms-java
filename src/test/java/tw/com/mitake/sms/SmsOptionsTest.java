package tw.com.mitake.sms;

import org.junit.Before;
import org.junit.Test;
import tw.com.mitake.sms.listener.OnPostSendListener;
import tw.com.mitake.sms.listener.OnPreSendListener;
import tw.com.mitake.sms.result.MitakeSmsSendResult;

import static org.junit.Assert.assertNotNull;

public class SmsOptionsTest {
    @Before
    public void setUp() {
        MitakeSms.init(System.getenv("MITAKE_SMS_USERNAME"), System.getenv("MITAKE_SMS_PASSWORD"));
    }

    @Test
    public void testSms() {
        SendOptions opts = new SendOptions();

        opts.addDestination("phone1");
        opts.addDestination("phone2");
        opts.setMessage("Hello World");

        MitakeSmsSendResult result = MitakeSms.send(opts);

        System.out.println("result: " + result.toString());

        assertNotNull(result);
    }
}
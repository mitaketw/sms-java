package tw.com.mitake.sms;

import org.junit.Before;
import org.junit.Test;
import tw.com.mitake.sms.listener.OnPostSendListener;
import tw.com.mitake.sms.listener.OnPreSendListener;
import tw.com.mitake.sms.result.MitakeSmsSendResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class SmsMultiBodyTest {
    @Before
    public void setUp() {
        MitakeSms.init(System.getenv("MITAKE_SMS_USERNAME"), System.getenv("MITAKE_SMS_PASSWORD"));
    }

    @Test
    public void testSms1() {
        List<SendOptions> optsList = new ArrayList<SendOptions>();

        SendOptions opts = new SendOptions().addDestination("phone1").addDestination("phone2")
                .setMessage("this 簡訊 & 內容 message 1 + 2 羣");

        optsList.add(opts);

        MitakeSmsSendResult result = MitakeSms.send(optsList);

        System.out.println("result: " + result.toString());

        assertNotNull(result);
    }

    @Test
    public void testSms2() {
        List<SendOptions> optsList = new ArrayList<SendOptions>();

        SendOptions opts = new SendOptions().addDestination("phone1").setMessage("this 簡訊 & 內容 message 1 + 2 羣");

        optsList.add(opts);

        opts = new SendOptions().addDestination("phone2").setMessage("Hello World");

        optsList.add(opts);

        MitakeSmsSendResult result = MitakeSms.send(optsList);

        System.out.println("result: " + result.toString());

        assertNotNull(result);
    }
}
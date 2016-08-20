package tw.com.mitake.sms;

import org.junit.Before;
import org.junit.Test;
import tw.com.mitake.sms.result.MitakeSmsQueryAccountPointResult;
import tw.com.mitake.sms.result.MitakeSmsQueryMessageStatusResult;

import static org.junit.Assert.assertNotNull;

public class SmsQueryTest {
    @Before
    public void setUp() {
        MitakeSms.init(System.getenv("MITAKE_SMS_USERNAME"), System.getenv("MITAKE_SMS_PASSWORD"));
    }

    @Test
    public void testGetAccountPoint() {
        MitakeSmsQueryAccountPointResult result = MitakeSms.queryAccountPoint();

        System.out.println("result: " + result.toString());

        assertNotNull(result);
    }

    @Test
    public void testGetMessageStatus(){
        MitakeSmsQueryMessageStatusResult result = MitakeSms.queryMessageStatus("messageid");

        System.out.println("result: " + result.toString());

        assertNotNull(result);
    }
}
package tw.com.mitake.sms;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tw.com.mitake.sms.result.MitakeSmsQueryAccountPointResult;
import tw.com.mitake.sms.result.MitakeSmsQueryMessageStatusResult;

import static org.junit.Assert.assertNotNull;

public class SmsQueryTest {
    private static final Logger LOG = LoggerFactory.getLogger(SmsQueryTest.class);

    @Before
    public void setUp() {
        MitakeSms.init(System.getenv("MITAKE_SMS_USERNAME"), System.getenv("MITAKE_SMS_PASSWORD"));
    }

    @Test
    public void testGetAccountPoint() {
        // because server-side limits 1 req/s
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
        }

        MitakeSmsQueryAccountPointResult result = MitakeSms.queryAccountPoint();

        assertNotNull(result);
    }

    @Test
    public void testGetMessageStatus() {
        // because server-side limits 1 req/s
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
        }

        MitakeSmsQueryMessageStatusResult result = MitakeSms.queryMessageStatus("messageid");

        assertNotNull(result);
    }
}
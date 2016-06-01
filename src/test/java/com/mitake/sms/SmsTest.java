package com.mitake.sms;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class SmsTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SmsTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(SmsTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        MitakeSms.init(System.getenv("MITAKE_SMS_USERNAME"), System.getenv("MITAKE_SMS_PASSWORD"));

        MitakeSmsResult result = MitakeSms.send(System.getenv("MITAKE_SMS_TO"), "test message");

        assertNotNull(result);
    }
}

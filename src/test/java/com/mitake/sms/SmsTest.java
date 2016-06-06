package com.mitake.sms;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SmsTest {
    @Before
    public void setUp() {
        MitakeSms.init(System.getenv("MITAKE_SMS_USERNAME"), System.getenv("MITAKE_SMS_PASSWORD"));
    }

    @Test
    public void testSms() {
        MitakeSmsResult result = MitakeSms.send(System.getenv("MITAKE_SMS_TO"), "this 簡訊 & 內容 message 1 + 2 羣");

        System.out.println("result: " + result.toString());

        assertNotNull(result);
    }
}
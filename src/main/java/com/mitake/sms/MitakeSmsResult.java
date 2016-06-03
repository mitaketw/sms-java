package com.mitake.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MitakeSmsResult {
    private static final Logger LOG = LoggerFactory.getLogger(MitakeSmsSender.class);
    private static final Pattern PATTERN = Pattern.compile("(\\[(\\d+)\\]\\r\\n(\\w+)=(\\w+)\\r\\n(\\w+)=(\\w+))+\\r\\n(AccountPoint=(\\d+))?");

    private ArrayList<SmsResult> results;

    private int accountPoint;

    public MitakeSmsResult(String response, String to) {
        parseResult(response, to);
    }

    private void parseResult(String response, String to) {
        results = new ArrayList<SmsResult>();

        Matcher matcher = PATTERN.matcher(response);

        while (matcher.find()) {
            SmsResult result = new SmsResult();

            String group2Value = matcher.group(2);
            String group3Value = matcher.group(3);
            String group4Value = matcher.group(4);
            String group5Value = matcher.group(5);
            String group6Value = matcher.group(6);
            String group7Value = matcher.group(7);
            String group8Value = matcher.group(8);

            if (group2Value.length() != 10) {
                result.phoneNumber = to;
            } else {
                result.phoneNumber = group2Value;
            }

            if (group3Value.equals("msgid")) {
                result.messageId = group4Value;
            }

            if (group5Value.equals("statuscode")) {
                result.statusCode = StatusCode.findByKey(group6Value);
            }

            results.add(result);

            if (group7Value != null && group8Value != null) {
                accountPoint = Integer.valueOf(group8Value);
            }
        }
    }

    public ArrayList<SmsResult> getResults() {
        return results;
    }

    public int getAccountPoint() {
        return accountPoint;
    }

    public class SmsResult {
        private String phoneNumber;
        private String messageId;
        private StatusCode statusCode;

        @Override
        public String toString() {
            return "phoneNumber: " + phoneNumber + ", messageId: " + messageId + ", statusCode: " + statusCode.getMessage();
        }
    }
}
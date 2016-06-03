package com.mitake.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MitakeSmsResult {
    private static final Logger LOG = LoggerFactory.getLogger(MitakeSmsSender.class);
    private final static Pattern PATTERN = Pattern.compile("\\[(\\d+)\\]");

    private HashMap<String, String> resultMap = new HashMap<String, String>();

    /**
     * 簡訊序號。為SmGateway所編定的簡訊序號。發送後進行查詢或狀態回報，均以此作為Key值。若該筆簡訊發送失敗，則不會有此欄位。
     */
    private String msgId;



    /**
     * 剩餘點數。本次發送後的剩餘額度。
     */
    private String overage;

    /**
     * 是否為重複發送的簡訊。Y代表重複發送，其他值或無此欄位代表非重複發送。
     */
    private String isDuplicate;


    public MitakeSmsResult(ArrayList<String> lines) {
        parseResponse(lines);
    }

    private void parseResponse(ArrayList<String> lines) {
        for (String line : lines) {
            Matcher matcher = PATTERN.matcher(line);

            if (matcher.find()) {
                LOG.debug(matcher.group(1));
            } else {
                LOG.debug(line);
            }
        }
    }

    public String getMsgId() {
        return msgId;
    }

    public String getOverage() {
        return overage;
    }

    public String getIsDuplicate() {
        return isDuplicate;
    }

    public MitakeSmsResult setMsgId(String msgId) {
        this.msgId = msgId;
        return this;
    }

    public MitakeSmsResult setOverage(String overage) {
        this.overage = overage;
        return this;
    }

    public MitakeSmsResult setIsDuplicate(String isDuplicate) {
        this.isDuplicate = isDuplicate;
        return this;
    }
}
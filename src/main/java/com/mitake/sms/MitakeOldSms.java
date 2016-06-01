package com.mitake.sms;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class MitakeOldSms {
    private static final Logger LOG = LoggerFactory.getLogger(MitakeOldSms.class);

    /* Query */
    private static final String QUERY_AMPERSAND = "&";

    /* Query Keys */
    private static final String KEY_USER_NAME = "username";
    private static final String KEY_CODE = "password";
    private static final String KEY_MESSAGE = "smbody";
    private static final String KEY_DEST_NUMBER = "dstaddr";
    private static final String KEY_ENCODING = "encoding";
    private static final String KEY_SCHEDULE = "dlvtime";
    private static final String KEY_TIME_TO_LIVE = "vldtime";
    private static final String KEY_RESPONSE = "response";
    private static final String KEY_DEST_NAME = "DestName";
    private static final String KEY_CLIENT_ID = "ClientID";

    /* Query Values */
    public static final String ENCODING_BIG5 = "Big5";
    public static final String ENCODING_UTF8 = "UTF8";

    private HashMap<String, String> map;

    /**
     * Create buildMap method.
     */
    public MitakeOldSms() {
        map = new HashMap<String, String>();

        // Set default
        map.put(KEY_ENCODING, ENCODING_UTF8);
    }

    /**
     * 使用者帳號, 必要欄位, 最大長度:20。
     * <p>
     * <p>
     * 使用者帳號。SmGateway資料庫表格SMUser中需有此使用者，且狀態為啟用。
     * </p>
     *
     * @param userName sender user id
     * @return builder
     */
    public MitakeOldSms setUserName(String userName) throws IllegalArgumentException {
        if (checkValueLength(userName, 20)) {
            map.put(KEY_USER_NAME, userName);
        } else {
            throw new IllegalArgumentException("User name max length is 20.");
        }

        return this;
    }

    /**
     * 使用者密碼, 必要欄位, 最大長度:24。
     *
     * @param password sender user password
     * @return builder
     */
    public MitakeOldSms setPassword(String password) throws IllegalArgumentException {
        if (checkValueLength(password, 24)) {
            map.put(KEY_CODE, password);
        } else {
            throw new IllegalArgumentException("Password max length is 24.");
        }

        return this;
    }

    /**
     * 簡訊內容, 必要欄位, 最大長度:256。
     * <p>
     * <p>
     * 簡訊內容。必須為BIG-5編碼，長度70個中文字或是160個英數字。
     * 若有換行的需求，請填入ASCII Code 6代表換行。
     * 為避免訊息中有&電文分隔符號，請將此欄位進行url encode。
     * url encode時，空白可用%20或維持空白即可，請勿將空白encode為+號。
     * 若對url encode有任何的相容性疑慮，建議使用%FF的方式將參數內容全部編碼即可。
     * </p>
     *
     * @param message message body
     * @return builder
     */
    public MitakeOldSms setMessage(String message) throws IllegalArgumentException {
        if (checkValueLength(message, 256)) {
            String encoding = map.get(KEY_ENCODING);

            try {
                message = URLEncoder.encode(message, encoding);
                message = message.replace("+", "%20");
            } catch (Exception e) {
                LOG.error(e.getMessage());
            }

            map.put(KEY_MESSAGE, message);
        } else {
            throw new IllegalArgumentException("Message max length is 256.");
        }

        return this;
    }

    /**
     * 受訊方手機號碼, 必要欄位, 最大長度:20。
     * <p>
     * <p>
     * 受訊方手機號碼。請填入09帶頭的手機號碼。
     * </p>
     *
     * @param number destination number
     * @return builder
     */
    public MitakeOldSms setDestNumber(String number) throws IllegalArgumentException {
        //if (checkValueLength(number, 20)) {
        map.put(KEY_DEST_NUMBER, number);
        //} else {
        //	throw new IllegalArgumentException("Destination number max length is 20.");
        //}

        return this;
    }

    /**
     * 收訊人名稱, 最大長度:36。
     * <p>
     * <p>
     * 收訊人名稱。若其他系統需要與簡訊資料進行系統整合，此欄位可填入來源系統所產生的Key值，以對應回來源資料庫。
     * </p>
     *
     * @param name destination name
     * @return builder
     */
    public MitakeOldSms setDestName(String name) throws IllegalArgumentException {
        if (checkValueLength(name, 36)) {
            map.put(KEY_DEST_NAME, name);
        } else {
            throw new IllegalArgumentException("Destination name max length is 36.");
        }

        return this;
    }

    /**
     * 編碼方式, 最大長度:10。
     * <p>
     * <p>
     * 簡訊內容 及 收訊人名稱 的編碼方式(預設值為Big5)：
     * <p>
     * <ul>
     * <li>Big5
     * <li>Unicode
     * <li>utf-16le
     * <li>utf-16be
     * <li>UTF8
     * </ul>
     * <p>
     * 其中Unicode即為utf-16le
     * <p>
     * <ul>
     * <li>注意事項1：雖然三竹支援客戶指定編碼方式，但這是用於將資料轉成Big5的格式。若送來日文、韓文、簡體中文..等的unicode，保證送出去一定不是所要的結果。
     * <li>注意事項2：編碼的轉換是透過Window的CodePage做轉換。不保證每一個看起來像繁體中文的Unicode都能完美的對應到Big5的內碼，請測試OK後再上線，轉換結果若有缺陷，只能敬請見諒。
     * </ul>
     * </p>
     *
     * @param encoding
     * @return builder
     */
    public MitakeOldSms setEncoding(String encoding) throws IllegalArgumentException {
        if (checkValueLength(encoding, 10)) {
            map.put(KEY_ENCODING, encoding);
        } else {
            throw new IllegalArgumentException("Encoding max length is 10.");
        }

        return this;
    }

    /**
     * 簡訊預約時間, 最大長度:10。
     * <p>
     * <p>
     * 簡訊預約時間。也就是希望簡訊何時送達手機，格式為YYYY-MM-DD HH:NN:SS或YYYYMMDDHHNNSS，或是整數值代表幾秒後傳送。
     * 若預約時間大於系統時間，則為預約簡訊，預約時間離目前時間若預約時間已過或為空白則為即時簡訊。
     * <p>
     * 即時簡訊為儘早送出，若受到MsgType宵禁欄位的限制，就不一定是立刻送出。
     * </p>
     *
     * @param time
     * @return builder
     */
    public MitakeOldSms setSchedule(String time) throws IllegalArgumentException {
        if (checkValueLength(time, 10)) {
            map.put(KEY_SCHEDULE, time);
        } else {
            throw new IllegalArgumentException("Schedule max length is 10.");
        }

        return this;
    }

    /**
     * 簡訊有效期限, 最大長度:14。
     * <p>
     * <p>
     * 簡訊有效期限。格式為YYYY-MM-DD HH:NN:SS或YYYYMMDDHHNNSS，或是整數值代表傳送後幾秒後內有效。
     * <p>
     * 請勿超過大哥大業者預設之24小時期限，以避免業者不回覆簡訊狀態。
     * </p>
     *
     * @param time
     * @return builder
     */
    public MitakeOldSms setTimeToLive(String time) throws IllegalArgumentException {
        if (checkValueLength(time, 14)) {
            map.put(KEY_TIME_TO_LIVE, time);
        } else {
            throw new IllegalArgumentException("Time-To-Live max length is 14.");
        }

        return this;
    }

    /**
     * 狀態回報網址, 最大長度:256。
     * <p>
     * <p>
     * 狀態回報網址。請參考狀態回報的說明。
     * </p>
     *
     * @return builder
     */
    public MitakeOldSms setResponseURL(String url) throws IllegalArgumentException {
        if (checkValueLength(url, 256)) {
            map.put(KEY_RESPONSE, url);
        } else {
            throw new IllegalArgumentException("Response max length is 256.");
        }

        return this;
    }

    /**
     * 客戶簡訊ID, 最大長度:36。
     * <p>
     * <p>
     * 客戶簡訊ID。用於避免重複發送，若有提供此參數，
     * 則會判斷該簡訊ID是否曾經發送，若曾發送過，
     * 則直接回覆之前發送的回覆值，並加上Duplicate=Y。
     * <p>
     * 這個檢查機制只留存12小時內的資料，12小時候後重複的ClientID”可能”會被當成新簡訊。
     * 此外，ClientID必須維持唯一性，而非只在12小時內唯一，建議可使用GUID來保證其唯一性。
     * </p>
     *
     * @return builder
     */
    public MitakeOldSms setClientID(String id) {
        if (checkValueLength(id, 256)) {
            map.put(KEY_CLIENT_ID, id);
        } else {
            throw new IllegalArgumentException("Client ID max length is 10.");
        }

        return this;
    }

    public String buildQuery() throws IllegalArgumentException {
        Check result = checkFields();
        String query = null;

        if (result.isSuccess) {
            StringBuilder sb = new StringBuilder();

            for (Map.Entry<String, String> set : map.entrySet()) {
                sb.append(set.getKey())
                        .append("=")
                        .append(set.getValue())
                        .append(QUERY_AMPERSAND);
            }

            query = sb.toString();
            query = StringUtils.removeEnd(query, QUERY_AMPERSAND);
        } else {
            throw new IllegalArgumentException(result.errorMsg);
        }

        return query;
    }

    private boolean checkValueLength(String value, int length) {
        boolean isSuccess;

        if (null != value && value.length() <= length) {
            isSuccess = true;
        } else {
            isSuccess = false;
        }

        return isSuccess;
    }

    private Check checkFields() {
        Check result = new Check();

        // Check user name
        String userNmae = map.get(KEY_USER_NAME);
        if (StringUtils.isEmpty(userNmae)) {
            result.isSuccess = false;
            result.errorMsg = "SmsBuilder no set user name.";
            return result;
        }

        // Check password
        String password = map.get(KEY_CODE);
        if (StringUtils.isEmpty(password)) {
            result.isSuccess = false;
            result.errorMsg = "SmsBuilder no set password.";
            return result;
        }

        // Check message
        String message = map.get(KEY_MESSAGE);
        if (StringUtils.isEmpty(message)) {
            result.isSuccess = false;
            result.errorMsg = "SmsBuilder no set message.";
            return result;
        }

        // Check dest number
        String destNumber = map.get(KEY_DEST_NUMBER);
        if (StringUtils.isEmpty(destNumber)) {
            result.isSuccess = false;
            result.errorMsg = "SmsBuilder no set dest number.";
            return result;
        }

        // Check success
        result.isSuccess = true;
        result.errorMsg = null;

        return result;
    }

    private class Check {
        boolean isSuccess;
        String errorMsg;
    }
}
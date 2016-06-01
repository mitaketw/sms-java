package com.mitake.sms;

import java.util.regex.Pattern;

public class MitakeSmsResult {
    private static final String STATUS_CODE_OK = "1";

    /**
     * 簡訊序號。為SmGateway所編定的簡訊序號。發送後進行查詢或狀態回報，均以此作為Key值。若該筆簡訊發送失敗，則不會有此欄位。
     */
    private String msgId;

    /**
     * 發送狀態。請參考附錄二的說明。
     * <ul>
     * <li>* - 系統發生錯誤，請聯絡三竹資訊窗口人員
     * <li>a - 簡訊發送功能暫時停止服務，請稍候再試
     * <li>b - 簡訊發送功能暫時停止服務，請稍候再試
     * <li>c - 請輸入帳號
     * <li>d - 請輸入密碼
     * <li>e - 帳號、密碼錯誤
     * <li>f - 帳號已過期
     * <li>h - 帳號已被停用
     * <li>k - 無效的連線位址
     * <li>m - 必須變更密碼，在變更密碼前，無法使用簡訊發送服務
     * <li>n - 密碼已逾期，在變更密碼前，將無法使用簡訊發送服務
     * <li>p - 沒有權限使用外部Http程式
     * <li>r - 系統暫停服務，請稍後再試
     * <li>s - 帳務處理失敗，無法發送簡訊
     * <li>t - 簡訊已過期
     * <li>u - 簡訊內容不得為空白
     * <li>v - 無效的手機號碼
     * <li>0 - 預約傳送中
     * <li>1 - 已送達業者
     * <li>2 - 已送達業者
     * <li>3 - 已送達業者
     * <li>4 - 已送達手機
     * <li>5 - 內容有錯誤
     * <li>6 - 門號有錯誤
     * <li>7 - 簡訊已停用
     * <li>8 - 逾時無送達
     * <li>9 - 預約已取消
     * </ul>
     */

    /**
     * 剩餘點數。本次發送後的剩餘額度。
     */
    private String overage;

    /**
     * 是否為重複發送的簡訊。Y代表重複發送，其他值或無此欄位代表非重複發送。
     */
    private String isDuplicate;

    private final static Pattern pattern = Pattern.compile("\\[([0-9+]+)\\],msgid=([0-9a-zA-Z]+),statuscode=([0-9a-zA-Z]+)");

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
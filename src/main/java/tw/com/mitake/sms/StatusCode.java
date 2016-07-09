package tw.com.mitake.sms;

public enum StatusCode {
    CODE_STAR("*", "系統發生錯誤，請聯絡三竹資訊窗口人員"),
    CODE_A("a", "簡訊發送功能暫時停止服務，請稍候再試"),
    CODE_B("b", "簡訊發送功能暫時停止服務，請稍候再試"),
    CODE_C("c", "請輸入帳號"),
    CODE_D("d", "請輸入密碼"),
    CODE_E("e", "帳號、密碼錯誤"),
    CODE_F("f", "帳號已過期"),
    CODE_H("h", "帳號已被停用"),
    CODE_K("k", "無效的連線位址"),
    CODE_M("m", "必須變更密碼，在變更密碼前，無法使用簡訊發送服務"),
    CODE_N("n", "密碼已逾期，在變更密碼前，將無法使用簡訊發送服務"),
    CODE_P("p", "沒有權限使用外部Http程式"),
    CODE_R("r", "系統暫停服務，請稍後再試"),
    CODE_S("s", "帳務處理失敗，無法發送簡訊"),
    CODE_T("t", "簡訊已過期"),
    CODE_U("u", "簡訊內容不得為空白"),
    CODE_V("v", "無效的手機號碼"),
    CODE_0("0", "預約傳送中"),
    CODE_1("1", "已送達業者"),
    CODE_2("2", "已送達業者"),
    CODE_3("3", "已送達業者"),
    CODE_4("4", "已送達手機"),
    CODE_5("5", "內容有錯誤"),
    CODE_6("6", "門號有錯誤"),
    CODE_7("7", "簡訊已停用"),
    CODE_8("8", "逾時無送達"),
    CODE_9("9", "預約已取消");

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    StatusCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static StatusCode findByKey(String code) {
        for (StatusCode e : values()) {
            if (e.code.equals(code)) {
                return e;
            }
        }

        return null;
    }
}

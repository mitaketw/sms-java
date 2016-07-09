package tw.com.mitake.sms;

public enum ConnectionResult {
    OK(1), FAIL(2), EXCEPTION(3);

    private int code;

    public int getCode() {
        return code;
    }

    ConnectionResult(int code) {
        this.code = code;
    }

    public static ConnectionResult findByKey(int code) {
        for (ConnectionResult e : values()) {
            if (e.code == code) {
                return e;
            }
        }

        return null;
    }

}

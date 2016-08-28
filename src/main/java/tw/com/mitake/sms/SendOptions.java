package tw.com.mitake.sms;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class SendOptions {
    private ArrayList<String> destinations = new ArrayList<String>();
    private String message;
    private Calendar deliveryTime;
    private Calendar expiredTime;

    public SendOptions addDestination(String... destinations) {
        Collections.addAll(this.destinations, destinations);

        return this;
    }

    public ArrayList<String> getDestinations() {
        return destinations;
    }

    public SendOptions addDestination(ArrayList<String> destinations) {
        this.destinations.addAll(destinations);

        return this;
    }

    public SendOptions setMessage(String message) {
        this.message = message;

        return this;
    }

    public String getMessage() {
        return message;
    }

    public SendOptions setDeliveryTime(Calendar deliveryTime) {
        this.deliveryTime = deliveryTime;

        return this;
    }

    public Calendar getDeliveryTime() {
        return deliveryTime;
    }

    public SendOptions setDeliveryTime(int seconds) {
        deliveryTime = Calendar.getInstance();

        deliveryTime.add(Calendar.SECOND, seconds);

        return this;
    }

    public SendOptions setExpiredTime(Calendar expiredTime) {
        this.expiredTime = expiredTime;

        return this;
    }

    public Calendar getExpiredTime() {
        return expiredTime;
    }

    public SendOptions setExpiredTime(int seconds) {
        expiredTime = Calendar.getInstance();

        expiredTime.add(Calendar.SECOND, seconds);

        return this;
    }
}

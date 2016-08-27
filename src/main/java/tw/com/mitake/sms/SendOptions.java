package tw.com.mitake.sms;

import java.util.ArrayList;
import java.util.Calendar;

public class SendOptions {
    private ArrayList<String> destinations = new ArrayList<String>();
    private String message;
    private Calendar deliveryTime;

    public SendOptions addDestination(String destination) {
        destinations.add(destination);

        return this;
    }

    public ArrayList<String> getDestinations() {
        return destinations;
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
}

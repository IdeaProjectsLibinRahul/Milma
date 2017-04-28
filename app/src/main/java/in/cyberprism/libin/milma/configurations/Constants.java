package in.cyberprism.libin.milma.configurations;

import com.google.gson.annotations.SerializedName;

/**
 * Created by libin on 28/04/17.
 */

public class Constants {
    public enum Status {
        @SerializedName("0")
        ERROR,
        @SerializedName("1")
        SUCCESS,
        @SerializedName("2")
        INVALID_USER;
    }

    public enum Type {
        @SerializedName("1")
        DEALER,
        @SerializedName("2")
        SELLER;
    }

    public enum MessageType {
        @SerializedName("0")
        ERROR(0),
        @SerializedName("1")
        SUCCESS(1),
        @SerializedName("2")
        TIME_OUT(2);

        private int value;

        MessageType(int value) {
            this.value = value;
        }
    }

    public enum GroupBy {
        @SerializedName("0")
        QUANTITY,
        @SerializedName("1")
        CATEGORY;
    }
}

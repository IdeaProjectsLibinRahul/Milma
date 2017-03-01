package in.cyberprism.libin.milma.events;

/**
 * Created by libin on 28/02/17.
 */

public class ItemSelectEvent {
    private String key;
    private int count;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

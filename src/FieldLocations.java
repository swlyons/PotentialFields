import java.io.Serializable;
import java.util.Map;

/**
 * Created by devonkinghorn on 5/22/17.
 */
public class FieldLocations implements Serializable {
    int time;
    Map<Integer, Location> fields;

    public FieldLocations(int time, Map<Integer, Location> fields) {
        this.time = time;
        this.fields = fields;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Map<Integer, Location> getFields() {
        return fields;
    }

    public void setFields(Map<Integer, Location> fields) {
        this.fields = fields;
    }
}

import java.io.Serializable;
import java.util.List;

/**
 * Created by devonkinghorn on 5/22/17.
 */
public class Location implements Serializable {
    List<List<Double>>  corners;
    List<Double> orientation;
    List<Double> center;
}

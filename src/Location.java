import java.io.Serializable;
import java.util.List;

/**
 * Created by devonkinghorn on 5/22/17.
 */
public class Location implements Serializable {
    List<List<Double>>  corners;
    List<Double> orientation;
    List<Double> center;

    public List<List<Double>> getCorners() {
        return corners;
    }

    public void setCorners(List<List<Double>> corners) {
        this.corners = corners;
    }

    public List<Double> getOrientation() {
        return orientation;
    }

    public void setOrientation(List<Double> orientation) {
        this.orientation = orientation;
    }

    public List<Double> getCenter() {
        return center;
    }

    public void setCenter(List<Double> center) {
        this.center = center;
    }

    // TODO: make sure this is the right x we want
    public double getX() {
        return center.get(0);
    }

    public double getY() {
        return center.get(1);
    }

    // TODO: make sure the angle is what we want
    public double getAngle() {
        return Math.atan2(orientation.get(1), orientation.get(0));
    }
}

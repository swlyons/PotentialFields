/**
 * Created by swlyons on 5/15/2017.
 */

public class RepulsionField implements PotentialField {

    private double x;
    private double y;
    private double r;
    private double s;
    private double alpha;

    public RepulsionField(double x, double y, double r, double repulsion) {
        this.x = x;
        this.y = y;
        this.r = r * r;
        s = 10;
        alpha = repulsion;
    }

    @Override
    public Vector getAttractionVector(double x, double y) {
        Vector v;
        double d = Math.sqrt((this.x - x) * (this.x - x) + (this.y - y) * (this.x - x));
        double theta = Math.atan2(this.y - y, this.x - x);

        if (d < r) {
            //it's inside     possibly replace alpha with -infinity?
            v = new Vector(alpha * Math.cos(theta), alpha * Math.sin(theta));
        } else {
            if (d < s + r) {
                //it's far away α(d − r) cos(θ)
                v = new Vector(0, 0);
            } else {
                //it's between
                v = new Vector(-alpha * (s + r - d) * Math.cos(theta), -alpha * (s + r - d) * Math.sin(theta));
            }
        }

        return v;
    }
}

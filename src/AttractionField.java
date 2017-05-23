/**
 * Created by swlyons on 5/15/2017.
 */

public class AttractionField implements PotentialField {

    private double x;
    private double y;
    private double r;
    private double s;
    private double alpha;

    public AttractionField(double x, double y, double r, double attractionStrength, double spread) {
        this.x = x;
        this.y = y;
        this.r = r;
        s = spread;
        alpha = attractionStrength;
    }

    @Override
    public Vector getAttractionVector(double x, double y) {
        Vector v;
        double d = Math.sqrt((this.x - x) * (this.x - x) + (this.y - y) * (this.y - y));
        double theta = Math.atan2(this.y - y, this.x - x);
        System.out.println((this.x - x) * (this.x - x) );
        if (d < r) {
            //it's inside
            v = new Vector(0, 0);
        } else {
            if (d < s + r) {
                //it's far away α(d − r) cos(θ)
                v = new Vector(alpha * Math.cos(theta), alpha * Math.sin(theta));
            } else {
                //it's between
                v = new Vector(alpha * (d - r) * Math.cos(theta), alpha * (d - r) * Math.sin(theta));
            }
        }

        return v;
    }
}

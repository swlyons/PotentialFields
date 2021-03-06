/**
 * Created by swlyons on 5/15/2017.
 */

public class RepulsionField implements PotentialField {

    private double x;
    private double y;
    private double r;
    private double s;
    private double alpha;

    public RepulsionField(double x, double y, double r, double repulsionStrength, double spread) {
        this.x = x;
        this.y = y;
        this.r = r;
        s = spread;
        alpha = repulsionStrength;
    }

    @Override
    public Vector getAttractionVector(double x, double y) {
        Vector v;
        double d = Math.sqrt((this.x - x) * (this.x - x) + (this.y - y) * (this.y - y));
        double theta = Math.atan2(this.y - y, this.x - x);


        if (d < r) {
            //it's inside     possibly replace alpha with -infinity?
            v = new Vector(-10, -10);
//            System.out.println("Yoz"+-alpha * Math.cos(theta));
        } else {
            if (d > s + r) {
                //it's far away α(d − r) cos(θ)
                v = new Vector(0, 0);
//                System.out.println("Yoa"+-0);
            } else {
                //it's between
                v = new Vector(-alpha * (s + r - d) * Math.cos(theta), -alpha * (s + r - d) * Math.sin(theta));
                System.out.println(v.getX());
                System.out.println(v.getY());
                //                System.out.println("Yo"+-alpha * (s + r - d) * Math.cos(theta));
            }
        }

        return v;
    }
    public double getX(){return x;}
    public double getY(){return y;}

}

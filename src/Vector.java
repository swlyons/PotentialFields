/**
 * Created by swlyons on 5/15/2017.
 */
public class Vector {
    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x=x;
        this.y=y;
    }

    public void addVector(Vector v){
        this.x+=v.getX();
        this.y+=v.getY();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getMagnitude(){
        return Math.sqrt(x*x + y*y);
    }

}

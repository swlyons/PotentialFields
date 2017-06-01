import java.util.Date;
import java.util.Random;

/**
 * Created by swlyons on 5/15/2017.
 */

public class RandomField implements PotentialField {
    private double alpha;
    private int initialRandomSeed;

    public RandomField(double strength) {
        this.alpha = strength;
        initialRandomSeed = (int) (new Date()).getTime();
    }


    @Override
    public Vector getAttractionVector(double x, double y) {
        Random r = new Random((new Random((int) x)).nextInt() + (new Random(initialRandomSeed + (int) y)).nextInt());
        return new Vector(r.nextDouble()*2*alpha-alpha, r.nextDouble()*2*alpha-alpha);
    }
    public double getX(){return 0;}
    public double getY(){return 0;}

}

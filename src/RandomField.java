import java.util.Date;
import java.util.Random;

/**
 * Created by swlyons on 5/15/2017.
 */

public class RandomField implements PotentialField {
    private double x;
    private double y;
    private double r;
    private double alpha;
    private int initialRandomSeed;

    public RandomField(double x, double y, double r, double strength) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.alpha = strength;
        initialRandomSeed = (int) (new Date()).getTime();
    }


    @Override
    public Vector getAttractionVector(double x, double y) {
        Random r = new Random((new Random((int) x)).nextInt() + (new Random(initialRandomSeed + (int) y)).nextInt());
        return new Vector(r.nextDouble()*alpha, r.nextDouble()*alpha);
    }
}

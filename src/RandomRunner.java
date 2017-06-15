import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by devonkinghorn on 6/15/17.
 */


public class RandomRunner {
    int maxSpeed;
    int maxTime;
    int iterations;
    String outputFile;
    Communicator c;

    public RandomRunner(int maxSpeed, int maxTime, int iterations, String outputFile, Communicator c) {
        this.maxSpeed = maxSpeed;
        this.maxTime = maxTime;
        this.iterations = iterations;
        this.outputFile = outputFile;
        this.c = c;
    }

    public void run() throws InterruptedException {
        ArrayList<TrialData> trials = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < iterations; i++ ){
            goToCenter();
            Location original = getLocation();
            int leftPower = random.nextInt(maxSpeed + 1);
            int rightPower = random.nextInt(maxSpeed + 1);
            int time = random.nextInt(maxTime) ;
            c.sendMessage(String.format("speed %1$s %2$s", leftPower, rightPower));
            Thread.sleep(time);
            c.sendMessage("speed 0 0");
            Location dest = getLocation();
            trials.add(new TrialData(original, dest.getX(), dest.getY(), leftPower, rightPower, time));
        }
        String result = new Gson().toJson(trials);
        System.out.println(result);
    }

    private void goToCenter() {

    }

    public Location getLocation() {
        // get the location,
        //do,
        //   sleep x time
        //   get new location
        //   while new location time == original time or the x and y values are very similar
        return null;
    }
}

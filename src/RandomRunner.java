import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    public void run() throws Exception {
        ArrayList<TrialData> trials = new ArrayList<TrialData>();
        Random random = new Random((int) (new Date()).getTime());
        System.out.println("A");
        for (int i = 0; i < iterations; i++ ){
            System.out.println("B"+i);
            goToCenter();
            Location original = getLocation();

            int turn = random.nextInt(maxSpeed);
            if(random.nextBoolean()){
                turn=turn*-1;
            }

//            int leftPower = random.nextInt(maxSpeed + 1);
//            if(random.nextBoolean()){
//                leftPower=leftPower*-1;
//            }
//            int rightPower = random.nextInt(maxSpeed + 1);
//            if(random.nextBoolean()){
//                rightPower=rightPower*-1;
//            }

            int leftPower=maxSpeed;
            int rightPower=maxSpeed;

            if(turn<0){
                leftPower = maxSpeed + turn;
            }else{
                rightPower = maxSpeed - turn;
            }

//            int time = maxTime;
            int time = random.nextInt(maxTime) ;
            c.sendMessage(String.format("speed %1$s %2$s", leftPower, rightPower));
//            System.out.println("time"+time);
            Thread.sleep(time);
            c.sendMessage("power 0 0");
            Location dest = getLocation();
            trials.add(new TrialData(original, dest.getX(), dest.getY(), turn, time));
        }
        System.out.println("Z");
        String result = new Gson().toJson(trials);
        FileWriter fw = new FileWriter(outputFile);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(result);
        bw.flush();
        bw.close();
        System.out.println(result);
    }

    private void goToCenter() {
        List<PotentialField> fields = new ArrayList<PotentialField>();
        fields.add(new AttractionField(930, 450));
        PathTransversal traverser = new PathTransversal(c);
        traverser.transversePath(fields);

    }

    public Location getLocation() throws Exception{
        Location robotLocOld = c.getRobotPosition();
        Location robotLocCurrent = robotLocOld;
        do{
            robotLocOld=robotLocCurrent;
            Thread.sleep(750);
            robotLocCurrent = c.getRobotPosition();
        }while(Math.abs(robotLocOld.getX()-robotLocCurrent.getX())>5&&Math.abs(robotLocOld.getY()-robotLocCurrent.getY())>5);
        return robotLocCurrent;
        // get the location,
        //do,
        //   sleep x time
        //   get new location
        //   while new location time == original time or the x and y values are very similar
//        return null;
    }
}

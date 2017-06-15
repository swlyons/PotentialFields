/**
 * Created by devonkinghorn on 6/15/17.
 */
public class TrialData {
    Location robotLoc;
    double destX;
    double destY;
    int leftPower;
    int rightPower;
    int time;

    public TrialData(Location robotLoc, double destX, double destY, int leftPower, int rightPower, int time) {
        this.robotLoc = robotLoc;
        this.destX = destX;
        this.destY = destY;
        this.leftPower = leftPower;
        this.rightPower = rightPower;
        this.time = time;
    }

    public Location getRobotLoc() {
        return robotLoc;
    }

    public void setRobotLoc(Location robotLoc) {
        this.robotLoc = robotLoc;
    }

    public double getDestX() {
        return destX;
    }

    public void setDestX(double destX) {
        this.destX = destX;
    }

    public double getDestY() {
        return destY;
    }

    public void setDestY(double destY) {
        this.destY = destY;
    }

    public int getLeftPower() {
        return leftPower;
    }

    public void setLeftPower(int leftPower) {
        this.leftPower = leftPower;
    }

    public int getRightPower() {
        return rightPower;
    }

    public void setRightPower(int rightPower) {
        this.rightPower = rightPower;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}

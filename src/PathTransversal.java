import java.util.List;

/**
 * Created by devonkinghorn on 6/1/17.
 */
public class PathTransversal {
    List<PotentialField> destinations;
    Communicator communicator;
    public PathTransversal(Communicator communicator) {
        this.communicator = communicator;
    }

    private Location getRobotLocation() {
        while(true) {
            try {
                return communicator.getRobotPosition();

            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void transversePath(List<PotentialField> destinations) {
        Location robotLoc = getRobotLocation();
        for (PotentialField destination: destinations) {
            while (Math.abs(robotLoc.getX()-destination.getX()) > 200 ||
                    Math.abs(robotLoc.getY()-destination.getY()) > 100) {
                robotLoc = getRobotLocation();
                double angle = (Math.toDegrees(Math.atan2(destination.getY() - robotLoc.getY(), destination.getX() - robotLoc.getX())) + 360) % 360;
                double robotAngle = (Math.toDegrees(robotLoc.getAngle()) + 360) % 360;
                double motionAngle = (robotAngle - angle + 360) % 360;
                int rightMotor = 9;
                int leftMotor = 9;
                if(motionAngle > 180) {
                    leftMotor = (int)(leftMotor * ((motionAngle - 180)/180 - .5) * 2);
                } else {
                    rightMotor = (int)(rightMotor * ((180 - motionAngle)/180 - .5) * 2);
                }
                communicator.sendMessage("speed " + rightMotor + " " + leftMotor);

            }

        }
        communicator.sendMessage("speed 0 0");
    }
}

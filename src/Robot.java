

public class Robot {
    public class MoveCommand {

    }
    double xPosition;
    double yPosition;
    //TODO: change angle to a vector from a double
    double angle;

    public Robot(double xPosition, double yPosition, double angle) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.angle = angle;
    }

    public void setPosition(double xPosition, double yPosition, double angle) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.angle = angle;
    }

    public MoveCommand getMoveCommand(Object vectorToFollow) {
        // compare my angle to the vector
        // get how much the motors should move
        return null;
    }
}

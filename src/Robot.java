

public class Robot {
    public class MoveCommand {
        int left;
        int right;

        public MoveCommand(int left, int right) {
            this.left = left;
            this.right = right;
        }

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public int getRight() {
            return right;
        }

        public void setRight(int right) {
            this.right = right;
        }
    }
    public double xPosition;
    public double yPosition;
    //TODO: change angle to a vector from a double
    public double angle;

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

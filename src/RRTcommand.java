import java.util.*;

/**
 * Created by swlyons on 6/1/2017.
 */
public class RRTcommand {

    public RRTcommand() {
    }

    public List<PotentialField> goToGoalRRT(int goal) {
        System.out.println("START");
        int obstacleSize= 35;
        int deltaQ = 70;
        int goalX = -1;
        int goalY = -1;
        Random r = new Random((int) (new Date()).getTime());
        String hostName = "192.168.2.29";
        int portNumber = 55555;
        List<int[]> rrtree = new ArrayList<int[]>();
        List<Integer> cameFrom = new ArrayList<Integer>();
        cameFrom.add(-1);
        List<Location> obstacleLocations = new ArrayList<Location>();
        try {
            Communicator c = new Communicator(hostName, portNumber);
            System.out.println(c.sendMessage("where others"));
            System.out.println(c.sendMessage("param kp 30"));
            System.out.println(c.sendMessage("param ki 0.2"));
            FieldLocations f = c.getFields();

            for (Map.Entry<Integer, Location> entry : f.getFields().entrySet()) {
                if (entry.getKey() == goal) {
                    goalX = (int) entry.getValue().getX();
                    goalY = (int) entry.getValue().getY();
                } else {
                    obstacleLocations.add(entry.getValue());
                }
            }

            Location start = c.getRobotPosition();

            rrtree.add(new int[2]);
            rrtree.get(0)[0] = (int) start.getX();
            rrtree.get(0)[1] = (int) start.getY();

            System.out.println(start.getX());
            System.out.println(goalX);

            int iterations = 0;
            boolean notComplete = true;
            while (notComplete) {
                int xRand = r.nextInt(1700);
                int yRand = r.nextInt(800);

                int nearest = 0;
                int distance = (rrtree.get(0)[0] - xRand) * (rrtree.get(0)[0] - xRand) + (rrtree.get(0)[1] - yRand) * (rrtree.get(0)[1] - yRand);

                for (int i = 1; i < rrtree.size(); i++) {
                    int tempDistance = (rrtree.get(i)[0] - xRand) * (rrtree.get(i)[0] - xRand) + (rrtree.get(i)[1] - yRand) * (rrtree.get(i)[1] - yRand);
                    if (tempDistance < distance) {
                        nearest = i;
                        distance = tempDistance;
                    }
                }

                distance = (int) Math.sqrt(distance);

                int xNew;
                int yNew;

                if (distance < deltaQ) {
                    xNew = xRand;
                    yNew = yRand;
                } else {
                    double angle = Math.atan2(yRand - rrtree.get(nearest)[1], xRand - rrtree.get(nearest)[0]);
                    xNew = (int) (rrtree.get(nearest)[0] + deltaQ * Math.cos(angle));
                    yNew = (int) (rrtree.get(nearest)[1] + deltaQ * Math.sin(angle));
                }


                //test to see if obstacle free
                boolean tooCloseToAnObstacle = false;
                for (int i = 0; i < obstacleLocations.size(); i++) {
                    double xKnot = obstacleLocations.get(i).getX();
                    double yKnot = obstacleLocations.get(i).getY();
                    double xOne = rrtree.get(nearest)[0];
                    double yOne = rrtree.get(nearest)[1];
                    double xTwo = xNew;
                    double yTwo = yNew;

                    distance = Math.max(distance, 1);

                    int obstacleDistance = (int) Math.abs((yTwo - yOne) * xKnot - (xTwo - xOne) * yKnot + xTwo * yOne - yTwo * xOne) / distance;

                    if (obstacleDistance < obstacleSize) {
                        tooCloseToAnObstacle = true;
                        break;
                    }
                }

                if (!tooCloseToAnObstacle) {
                    rrtree.add(new int[2]);
                    rrtree.get(rrtree.size() - 1)[0] = xNew;
                    rrtree.get(rrtree.size() - 1)[1] = yNew;
                    cameFrom.add(nearest);
                    //check to see if goal is in range
                    if ((goalX - xNew) * (goalX - xNew) + (goalY - yNew) * (goalY - yNew) < deltaQ * deltaQ) {
//                        rrtree.add(new int[2]);
//                        rrtree.get(rrtree.size() - 1)[0] = goalX;
//                        rrtree.get(rrtree.size() - 1)[1] = goalY;

                        notComplete = false;
                    }
                }
            }

            List<PotentialField> pointsAsAnswers = new ArrayList<PotentialField>();
            pointsAsAnswers.add(new AttractionField(goalX, goalY));

            for (int i = cameFrom.get(cameFrom.size() - 1); i != -1; i = cameFrom.get(i)) {
                pointsAsAnswers.add(0, new AttractionField(rrtree.get(i)[0], rrtree.get(i)[1]));
            }
            System.out.println("END");
            return pointsAsAnswers;

        } catch (Exception e) {
            System.out.println(e);
            System.out.println(e.getMessage());
            System.out.println("ERROR");
            return null;
        }

    }

}

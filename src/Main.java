import java.net.UnknownHostException;
import java.util.*;
import java.io.*;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {

        String hostName = "localhost";
        int portNumber = 55555;
        List<PotentialField> fields = new ArrayList<PotentialField>();
        System.out.println("A");
        try {
            Communicator c = new Communicator(hostName, portNumber);
//            c.sendMessage("where others");
//            c.sendMessage("speed 0 0");
//            while(true){
//                if(1==2)
//                break;
//            }
            System.out.println("B");
            FieldLocations f = c.getFields();
            for(Map.Entry<Integer, Location> entry: f.getFields().entrySet()){
                if(entry.getKey()==21){
                    fields.add(new AttractionField(entry.getValue().getCenter().get(0), entry.getValue().getCenter().get(1), 200, 10, 1000));
                }
                if(entry.getKey()==20){
                    fields.add(new RepulsionField(entry.getValue().getCenter().get(0), entry.getValue().getCenter().get(1), 200, 10, 10000));
                }
            }
            System.out.println("C");
            Location robotLoc = c.getRobotPosition();
            while (robotLoc.getCenter() == null){
                System.out.println("Why?");
                robotLoc = c.getRobotPosition();
            }
            System.out.println("D");
            Robot robot = new Robot(robotLoc.getX(), robotLoc.getY(), robotLoc.getAngle());

            System.out.println("E");
            Vector finalVector = new Vector(10,10);

            String s;

            boolean clock = true;

            System.out.println("F");
            while(finalVector.getMagnitude() > 1) {
                clock = !clock;
                System.out.println("Z");
                finalVector = new Vector(0, 0);
                robotLoc = c.getRobotPosition();
                while (robotLoc.getCenter() == null){
                    System.out.println("Why?");
                    robotLoc = c.getRobotPosition();
                }
                robot = new Robot(robotLoc.getX(), robotLoc.getY(), robotLoc.getAngle());
                for (PotentialField field : fields) {
                    finalVector.addVector(field.getAttractionVector(robot.xPosition, robot.yPosition));
                }
                System.out.println("G");

//                while (Math.abs(robotLoc.getAngle() - Math.atan2(finalVector.getY(), finalVector.getX())) > 1) {
//                    s = c.sendMessage("speed 3 -3");
//                    robotLoc = c.getRobotPosition();
//                    while (robotLoc.getCenter() == null){
//                        System.out.println("Why?");
//                        robotLoc = c.getRobotPosition();
//                    }
//                    Thread.sleep(5);
//                }
//                Thread.sleep(1000);



                System.out.println(robotLoc.getAngle());
                System.out.println(Math.atan2(finalVector.getY(), finalVector.getX()));

//                c.sendMessage("where others");
//                c.sendMessage("speed 0 0");
//                while(true){
//                    if(1==2)
//                    break;
//                }

                double robotangle = robotLoc.getAngle()*-1;
                if(robotangle<0){
                    robotangle+=2*Math.PI;
                }
                double vectorangle = Math.atan2(finalVector.getY(), finalVector.getX())*-1;
                if(vectorangle<0){
                    vectorangle+=2*Math.PI;
                }

                vectorangle-=robotangle;
                if(vectorangle<0){
                    vectorangle+=Math.PI*2;
                }

                //true is left
                if(vectorangle<Math.PI){
                    System.out.println("AngleA "+Math.abs(robotLoc.getAngle() - Math.atan2(finalVector.getY(), finalVector.getX())));
                    while (Math.abs(robotLoc.getAngle() - Math.atan2(finalVector.getY(), finalVector.getX())) > 0.3) {
                        s = c.sendMessage("speed -2 2");
                        robotLoc = c.getRobotPosition();
                        while (robotLoc.getCenter() == null) {
                            System.out.println("Why?");
                            robotLoc = c.getRobotPosition();
                        }
                        Thread.sleep(5);
                    }
                    s = c.sendMessage("speed 0 0");
                    s = c.sendMessage("speed 0 0");
                    s = c.sendMessage("speed 0 0");
                    s = c.sendMessage("speed 0 0");
                    s = c.sendMessage("speed 0 0");
                    Thread.sleep(500);
                    while (robotLoc.getCenter() == null) {
                        System.out.println("Why?");
                        robotLoc = c.getRobotPosition();
                    }
                }else{
                    System.out.println("AngleB "+Math.abs(robotLoc.getAngle() - Math.atan2(finalVector.getY(), finalVector.getX())));
                    while (Math.abs(robotLoc.getAngle() - Math.atan2(finalVector.getY(), finalVector.getX())) > 0.3) {
                        s = c.sendMessage("speed 2 -2");
                        robotLoc = c.getRobotPosition();
                        while (robotLoc.getCenter() == null) {
                            System.out.println("Why?");
                            robotLoc = c.getRobotPosition();
                        }
                        Thread.sleep(5);
                    }
                    s = c.sendMessage("speed 0 0");
                    s = c.sendMessage("speed 0 0");
                    s = c.sendMessage("speed 0 0");
                    s = c.sendMessage("speed 0 0");
                    s = c.sendMessage("speed 0 0");
                    Thread.sleep(500);
                    while (robotLoc.getCenter() == null) {
                        System.out.println("Why?");
                        robotLoc = c.getRobotPosition();
                    }
                }

//                int speed = 2;
//                int speed = (int) finalVector.getMagnitude()/3+1;
                int speed = (int) finalVector.getMagnitude();
//                System.out.println("speed "+speed);
                s = c.sendMessage("speed "+speed+" "+speed);
                s = c.sendMessage("speed "+speed+" "+speed);
                s = c.sendMessage("speed "+speed+" "+speed);
                Thread.sleep(500);
            }
            s = c.sendMessage("speed 0 0");
            s = c.sendMessage("speed 0 0");
            s = c.sendMessage("speed 0 0");

            System.out.println("DONE!");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error!");
        }


//        try {
//            Socket theSocket = new Socket(hostName, portNumber);
//            PrintWriter out = new PrintWriter(theSocket.getOutputStream(), true);
//            BufferedReader in = new BufferedReader(new InputStreamReader(theSocket.getInputStream()));
//
//            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
//            String fromServer;
//            String fromUser;
//
//            while ((fromServer = in.readLine()) != null) {
////                System.out.println("Server: " + fromServer);
//                if (fromServer.equals("Bye."))
//                    break;
//
//                fromUser = stdIn.readLine();
////                if (fromUser != null) {
////                    System.out.println("Client: " + fromUser);
//                out.println(fromUser);
////                }
//            }
//        } catch (UnknownHostException e) {
//            System.err.println("Don't know about host " + hostName);
//            System.exit(1);
//        } catch (IOException e) {
//            System.err.println("Couldn't get I/O for the connection to " +
//                    hostName);
//            System.exit(1);
//        }
    }
}


import com.google.gson.Gson;

import java.net.*;
import java.util.*;
import java.io.*;

public class Main {

    private static void goAStar(Communicator c) throws Exception {
        FieldLocations locations = c.getFields();
        Location destinationField = locations.getFields().get(74);
        locations.getFields().remove(74);
        Graph graph = new Graph(locations, 800,1600,25);
        List<Node> nodes = graph.getGraph();
        Location robotLocation = c.getRobotPosition();
        Node robot = graph.getClosest((int)robotLocation.getX(), (int)robotLocation.getY(), nodes);
        Node destination = graph.getClosest((int)destinationField.getX(), (int)destinationField.getY(), nodes);
        List<PotentialField> fields = new AStar().traverse(robot, destination);
        PathTransversal traverser = new PathTransversal(c);
        traverser.transversePath(fields);
        return;
    }
    public static void main(String[] args) {
          System.out.println("Start");
//        RRTcommand rrTcommand = new RRTcommand();

//        rrTcommand.goToGoalRRT(74);

        String hostName = "localhost";
        int portNumber = 55555;
//        List<PotentialField> fields = rrTcommand.goToGoalRRT(74);
//        System.out.println(fields.size());  27
//        List<PotentialField> fieldscan = new ArrayList<PotentialField>();
        try {
//
            Communicator c = new Communicator(hostName, portNumber);
            System.out.println(c.sendMessage("param kp 30"));
            System.out.println(c.sendMessage("param ki 0.2"));
            int maxspeed=7;
            int trialNum = 1;
            while(true) {
                RandomRunner r = new RandomRunner(maxspeed, 3500, 10, "carpet/trial_demo-" + Integer.toString(trialNum) + ".json", c);
                r.run();
                trialNum ++;
            }

//            List<PotentialField> fields = new ArrayList<PotentialField>();
//            fields.add(new AttractionField(930, 450));
//            PathTransversal traverser = new PathTransversal(c);
//            traverser.transversePath(fields);
//            Thread.sleep(4000);
/////////////////////////////////////
//            int goalX=0;
//            int goalY=0;
//            FieldLocations f = c.getFields();
//            for (Map.Entry<Integer, Location> entry : f.getFields().entrySet()) {
//                if (entry.getKey() == 27) {
//                    goalX = (int) entry.getValue().getX();
//                    goalY = (int) entry.getValue().getY();
//                break;
//                }
//            }
//
//            Location robotLoc = c.getRobotPosition();
//            TrialData t = new TrialData(robotLoc, goalX, goalY, 0, 0);
//            String result = new Gson().toJson(t);
//
//            String hostName2="http://localhost";
//            int portNumber2=5000;
//
//            URL url = new URL(hostName2+":"+portNumber2 + "/predict");
//            HttpURLConnection connection = null;
//            connection = (HttpURLConnection) url.openConnection();
//
//            connection.setDoOutput(true);
//            connection.setRequestMethod("POST");
//            connection.setRequestProperty("Content-Type", "application/json");
//            connection.setRequestProperty("Accept", "application/json");
//            connection.setRequestProperty("Content-Language", "en-US");
//
//            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
//            writer.write(result);
//            writer.close();
//
//            if (connection.getResponseCode() == 401) {}
//
//            InputStream is = connection.getInputStream();
//            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
//
//            String line="";
//            StringBuilder response=new StringBuilder();
//            while ((line = rd.readLine()) != null) {
//                response.append(line);
//            }
//
//            rd.close();
//            System.out.println(response.toString());
//            Gson gson = new Gson();
//            ArrayList<Double> res = gson.fromJson(response.toString(), ArrayList.class);
//            int turn = res.get(0).intValue();
//            int sleepTime = res.get(1).intValue();
//
//
//
//
//            int leftPower=maxspeed;
//            int rightPower=maxspeed;
//            if(turn<0){
//                leftPower = maxspeed + turn;
//            }else{
//                rightPower = maxspeed - turn;
//            }
//            System.out.println(String.format("%1$s %2$s", leftPower, rightPower));
//            c.sendMessage(String.format("speed %1$s %2$s", leftPower, rightPower));
//            Thread.sleep(sleepTime);
//            c.sendMessage("speed 0 0");
///////////////////////////////////////////////


//            public TrialData(Location robotLoc, double destX, double destY, int leftPower, int rightPower, int time) {
//            System.out.println(c.sendMessage("where others"));
//            System.out.println(c.sendMessage("param kp 30"));
//            System.out.println(c.sendMessage("param ki 0.2"));
//            goAStar(c);
//            Location target = c.getFields().getFields().get(74);
//            fields.add(new AttractionField(target.getCenter().get(0), target.getCenter().get(1)));
//            target = c.getFields().getFields().get(85);
//            fields.add(new AttractionField(target.getCenter().get(0), target.getCenter().get(1)));
//            PathTransversal traverser = new PathTransversal(c);
//            traverser.transversePath(fields);
//            return;

//            FieldLocations f = c.getFields();
//            for(Map.Entry<Integer, Location> entry: f.getFields().entrySet()){
////                if(entry.getKey()!=5){
////                    fields.add(new RepulsionField(entry.getValue().getCenter().get(0), entry.getValue().getCenter().get(1), 50, 0.5, 100));
////                    fieldscan.add(new AttractionField(entry.getValue().getCenter().get(0), entry.getValue().getCenter().get(1), 50, 10, 100));
////                }
//                if(entry.getKey()==21){
//                    fields.add(new AttractionField(entry.getValue().getCenter().get(0), entry.getValue().getCenter().get(1), 100, 10, 350));
//                }
//                if(entry.getKey()==20){
//                    fields.add(new RepulsionField(entry.getValue().getCenter().get(0), entry.getValue().getCenter().get(1), 50, 0.5, 200));
//                }
//            }
//            fields.add(new RandomField(3));
//
//
//            Location robotLoc = c.getRobotPosition();
//            while (robotLoc.getCenter() == null){
//                System.out.println("Why?");
//                robotLoc = c.getRobotPosition();
//            }
//            Robot robot = new Robot(robotLoc.getX(), robotLoc.getY(), robotLoc.getAngle());
//
//            Vector finalVector = new Vector(10,10);
//
//            String s;
//
//            boolean clock = true;
//
//
////            int index = 0;
//
////            while (index<fields.size()-1){
////                fields.add(index, fieldscan.get(index));
////                PotentialField tempField = fields.remove(index+1);
//
//                while(finalVector.getMagnitude() > 1) {
////                    If the speed is 0, just move to the next goal since the robot's not going to move
//
//                clock = !clock;
//                finalVector = new Vector(0, 0);
//                robotLoc = c.getRobotPosition();
//                while (robotLoc.getCenter() == null){
//                    System.out.println("Why?");
//                    robotLoc = c.getRobotPosition();
//                }
//                robot = new Robot(robotLoc.getX(), robotLoc.getY(), robotLoc.getAngle());
////                if(Math.abs(robotLoc.getX()-fields.get(index).getX())<100&&Math.abs(robotLoc.getY()-fields.get(index).getY())<100){
////                    System.out.println("GOOOOAAALLL!");
////                    break;
////                }
//                for (PotentialField field : fields) {
//                    finalVector.addVector(field.getAttractionVector(robot.xPosition, robot.yPosition));
//                }
//
//
//                double robotangle = robotLoc.getAngle()*-1;
//                if(robotangle<0){
//                    robotangle+=2*Math.PI;
//                }
//                double vectorangle = Math.atan2(finalVector.getY(), finalVector.getX())*-1;
//                if(vectorangle<0){
//                    vectorangle+=2*Math.PI;
//                }
//
//                vectorangle-=robotangle;
//                if(vectorangle<0){
//                    vectorangle+=Math.PI*2;
//                }
//
//                //true is left
//                if(vectorangle<Math.PI){
//                    System.out.println("AngleA "+Math.abs(robotLoc.getAngle() - Math.atan2(finalVector.getY(), finalVector.getX())));
//                    while (Math.abs(robotLoc.getAngle() - Math.atan2(finalVector.getY(), finalVector.getX())) > 0.3) {
////                        if(Math.abs(robotLoc.getX()-fields.get(index).getX())<100&&Math.abs(robotLoc.getY()-fields.get(index).getY())<100){
////                            System.out.println("GOOOOAAALLL!");
////                            break;
////                        }
//
//                        int speed = (int) finalVector.getMagnitude();
//                        if(speed>10){
//                            speed=10;
//                        }
//                        s = c.sendMessage("speed "+(speed-8)+" "+speed);
//                        robotLoc = c.getRobotPosition();
//                        while (robotLoc.getCenter() == null) {
//                            System.out.println("Why?");
//                            robotLoc = c.getRobotPosition();
//                        }
//                        Thread.sleep(5);
//                    }
////                    s = c.sendMessage("speed 0 0");
////                    s = c.sendMessage("speed 0 0");
////                    s = c.sendMessage("speed 0 0");
////                    s = c.sendMessage("speed 0 0");
////                    s = c.sendMessage("speed 0 0");
//                    Thread.sleep(5);
//                    while (robotLoc.getCenter() == null) {
//                        System.out.println("Why?");
//                        robotLoc = c.getRobotPosition();
//                    }
//                }else{
//                    System.out.println("AngleB "+Math.abs(robotLoc.getAngle() - Math.atan2(finalVector.getY(), finalVector.getX())));
//                    while (Math.abs(robotLoc.getAngle() - Math.atan2(finalVector.getY(), finalVector.getX())) > 0.3) {
////                        if(Math.abs(robotLoc.getX()-fields.get(index).getX())<100&&Math.abs(robotLoc.getY()-fields.get(index).getY())<100){
////                            System.out.println("GOOOOAAALLL!");
////                            break;
////                        }
//
//                        int speed = (int) finalVector.getMagnitude();
//                        if(speed>10){
//                            speed=10;
//                        }
//                        s = c.sendMessage("speed "+speed+" "+(speed-8));
//                        robotLoc = c.getRobotPosition();
//                        while (robotLoc.getCenter() == null) {
//                            System.out.println("Why?");
//                            robotLoc = c.getRobotPosition();
//                        }
//                        Thread.sleep(5);
//                    }
//                    Thread.sleep(5);
//                    while (robotLoc.getCenter() == null) {
//                        System.out.println("Why?");
//                        robotLoc = c.getRobotPosition();
//                    }
//                }
//
////                    if(Math.abs(robotLoc.getX()-fields.get(index).getX())<100&&Math.abs(robotLoc.getY()-fields.get(index).getY())<100){
////                        System.out.println("GOOOOAAALLL!");
////                        break;
////                    }
//
//
////                int speed = 2;
////                int speed = (int) finalVector.getMagnitude()/3+1;
//                int speed = (int) finalVector.getMagnitude();
//                if(speed>10){
//                    speed=10;
//                }
//                s = c.sendMessage("speed "+speed+" "+speed);
//                s = c.sendMessage("speed "+speed+" "+speed);
//                s = c.sendMessage("speed "+speed+" "+speed);
//                Thread.sleep(5);
//            }
////                fields.remove(index);
////                fields.add(index,tempField);
////                index++;
////                finalVector = new Vector(0, 0);
////                for (PotentialField field : fields) {
////                    finalVector.addVector(field.getAttractionVector(robot.xPosition, robot.yPosition));
////                }
//
////            }
//
//            s = c.sendMessage("speed 0 0");
//            s = c.sendMessage("speed 0 0");
//            s = c.sendMessage("speed 0 0");
//
//            c.sendMessage("speed 0 0");
//            System.out.println("DONE!");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error!");
        }
//
//
    }
}


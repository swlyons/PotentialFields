import java.net.UnknownHostException;
import java.util.*;
import java.io.*;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {

        String hostName = "localhost";
        int portNumber = 55555;
        List<PotentialField> fields = new ArrayList<PotentialField>();


        try {
            Communicator c = new Communicator(hostName, portNumber);
            FieldLocations f = c.getFields();
            for(Map.Entry<Integer, Location> entry: f.getFields().entrySet()){
                if(entry.getKey()==21){
                    fields.add(new AttractionField(entry.getValue().getCenter().get(0), entry.getValue().getCenter().get(1), 100, 10, 1000));
                }
            }
            Robot robot = new Robot(c.getRobotPosition().getX(), c.getRobotPosition().getY(), c.getRobotPosition().getAngle());

            Vector finalVector = new Vector(10,10);

            while(finalVector.getY() > 1 && finalVector.getX() > 1) {
                finalVector = new Vector(0, 0);

                for (PotentialField field : fields) {
                    finalVector.addVector(field.getAttractionVector(robot.xPosition, robot.yPosition));
                }

                System.out.println(Math.sqrt(finalVector.getX() * finalVector.getX() + finalVector.getY() * finalVector.getY()));
                String s;

                if (Math.abs(c.getRobotPosition().getAngle() - Math.atan2(finalVector.getY(), finalVector.getX())) > 10) {
                    s = c.sendMessage("speed 2 -2");
                    while (Math.abs(c.getRobotPosition().getAngle() - Math.atan2(finalVector.getY(), finalVector.getX())) > 10) {
                        Thread.sleep(5);
                    }
                }

                s = c.sendMessage("speed 2 2");
                Thread.sleep(5);
            }



        } catch (Exception e) {

        }


        try {
            Socket theSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(theSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(theSocket.getInputStream()));

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;

            while ((fromServer = in.readLine()) != null) {
//                System.out.println("Server: " + fromServer);
                if (fromServer.equals("Bye."))
                    break;

                fromUser = stdIn.readLine();
//                if (fromUser != null) {
//                    System.out.println("Client: " + fromUser);
                out.println(fromUser);
//                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }
    }
}


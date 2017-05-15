import java.io.IOException;
import java.net.Socket;

public class Communicator {
    Socket socket;
    public Communicator(String hostName, int port) throws IOException{
        socket = new Socket(hostName, port);
    }

    public String receiveMessage(){
        return "";
    }

    public void sendMessage(String message){
        return;
    }

    public Object getFields(){
        return null;
    }

    public Object getRobotPosition(){
        return null;
    }

    public void sendMoveInstruction(Robot.MoveCommand command) {

    }

}
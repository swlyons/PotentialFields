import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Communicator {
    Socket socket;
    BufferedReader reader;
    PrintWriter writer;


    public Communicator(String hostName, int port) throws IOException{
        socket = new Socket(hostName, port);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
    }

    public String sendMessage(String message){
        try {
            writer.println(message);
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "broken connection";
    }

    public FieldLocations getFields(){
        String results = sendMessage("where others");
        return parseFieldLocations(results);
    }

    public Location getRobotPosition(){
        String results = sendMessage("where robot");
        return new Gson().fromJson(results, Location.class);
    }

    public void sendMoveInstruction(Robot.MoveCommand command) {
        sendMessage(String.format("speed %1$s %2$s", command.getLeft(), command.getRight()));
    }

    private FieldLocations parseFieldLocations(String json) {
        Gson gson = new Gson();
        JsonObject o = gson.fromJson(json, JsonObject.class);
        Set<Map.Entry<String, JsonElement>> entries = o.entrySet();
        int time = 0;

        Map<Integer, Location> locations = new TreeMap<Integer, Location>();
        for(Map.Entry<String, JsonElement> entry : entries) {
            if (entry.getKey().equals("time")) {
                time = entry.getValue().getAsInt();
            } else {
                int key = Integer.parseInt(entry.getKey());
                locations.put(key, (Location)gson.fromJson(entry.getValue(),Location.class));
            }
        }
        return new FieldLocations(time, locations);
    }

}
import java.util.ArrayList;
import java.util.List;

/**
 * Created by devonkinghorn on 6/1/17.
 */
public class Graph {

    FieldLocations obstacles;
    int height, width, distanceBetweenNodes;

    List<Integer> obstacleX;
    List<Integer> obstacleY;

    public Graph(FieldLocations obstacles, int height, int width, int distanceBetweenNodes) {
        this.obstacles = obstacles;
        this.height = height;
        this.width = width;
        this.distanceBetweenNodes = distanceBetweenNodes;
        obstacleX = new ArrayList<>();
        obstacleY = new ArrayList<>();
        for(Location obstacle : obstacles.getFields().values()){
            obstacleX.add((int)obstacle.getX());
            obstacleY.add((int)obstacle.getY());
        }

    }


    private boolean inObstacle(int x, int y) {
        System.out.println("obstacle?");
        for(int i = 0; i < obstacleX.size(); i++) {
            int _x = obstacleX.get(i);
            int _y = obstacleY.get(i);
            if(Math.abs(x - _x) < 100 && Math.abs(y - _y) < 100){
                System.out.println("yes");
                return true;
            }
        }
        System.out.println("no");
        return false;
    }
    public List<Node> getGraph(){
        int xcount = width/distanceBetweenNodes;
        int ycount = height/distanceBetweenNodes;
        Node[][] nodes = new Node[xcount][ycount];
        for(int x = 0; x < xcount; x++) {
            int potentialX = x * distanceBetweenNodes;
            for(int y = 0; y < ycount; y++) {
                int potentialY = y * distanceBetweenNodes;
                if( !inObstacle(potentialX,potentialY)) {
                    nodes[x][y] = new Node(potentialX, potentialY);
                }
            }
        }
        ArrayList<Node> result = new ArrayList<>();
        for(int x = 0; x < xcount; x++) {
            for (int y = 0; y < ycount; y++) {
                Node node = nodes[x][y];
                if(node != null) {
                    System.out.println("Adding node");
                    result.add(node);
                    node.setData("X: " + node.getX() + " Y: " + node.getY());
                    if (x > 0)
                        node.addNeighbor(nodes[x-1][y]);
                    if(x < xcount - 1)
                        node.addNeighbor(nodes[x+1][y]);
                    if(y > 0)
                        node.addNeighbor(nodes[x][y - 1]);
                    if(y < ycount - 1)
                        node.addNeighbor(nodes[x][y + 1]);
                    System.out.println("node added");
                }
            }
        }
        return result;
    }
    public Node getClosest(int x, int y, List<Node> nodes) {
        Node closest = null;
        int closestDistance = Integer.MAX_VALUE;
        for(Node node : nodes) {
            int distance = Math.abs(node.getX() - x) + Math.abs(node.getY() - y);
            if (distance < closestDistance){
                closestDistance = distance;
                closest = node;
            }
        }
        return closest;
    }
}

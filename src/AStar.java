
import java.util.*;

public class AStar {

    private PriorityQueue<Node> openList;
    private ArrayList<Node> closedList;
    HashMap<Node, Integer> gVals;
    HashMap<Node, Integer> fVals;
    private int initialCapacity = 100;
    private int distanceBetweenNodes = 1;

    public AStar() {
        gVals = new HashMap<Node, Integer>();
        fVals = new HashMap<Node, Integer>();
        openList = new PriorityQueue<Node>(initialCapacity, new fCompare());
        closedList = new ArrayList<Node>();
    }

    public List<PotentialField> traverse(Node start, Node end) {
        openList.clear();
        closedList.clear();

        gVals.put(start, 0);
        openList.add(start);

        while (!openList.isEmpty()) {
            Node current = openList.element();
            if (current.equals(end)) {
                System.out.println("Goal Reached");
                printPath(current);
                return getPath(current);
            }
            closedList.add(openList.poll());
            ArrayList<Node> neighbors = current.getNeighbors();

            for (Node neighbor : neighbors) {
                int gScore = gVals.get(current) + distanceBetweenNodes;
                int fScore = gScore + h(neighbor, current);

                if (closedList.contains(neighbor)) {

                    if (gVals.get(neighbor) == null) {
                        gVals.put(neighbor, gScore);
                    }
                    if (fVals.get(neighbor) == null) {
                        fVals.put(neighbor, fScore);
                    }

                    if (fScore >= fVals.get(neighbor)) {
                        continue;
                    }
                }
                if (!openList.contains(neighbor) || fScore < fVals.get(neighbor)) {
                    neighbor.setParent(current);
                    gVals.put(neighbor, gScore);
                    fVals.put(neighbor, fScore);
                    if (!openList.contains(neighbor)) {
                        openList.add(neighbor);

                    }
                }
            }
        }
        System.out.println("FAIL");
        return new ArrayList<>();
    }

    private int h(Node node, Node goal) {
        int x = node.getX() - goal.getX();
        int y = node.getY() - goal.getY();
        return x * x + y * y;
    }

    private List<PotentialField> getPath(Node node){
        ArrayList<PotentialField> fields = new ArrayList<>();
        fields.add(0,new AttractionField(node.getX(), node.getY()));
        while (node.getParent() != null) {
            node = node.getParent();
            fields.add(0,new AttractionField(node.getX(), node.getY()));
        }
        return fields;
    }
    private void printPath(Node node) {
        System.out.println(node.getData());

        while (node.getParent() != null) {
            node = node.getParent();
            System.out.println(node.getData());
        }
    }

    class fCompare implements Comparator<Node> {

        public int compare(Node o1, Node o2) {
            if (fVals.get(o1) < fVals.get(o2)) {
                return -1;
            } else if (fVals.get(o1) > fVals.get(o2)) {
                return 1;
            } else {
                return 1;
            }
        }
    }
}
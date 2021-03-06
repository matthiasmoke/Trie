/**
 * Trie data structure for saving student data
 */
public class Trie {

    private  Node root;

    /**
     *Creates a new instance of Trie
     */
    public  Trie() {
        root = new Node();
    }

    /**
     *Adds a new node to position key with the value points
     * @param key name of student
     * @param points points of student
     * @return true if new node is added successfully
     */
    public  boolean add(String key, Integer points) {

        char[] keyChars = key.toCharArray();
        Node currNode = root;
        Node childNode = null;

        //navigate through the tree to find/create end node of key
        for (int i = 0; i < keyChars.length; i++) {

            childNode = currNode.getChild(keyChars[i]);
            if (childNode == null) {
                childNode = new Node(keyChars[i], currNode);
            }
            currNode = childNode;
        }

        if (currNode.getPoints() != null) {
            return false;
        }
        currNode.setPoints(points);
        return true;
    }

    /**
     *Deletes the given node and all unnecessary related nodes
     * @param key name of student
     * @return true when child is found
     */
    public boolean remove(String key) {
        Node nodeToRemove = root.find(key);

        if (nodeToRemove != null && nodeToRemove.getPoints() != null) {
            nodeToRemove.remove();
            return  true;
        }
        return false;
    }

    /**
     *Changes the point value of the given node
     * @param key name of student
     * @param points point value
     * @return true if points are changed successfully
     */
    public boolean change(String key, Integer points) {

        Node nodeToChange = root.find(key);

        if (nodeToChange != null && nodeToChange.getPoints() != null) {
            nodeToChange.setPoints(points);
            return true;
        }
        return false;
    }

    /**
     *Searches for the end-node of the keyword and gets points
     * @param key keyword
     * @return points or null if no node is found
     */
    public Integer points(String key) {
        Node n = root.find(key);
        if (n != null && n.getPoints() != null) {
            return n.getPoints();
        }
            return -1;
    }

    /**
     *Represents the trie as string
     * @return a string that presents the current trie
     */
    public String toString() {
        if (root != null) {
            String output = "+";
            output += root.toString();
            return output;
        } else {
            return "";
        }
    }
}

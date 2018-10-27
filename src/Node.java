public class Node {

    private Integer points;
    private Node[] children;
    private Node parent;
    private char ch;
    private final char[] ALPHABET = ("abcdefghijklmnopqrstuvwxyz").toCharArray();

    public Node(){
        this.children = new Node[26];
    }

    public Node(char ch, Node parent){
        this.ch = ch;
        this.parent = parent;
        this.children = new Node[26];
    }



    /**
     * Sets the reference to a childnode for the given character
     * @param ch character of childnode
     * @param child node to set the reference to
     */
    private void setChild(char ch, Node child){
        children[getArrayPositionOfChar(ch)] = child;
    }

    /**
     * Gets the childnode for a certain character
     * @param ch character of childnode
     * @return childnode or null if no child is set
     */
    public Node getChild(char ch){
        return children[getArrayPositionOfChar(ch)];
    }

    /**
     * Gets the end-node for the given keyword
     * @param key keyword
     * @return end-node or null if no node is found
     */
    public Node find(String key){
        char[] characters = key.toLowerCase().toCharArray();
        Node currChild = getChild(characters[0]);

        for(int i = 1; i < characters.length; i++){

            if(currChild != null){
                currChild = currChild.getChild(characters[i]);
            }

            return null;
        }

        return currChild;
    }

    /**
     * Removes the point value and children of the node
     */
    public void remove(){

    }

    /**
     *
     * @return
     */
    public String toString(){
        return "";
    }

    /**
     * Sets the points value of the node
     * @param points
     */
    public  void setPoints(Integer points){
        this.points = points;
    }

    /**
     * Returns the points value of the node
     * @return
     */
    public Integer getPoints(){
        return  points;
    }

    /**
     * Gets the position of the given char in the children array
     * @param c char
     * @return node position of given char
     */
    private int getArrayPositionOfChar(char c) throws IllegalArgumentException{
        for(int i = 0; i < ALPHABET.length; i++){
            if(ALPHABET[i] == c){
                return i;
            }
        }

        throw new IllegalArgumentException();
    }
}

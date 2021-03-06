/**
 *Represents a node in the Trie
 */
public class Node {

    private Integer points;
    private Node[] children;
    private Node parent;
    private char ch;

    /**
     *Constructor for creating a root-node
     */
    public Node() {
        this.children = new Node[26];
    }

    /**
     *Constructor for creating a regular node
     * @param ch represents the char that points to the node
     * @param parent is the superordinate node
     */
    public Node(char ch, Node parent) {
        this.ch = ch;
        this.parent = parent;
        this.children = new Node[26];
        parent.setChild(ch, this);
    }

    /**
     *Sets the reference to a childnode for the given character
     * @param ch character of childnode
     * @param child node to set the reference to
     */
    private void setChild(char ch, Node child) {
        children[getArrayPositionOfChar(ch)] = child;
    }

    /**
     *Gets the childnode for a certain character
     * @param ch character of childnode
     * @return childnode or null if no child is set
     */
    public Node getChild(char ch) {
        return children[getArrayPositionOfChar(ch)];
    }

    /**
     *Gets the end-node for the given keyword
     * @param key keyword
     * @return end-node or null if no node is found
     */
    public Node find(String key) {
        if (isValidName(key)) {
            char[] characters = key.toLowerCase().toCharArray();

            //Navigate through the trie to find given node
            Node currChild = getChild(characters[0]);

            for (int i = 1; i < characters.length; i++) {

                if (currChild != null) {
                    currChild = currChild.getChild(characters[i]);
                } else {
                    return null;
                }
            }
            return currChild;
        } else {
            return null;
        }
    }

    /**
     *Removes the point value and children of the node
     */
    public void remove() {
        points = null;
        if (!hasChildren()) {
            cleanUp();
        }
    }

    /**
     *Represents the node as string
     * @return presentation of the current node as string
     */
    public String toString() {
        StringBuilder b = new StringBuilder();
        boolean hasChildren = false;
        //Create string for this node and execute toString() for children
        for (int i = 0; i < children.length; i++) {
            if (children[i] != null) {
                hasChildren = true;
                b.append(children[i].ch);

                if (children[i].hasPoints()) {
                    int value = children[i].getPoints();
                    b.append("[" + value + "]");
                }

                b.append(children[i].toString());
            }
        }

        if (hasChildren) {
            addBrackets(b);
        }
        return b.toString();
    }

    /**
     *Sets the points value of the node
     * @param points points value
     */
    public  void setPoints(Integer points) {
        this.points = points;
    }

    /**
     *Returns the points value of the node
     * @return point value of the node
     */
    public Integer getPoints() {
        return points;
    }

    /**
     *Gets the position of the given char in the children array
     * @param c char
     * @return node position of given char
     */
    private int getArrayPositionOfChar(char c) {
        int position = (int) c - (int) ('a');
        return position;
    }

    /**
     * Cleans all unnecessary parent nodes
     */
    private void cleanUp() {
        Node currNode = parent;
        char currChar = this.ch;
        boolean remove = true;

        while (remove) {
            currNode.setChild(currChar, null);

            if (currNode.hasChildren() || currNode.points != null) {
                remove = false;
            }

            if (currNode.ch != '\u0000') {
                currChar = currNode.ch;
            } else {
                remove = false;
            }

            if (currNode.parent != null) {
                currNode = currNode.parent;
            }
        }
    }

    private boolean hasChildren() {
        for (int i = 0; i < children.length; i++) {
            if (children[i] != null) {
                return true;
            }
        }
        return false;
    }

    private boolean hasPoints() {
        if (points != null && points >= 0) {
            return true;
        }
        return false;
    }

    /**
     * Adds brackets in the front and at the end
     * @param builder Stringbuilder to append brackets to
     * @return String with added brackets
     */
    private String addBrackets(StringBuilder builder) {
        if (builder != null) {
            builder.insert(0, "(");
            builder.append(")");
            return builder.toString();
        } else {
            return "";
        }
    }

    /**
     * Checks if given string is a valid name without numbers in it
     * @param name name to check
     * @return true if name is valid
     */
    private boolean isValidName(String name) {
        try {
            Integer.parseInt(name);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }
}

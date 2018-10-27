public class Trie {

    private  Node root;

    public  Trie(){
        root = new Node();
    }

    /**
     *
     * @param key
     * @param points
     * @return
     */
    public  boolean add(String key, Integer points){

        if(root.find(key).getPoints() > 0){
            return false;
        }
    }

    /**
     *
     * @param key
     * @return
     */
    public boolean remove(String key){

    }

    /**
     *
     * @param key
     * @param points
     * @return
     */
    public boolean change(String key, Integer points){

        Node nodeToChange = root.find(key);

        if(nodeToChange != null){

        }

        return false;
    }

    /**
     *Searches for the end-node of the keyword and gets points
     * @param key keyword
     * @return points or null if no node is found
     */
    public Integer points(String key){
        int points = root.find(key).getPoints();
        return points;
    }

    /**
     *
     * @return
     */
    public  String toString(){

    }


}

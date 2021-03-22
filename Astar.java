import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

public class Astar
{
    Visualizer visualizer = new Visualizer();
    //func for chek node (from BFS)
    public boolean isGoal(Node node){
        if (node.map.at(node.player.i,node.player.j).name == 'C'){
            return true;
        }
        else {
            return false;
        }
    }

    //func for result (from BFS)
    public void result(Node node){
        Stack<Node> nodes = new Stack<Node>();
        while (true){
            nodes.push(node);
            if(node.parentNode == null){
                break;
            }
            else {
                node = node.parentNode;
            }
        }
        nodes.pop();
        try {
            FileWriter myWriter = new FileWriter("result.txt");
            while (!nodes.empty()){
                Node tempNode = nodes.pop();
                String action = tempNode.priviousAction;
                System.out.println(action+" "+tempNode.player.money+" "+tempNode.player.food);
                myWriter.write(action+"\n");
                //print visualized map for every movement
                visualizer.printMap(tempNode.map, tempNode.player);
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public  int[] findCastel(Node node){
        int[] location=new int[2];
        for (int i = 0; i < node.map.rows; i++) {
            for (int j = 0; j < node.map.cols; j++) {
                if (node.map.at(i,j).name=='C'){
                    location[0]=i;
                    location[1]=j;
                    return location;
                }

            }

        }
        return null;
    }

    public  int hurestic(Node node){
        int h=0;
        int [] locationOfCastel=findCastel(node);
        int [] locationOfPlayer=new int[2];
        locationOfPlayer [0]= node.player.i;
        locationOfPlayer[1]= node.player.j;

        int tempI= Math.abs(locationOfCastel[0]-locationOfPlayer[0]);
        int tempJ=  Math.abs(locationOfCastel[1]-locationOfPlayer[1]);
        h=tempI+tempJ;
        return h;
    }


    public void search(Node intialNode){
        int nodeNumer=0;
        ArrayList<Node> frontier=new ArrayList<Node>();
        Hashtable<String, Boolean> inFrontier = new Hashtable<>();
        Hashtable<String, Boolean> explored = new Hashtable<>();

        if (isGoal(intialNode)) {
            System.out.println("first node was result!");
            result(intialNode);
        }
        int x = hurestic(intialNode);
        intialNode.f=x+ intialNode.depth;


        frontier.add(intialNode);
        inFrontier.put(intialNode.hash(),true);
        int minIndex=0;
        while (!frontier.isEmpty()){

            Node temp = frontier.get(minIndex);
            inFrontier.remove(temp.hash());
            explored.put(temp.hash(),true);

            frontier.remove(minIndex);

            ArrayList<Node> children = temp.successor();
            nodeNumer++;

            for (int i = 0; i < children.size(); i++) {
                if (!(inFrontier.containsKey(children.get(i).hash())) && !(explored.containsKey(children.get(i).hash()))) {
                    x = hurestic(children.get(i));
                    children.get(i).f = x + children.get(i).depth;
                    frontier.add(children.get(i));
                    inFrontier.put(children.get(i).hash(), true);
                }
            }
            minIndex=findMinimum(frontier);
            if (isGoal(frontier.get(minIndex))) {
                result(frontier.get(minIndex));
                System.out.println(nodeNumer);
                return;
            }






        }






    }
    public int findMinimum(ArrayList<Node>arrayList){
        int min=0;
        int index=0;
        min=arrayList.get(0).f;
        int i;
        for ( i =0;i<arrayList.size();i++){
            if (arrayList.get(i).f<min){
                min = arrayList.get(i).f;
                index=i;

            }
        }
        return index;
    }

    public ArrayList<Node> sort(ArrayList<Node> arrayList){
        ArrayList<Node> sortArrayList=arrayList;
        for (int i = 1; i < sortArrayList.size(); i++) {
            for (int j = i; j > 0; j--) {

            }

        }

        return sortArrayList;
    }





}

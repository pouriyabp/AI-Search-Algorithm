import java.io.FileWriter;
import java.io.IOException;
import java.util.*;







public class DFS{
    Visualizer visualizer = new Visualizer();

   public static void main(String[] args) {
        System.out.println("hello");
    }






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
    Stack<Node>  nodes = new Stack<Node>();
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



}
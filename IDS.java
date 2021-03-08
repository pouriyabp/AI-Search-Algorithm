import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Stack;

public class IDS {
    Visualizer visualizer = new Visualizer();
    Player player ;
    Map map;
    public  void buildMap() {
        Scanner scan = new Scanner(System.in);

        //get map size
        System.out.println("Enter map rows and cols(i,j):");
        String inputOfMap=scan.nextLine();
        int rows,col;
        String[] arr;
        arr = inputOfMap.split(",");
        rows= Integer.parseInt(arr[0]);
        col= Integer.parseInt(arr[1]);
        map =new Map(rows, col);

        //get player's information
        System.out.println("Enter player first position(i,j), food and money(use ',' for divide): ");
        int i,j,food,money;
        String inputOfPlayer=scan.nextLine();
        arr = inputOfPlayer.split(",");
        i=Integer.parseInt(arr[0]);
        i-=1;

        j=Integer.parseInt(arr[1]);
        j-=1;

        food=Integer.parseInt(arr[2]);
        money=Integer.parseInt(arr[3]);
        player=new Player(i, j, money, food);
        System.out.println("player position: "+player.i+" "+player.j);


        char [][] MapArray=new char[rows][col];
        //get map information
        System.out.println("Enter location of swamp[i,j-i,j-...]: ");
        String inputOfLocation=scan.nextLine();
        arr=inputOfLocation.split("-");


        for (int k = 0; k < arr.length; k++) {
            String text = arr[k];
            String[] arr2=text.split(",");
            i=Integer.parseInt(arr2[0]);
            i-=1;

            j=Integer.parseInt(arr2[1]);
            j-=1;

            MapArray[i][j]='S';
            //map.addEntity(i, j,new BaseEntity('S'));

        }


        System.out.println("Enter location of key[i,j]: ");
        inputOfLocation=scan.nextLine();
        arr=inputOfLocation.split("-");


        for (int k = 0; k < arr.length; k++) {
            String text = arr[k];
            String[] arr2=text.split(",");
            i=Integer.parseInt(arr2[0]);
            i-=1;

            j=Integer.parseInt(arr2[1]);
            j-=1;

            MapArray[i][j]='K';


        }


        System.out.println("Enter location of castel[i,j]: ");
        inputOfLocation=scan.nextLine();
        arr=inputOfLocation.split("-");


        for (int k = 0; k < arr.length; k++) {
            String text = arr[k];
            String[] arr2=text.split(",");
            i=Integer.parseInt(arr2[0]);
            i-=1;

            j=Integer.parseInt(arr2[1]);
            j-=1;

            MapArray[i][j]='C';


        }

        System.out.println("Enter location of bridge(P)[i,j-i,j-...]: ");
        inputOfLocation=scan.nextLine();
        arr=inputOfLocation.split("-");


        for (int k = 0; k < arr.length; k++) {
            String text = arr[k];
            String[] arr2=text.split(",");
            i=Integer.parseInt(arr2[0]);
            i-=1;

            j=Integer.parseInt(arr2[1]);
            j-=1;

            MapArray[i][j]='P';


        }


        System.out.println("Enter location of Loot[i,j,valueM,valueF-i,j,valueM,valueF-...]: ");
        inputOfLocation=scan.nextLine();
        arr=inputOfLocation.split("-");
        int valueMoney;
        int valueFood;
        String [] valueOfLoot= new String [rows*col];


        for (int k = 0; k < arr.length; k++) {
            String text = arr[k];
            String[] arr2=text.split(",");
            i=Integer.parseInt(arr2[0]);
            i-=1;

            j=Integer.parseInt(arr2[1]);
            j-=1;

            valueMoney=Integer.parseInt(arr2[2]);
            valueFood=Integer.parseInt(arr2[3]);

            valueOfLoot[(i*col)+j]=arr2[2]+"-"+arr2[3];
            MapArray[i][j]='L';


        }


        System.out.println("Enter location of bandit[i,j,power-i,j,power-...]: ");
        inputOfLocation=scan.nextLine();
        arr=inputOfLocation.split("-");
        int power;
        int [] powerOfBandit=new int[rows * col];

        for (int k = 0; k < arr.length; k++) {
            String text = arr[k];
            String[] arr2=text.split(",");
            i=Integer.parseInt(arr2[0]);
            i-=1;

            j=Integer.parseInt(arr2[1]);
            j-=1;


            power=Integer.parseInt(arr2[2]);

            powerOfBandit[(i*col)+j]=power;

            MapArray[i][j]='B';


        }


        System.out.println("Enter location of wild_animal[i,j,power-i,j,power-...]: ");
        inputOfLocation=scan.nextLine();
        arr=inputOfLocation.split("-");
        int [] powerOfAnimal=new int[rows * col];


        for (int k = 0; k < arr.length; k++) {
            String text = arr[k];
            String[] arr2=text.split(",");
            i=Integer.parseInt(arr2[0]);
            i-=1;

            j=Integer.parseInt(arr2[1]);
            j-=1;

            power=Integer.parseInt(arr2[2]);
            powerOfAnimal[(i*col)+j]=power;

            MapArray[i][j]='W';


        }

/*
      for (int k = 0; k < powerOfAnimal.length; k++) {
          System.out.print(powerOfAnimal[k]);
      }

*/





/*
        for (i=0;i<rows;i++){
            for (j=0;j<col;j++) {

                if (MapArray[i][j]!='S' & MapArray[i][j]!='K'  & MapArray[i][j]!='C' & MapArray[i][j]!='L' & MapArray[i][j]!='B' & MapArray[i][j]!='W' & MapArray[i][j]!='P') {
                    MapArray[i][j]='G';
                    System.out.print(MapArray[i][j]);


                }

                else {
                    System.out.print(MapArray[i][j]);
                }

            }
            System.out.println();
        }


*/

        //add information in map object
        for (i=0 ;i<rows;i++){
            for (j=0;j<col;j++){
                if (MapArray[i][j]=='G'){
                    map.addEntity(i,j,new BaseEntity('G'));
                }
                else if(MapArray[i][j]=='S'){
                    map.addEntity(i,j,new BaseEntity('S'));
                }
                else if(MapArray[i][j]=='K'){
                    map.addEntity(i,j,new BaseEntity('K'));
                }
                else if(MapArray[i][j]=='C'){
                    map.addEntity(i,j,new BaseEntity('C'));
                }
                else if(MapArray[i][j]=='P'){
                    map.addEntity(i,j,new Bridge());
                }
                else if(MapArray[i][j]=='L'){
                    String loot=valueOfLoot[(i*col)+j];
                    String [] valueArry=loot.split("-");


                    map.addEntity(i,j,new Loot(Integer.parseInt(valueArry[0]),Integer.parseInt(valueArry[1])));
                }
                else if(MapArray[i][j]=='B'){

                    map.addEntity(i,j,new Bandit(powerOfBandit[(i*col)+j]));

                }
                else if(MapArray[i][j]=='W'){

                    map.addEntity(i,j,new WildAnimall(powerOfAnimal[(i*col)+j]));

                }

            }
        }
        map.print();




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

    public void search(Node intialNode){
        int i=1;
        while (i<Integer.MAX_VALUE){

            if (dlsSearch(intialNode,i)==1) {
                break;
            }
            i++;
        }

    }

    public int dlsSearch(Node intialNode,int depth){
        int depthWeAre=0;
        Stack <Node> frontier= new Stack<Node>();
        Hashtable<String, Boolean> inFrontier = new Hashtable<>();
        Hashtable<String, Boolean> explored = new Hashtable<>();

        if(isGoal(intialNode)){
            result(intialNode);
            return 1;
        }
        frontier.add(intialNode);
        inFrontier.put(intialNode.hash(),true);

        while (!frontier.isEmpty()){

                Node temp = frontier.pop();
                inFrontier.remove(temp.hash());
                explored.put(temp.hash(),true);
                ArrayList<Node> children = temp.successor();

                if (depthWeAre<depth) {

                    for (int i = 0; i < children.size(); i++) {
                        if (!(inFrontier.containsKey(children.get(i).hash())) && !(explored.containsKey(children.get(i).hash()))) {
                            if (isGoal(children.get(i))) {
                                result(children.get(i));
                                return 1;
                            }

                            frontier.push(children.get(i));
                            inFrontier.put(children.get(i).hash(), true);

                        }

                    }
                }else {
                    return 0;
                    }
            depthWeAre+=1;


        }
        return 0;


    }





    public static void main(String[] args) {
        IDS ids=new IDS();
        ids.buildMap();
        Node node = new Node(ids.player, ids.map, null,null);
        ids.search(node);


    }



}

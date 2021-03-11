import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class BDS
{
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
    //func for chek node (from BFS)
    public boolean isGoal(Node node){
        if (node.map.at(node.player.i,node.player.j).name == 'C'){
            return true;
        }
        else {
            return false;
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





    public void search(Node initialNode,Player player){

        int [] castel_location = findCastel(initialNode);
        int [] player_location = new int[2];
        player_location[0]=player.i;
        player_location[1]=player.j;
        Player upsidePlayer=new Player(player.i,player.j,player.money,player.food,player.haskey);
        Map upsideMap =new Map(initialNode.map.rows,initialNode.map.cols);
        for (int i = 0; i < initialNode.map.rows; i++) {
            for (int j = 0; j < initialNode.map.cols; j++) {
                upsideMap.addEntity(i,j,initialNode.map.at(i,j));
            }
        }


    upsideMap.removeEntity(castel_location[0], castel_location[1]);
    upsideMap.removeEntity(player_location[0], player_location[1]);
    upsideMap.addEntity(player_location[0], player_location[1], new BaseEntity('C'));
    upsideMap.addEntity(castel_location[0], castel_location[1], new BaseEntity('G'));
    upsidePlayer.changePlayerLocation(castel_location[0], castel_location[1]);


    Node upsideNode= new Node(upsidePlayer,upsideMap,null,null,0);


    //bds search
    //use to bfs for search
        Queue<Node> frontier = new LinkedList<Node>();
        
        Queue<Node> upsideFrontier = new LinkedList<Node>();
        
        Hashtable<String, Boolean> inFrontier = new Hashtable<>();
        Hashtable<String, Boolean> explored = new Hashtable<>();
        
        Hashtable<String, Boolean> upsideInFrontier = new Hashtable<>();
        Hashtable<String, Boolean> upsideExplored = new Hashtable<>();
        if(isGoal(initialNode)){
            result(initialNode);
            return;
        }
        
        if(isGoal(upsideNode)){
            result(upsideNode);
            return;
        }
        frontier.add(initialNode);
        inFrontier.put(initialNode.hash(),true);
        
        upsideFrontier.add(upsideNode);
        upsideInFrontier.put(upsideNode.hash(),true);

        while (!frontier.isEmpty()|!upsideFrontier.isEmpty()) {
            Node temp = frontier.poll();
            inFrontier.remove(temp.hash());
            explored.put(temp.hash(),true);
            ArrayList<Node> children = temp.successor();

            Node upsideTemp = upsideFrontier.poll();
            upsideInFrontier.remove(upsideTemp.hash());
            upsideExplored.put(upsideTemp.hash(),true);
            ArrayList<Node> upsideChildren = upsideTemp.successor();

            for(int i = 0;i<children.size();i++){
                if(!(inFrontier.containsKey(children.get(i).hash())) && !(explored.containsKey(children.get(i).hash()))) {
                    if (isGoal(children.get(i))) {
                       // result(children.get(i));
                        System.out.println("reach Goal side");
                        return;
                    }
                    frontier.add(children.get(i));
                    inFrontier.put(children.get(i).hash(), true);
                }
            }

            for(int i = 0;i<upsideChildren.size();i++){
                if(!(upsideInFrontier.containsKey(upsideChildren.get(i).hash())) && !(upsideExplored.containsKey(upsideChildren.get(i).hash()))) {
                    if (isGoal(upsideChildren.get(i))) {
                       // result(upsideChildren.get(i));
                        System.out.println("reach Goal upside");
                        return;
                    }
                    upsideFrontier.add(upsideChildren.get(i));
                    upsideInFrontier.put(upsideChildren.get(i).hash(), true);
                }
            }


            ArrayList list = new ArrayList(frontier);
            ArrayList upsidelist = new ArrayList(upsideFrontier);



            for (int i = 0; i < list.size(); i++) {

                Node n=(Node) list.get(i);

                String nHash=n.hash();
                for (int j = 0; j < upsidelist.size(); j++) {
                  Node uN = (Node) upsidelist.get(j);
                    String unHash= uN.hash();
                   if (nHash.equals(unHash)){
                       System.out.println("found from both dirction");
                       System.out.println("n hash is "+n.hash());
                       System.out.println("un hash is "+uN.hash());
                       System.out.println("side depth "+n.depth);
                       System.out.println("upside depth "+uN.depth);
                       //result(n);
                       System.out.println("************");

                       return;
                   }
                   //upsideFrontier.add(uN);

                }
                //frontier.add(n);
                
            }
            
            
            
        }


    }
    public Node bfsSearch(Node intialNode){
        Queue<Node> frontier = new LinkedList<Node>();
        Hashtable<String, Boolean> inFrontier = new Hashtable<>();
        Hashtable<String, Boolean> explored = new Hashtable<>();
        if(isGoal(intialNode)){
            return intialNode;
        }
        frontier.add(intialNode);
        inFrontier.put(intialNode.hash(),true);
        while (!frontier.isEmpty()){
            Node temp = frontier.poll();
            inFrontier.remove(temp.hash());
            explored.put(temp.hash(),true);
            ArrayList<Node> children = temp.successor();
            for(int i = 0;i<children.size();i++){
                if(!(inFrontier.containsKey(children.get(i).hash())) && !(explored.containsKey(children.get(i).hash()))) {
                    if (isGoal(children.get(i))) {
                        return children.get(i);
                    }
                    frontier.add(children.get(i));
                    inFrontier.put(children.get(i).hash(), true);
                }
            }
        }
        return null;
    }

    public void search2(Node initialNode,Player player){

        BDS bds=new BDS();
        Node upsideNode =bds.bfsSearch(initialNode) ;


        //bds search
        //use to bfs for search
        Queue<Node> frontier = new LinkedList<Node>();

        Queue<Node> upsideFrontier = new LinkedList<Node>();

        Hashtable<String, Boolean> inFrontier = new Hashtable<>();
        Hashtable<String, Boolean> explored = new Hashtable<>();

        Hashtable<String, Boolean> upsideInFrontier = new Hashtable<>();
        Hashtable<String, Boolean> upsideExplored = new Hashtable<>();
        if(isGoal(initialNode)){
            result(initialNode);
            return;
        }


        frontier.add(initialNode);
        inFrontier.put(initialNode.hash(),true);

        upsideFrontier.add(upsideNode);
        upsideInFrontier.put(upsideNode.hash(),true);

        while (!frontier.isEmpty()|!upsideFrontier.isEmpty()) {
            Node temp = frontier.poll();
            inFrontier.remove(temp.hash());
            explored.put(temp.hash(),true);
            ArrayList<Node> children = temp.successor();

            Node upsideTemp = upsideFrontier.poll();
            upsideInFrontier.remove(upsideTemp.hash());
            upsideExplored.put(upsideTemp.hash(),true);
            ArrayList<Node> upsideChildren = upsideTemp.upsideSuccessor();

            for(int i = 0;i<children.size();i++){
                if(!(inFrontier.containsKey(children.get(i).hash())) && !(explored.containsKey(children.get(i).hash()))) {
                    if (isGoal(children.get(i))) {
                        // result(children.get(i));
                        System.out.println("reach Goal side");
                        return;
                    }
                    frontier.add(children.get(i));
                    inFrontier.put(children.get(i).hash(), true);
                }
            }

            for(int i = 0;i<upsideChildren.size();i++){
                if(!(upsideInFrontier.containsKey(upsideChildren.get(i).hash())) && !(upsideExplored.containsKey(upsideChildren.get(i).hash()))) {

                    upsideFrontier.add(upsideChildren.get(i));
                    upsideInFrontier.put(upsideChildren.get(i).hash(), true);
                }
            }


            ArrayList list = new ArrayList(frontier);
            ArrayList upsidelist = new ArrayList(upsideFrontier);



            for (int i = 0; i < list.size(); i++) {

                Node n=(Node) list.get(i);

                String nHash=n.hash();
                for (int j = 0; j < upsidelist.size(); j++) {
                    Node uN = (Node) upsidelist.get(j);
                    String unHash= uN.hash();
                    if (nHash.equals(unHash)){
                        System.out.println("found from both dirction");
                        System.out.println("n hash is "+n.hash());
                        System.out.println("un hash is "+uN.hash());
                        System.out.println("side depth "+n.depth);
                        System.out.println("upside depth "+uN.depth);
                        result(n);
                        System.out.println("************");

                        return;
                    }
                    //upsideFrontier.add(uN);

                }
                //frontier.add(n);

            }



        }


    }




}

public class test4 {

    public static void main(String[] args) {

        Visualizer visualizer = new Visualizer();
        //indexes start from zero

        Player player = new Player(0,0,105,5);
        //indexes start from zero
        Map map = new Map(5,5);
        //initialization of the map
        //G for normal ground
        //S for swamp
        //K for key
        //C for castle
        //L for Loot
        //B for bandit
        //W for wildAnimals
        //P for bridge
        map.addEntity(0,0,new BaseEntity('G'));
        map.addEntity(0,1,new Bridge());
        map.addEntity(0,2,new Bandit(100));
        map.addEntity(0,3,new Loot(50,100));
        map.addEntity(0,4,new WildAnimall(100));
        map.addEntity(1,0,new BaseEntity('S'));
        map.addEntity(1,1,new  Loot(100,100));
        map.addEntity(1,2,new Bridge());
        map.addEntity(1,3,new Bandit(100));
        map.addEntity(1,4,new Loot(100,100));
        map.addEntity(2,0,new BaseEntity('K'));
        map.addEntity(2,1,new WildAnimall(100));
        map.addEntity(2,2,new BaseEntity('S'));
        map.addEntity(2,3,new BaseEntity('S'));
        map.addEntity(2,4,new Loot(500,500));
        map.addEntity(3,0,new BaseEntity('G'));
        map.addEntity(3,1,new BaseEntity('G'));
        map.addEntity(3,2,new WildAnimall(800));
        map.addEntity(3,3,new Bandit(100));
        map.addEntity(3,4,new BaseEntity('S'));
        map.addEntity(4,0,new Bandit(100));
        map.addEntity(4,1,new Bandit(100));
        map.addEntity(4,2,new Loot(100,100));
        map.addEntity(4,3,new WildAnimall(100));
        map.addEntity(4,4,new BaseEntity('C'));
        System.out.println("player position: "+player.i+" "+player.j);

        //print method prints the map
        map.print();
        visualizer.printMap(map, player);
        Node node = new Node(player,map,null,null,0);
        BFS bfs = new BFS();
        bfs.search(node);
        System.out.println("------------------------------------------------------");
        System.out.println("DFS Search!");
        DFS dfs =new DFS();
        dfs.search(node);

        System.out.println("------------------------------------------------------");
        System.out.println("DLS Search!");
        IDS ids=new IDS();
        ids.dlsSearch(node, 11);
        System.out.println("------------------------------------------------------");
        System.out.println("IDS Search!");
        ids.search(node);
        System.out.println("------------------------------------------------------");
        System.out.println("BDS Search!");
        BDS bds = new BDS();
        //bds.search(node,player);

        System.out.println("------------------------------------------------------");
        System.out.println("BDS Search2!");
        bds.search2(node,player);



    }





}

public class test11 {
    public static void main(String[] args) {
        Player player = new Player(0,4,220,140);
        Visualizer visualizer = new Visualizer();
        //indexes start from zero
        System.out.println("player position: "+player.i+" "+player.j);
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
        map.addEntity(0,0,new BaseEntity('C'));
        map.addEntity(0,1,new Bandit(450));
        map.addEntity(0,2,new Bridge());
        map.addEntity(0,3,new Bridge());
        map.addEntity(0,4,new BaseEntity('G'));
        map.addEntity(1,0,new WildAnimall(200));
        map.addEntity(1,1,new  BaseEntity('S'));
        map.addEntity(1,2,new BaseEntity('G'));
        map.addEntity(1,3,new BaseEntity('G'));
        map.addEntity(1,4,new BaseEntity('S'));
        map.addEntity(2,0,new Bridge());
        map.addEntity(2,1,new BaseEntity('G'));
        map.addEntity(2,2,new Bandit(100));
        map.addEntity(2,3,new BaseEntity('G'));
        map.addEntity(2,4,new BaseEntity('G'));
        map.addEntity(3,0,new BaseEntity('G'));
        map.addEntity(3,1,new BaseEntity('G'));
        map.addEntity(3,2,new Loot(100,100));
        map.addEntity(3,3,new WildAnimall(60));
        map.addEntity(3,4,new Bandit(400));
        map.addEntity(4,0,new BaseEntity('K'));
        map.addEntity(4,1,new BaseEntity('S'));
        map.addEntity(4,2,new BaseEntity('G'));
        map.addEntity(4,3,new BaseEntity('G'));
        map.addEntity(4,4,new WildAnimall(6000));
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
        // bds.search(node,player);
        System.out.println("------------------------------------------------------");
        System.out.println("BDS Search2!");
        bds.search2(node,player);
        // bds.search(node,player);
        System.out.println("------------------------------------------------------");
        System.out.println("A* Search!");
        Astar astar=new Astar();
        System.out.println(astar.hurestic(node));
        astar.search(node);
        System.out.println("------------------------------------------------------");
        System.out.println("IDA* Search!");
        IDAstar idAstar=new IDAstar();
        idAstar.search(node);

    }
}

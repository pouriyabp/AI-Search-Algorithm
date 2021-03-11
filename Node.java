import java.util.ArrayList;

public class Node {
    public Map map;
    public Player player;
    public Node parentNode;
    public String priviousAction;
    public int depth;

    public Node(Player player,Map map,Node parentNode,String priviousAction,int depth){
        this.map = map.copy();
        this.player = new Player(player.i,player.j,player.money,player.food,player.haskey);
        this.parentNode = parentNode;
        this.priviousAction = priviousAction;
        this.depth=depth;
    }

    public String hash(){
        int key = player.haskey ? 1 : 0;
        String result = player.i+","+player.j+","+player.money+","+player.food+","+key;
        int size = map.game.size();
        for(int i = 0;i<size;i++){
            if(map.game.get(i) instanceof Bridge){
                key = ((Bridge) map.game.get(i)).traveresd ? 1 : 0;
                result += key;
            }
            else if(map.game.get(i) instanceof Loot){
                key = ((Loot) map.game.get(i)).used ? 1 : 0;
                result += key;
            }
        }
        return result;
    }

    /*
    public String hash(){
        int key = player.haskey ? 1 : 0;
        String result = player.i+""+player.j+""+player.money+""+player.food+""+key;
        int size = map.game.size();
        for(int i = 0;i<size;i++){
            if(map.game.get(i) instanceof Bridge){
                key = ((Bridge) map.game.get(i)).traveresd ? 1 : 0;
                result += key;
            }
            else if(map.game.get(i) instanceof Loot){
                key = ((Loot) map.game.get(i)).used ? 1 : 0;
                result += key;
            }
        }
        return result;
    }
    */




    public ArrayList<Node> successor(){
        ArrayList<Node> result = new ArrayList<Node>();
        if(this.player.j<this.map.cols-1) {//player can move right
            BaseEntity entity = this.map.at(this.player.i,this.player.j+1);
            if(entity.name != 'S'){
                if(entity.name == 'G'){
                    Node temp = new Node(this.player,this.map,this,"right",this.depth+1);
                    temp.player.j ++;
                    result.add(temp);
                }
               else if(entity.name == 'P' ){
                    if(!((Bridge)entity).traveresd) {
                        Node temp = new Node(this.player, this.map, this, "right",this.depth+1);
                        temp.player.j++;
                        ((Bridge) temp.map.at(temp.player.i, temp.player.j)).traveresd = true;
                        result.add(temp);
                    }
               }
               else if(entity.name == 'C'){
                    if(player.haskey){
                        Node temp = new Node(this.player,this.map,this,"right",this.depth+1);
                        temp.player.j ++;
                        result.add(temp);
                    }
                }
                else if(entity.name == 'K'){
                    Node temp = new Node(this.player,this.map,this,"right",this.depth+1);
                    temp.player.j ++;
                    temp.player.haskey = true;
                    result.add(temp);
                }
                else if(entity.name == 'B'){
                    Bandit bandit = (Bandit) entity;
                    if(this.player.money>bandit.power){
                        Node temp = new Node(this.player,this.map,this,"right",this.depth+1);
                        temp.player.j++;
                        bandit.takeMoney(temp.player);
                        result.add(temp);
                    }
                }
                else if(entity.name == 'W'){
                    WildAnimall wildAnimall = (WildAnimall) entity;
                    if(this.player.food>wildAnimall.power){
                        Node temp = new Node(this.player,this.map,this,"right",this.depth+1);
                        temp.player.j ++;
                        wildAnimall.takeFood(temp.player);
                        result.add(temp);
                    }
                }
                else if(entity.name == 'L'){
                    Loot loot = (Loot)entity;
                    if(loot.used){
                        Node temp = new Node(this.player,this.map,this,"right",this.depth+1);
                        temp.player.j ++;
                        result.add(temp);
                    }
                    else{
                        Node temp1 = new Node(this.player,this.map,this,"right, use money",this.depth+1);
                        temp1.player.j ++;
                        ((Loot)temp1.map.at(temp1.player.i,temp1.player.j)).useMoney(temp1.player);
                        result.add(temp1);
                        Node temp2 = new Node(this.player,this.map,this,"right, use food",this.depth+1);
                        temp2.player.j ++;
                        ((Loot)temp2.map.at(temp2.player.i,temp2.player.j)).useFood(temp2.player);
                        result.add(temp2);
                    }
                }
            }
        }
        if(this.player.j>0) {//player can move left
            BaseEntity entity = this.map.at(this.player.i,this.player.j-1);
            if(entity.name != 'S'){
                if(entity.name == 'G'){
                    Node temp = new Node(this.player,this.map,this,"left",this.depth+1);
                    temp.player.j --;
                    result.add(temp);
                }
                else if(entity.name == 'P' ){
                    if(!((Bridge)entity).traveresd) {
                        Node temp = new Node(this.player, this.map, this, "left",this.depth+1);
                        temp.player.j--;
                        ((Bridge) temp.map.at(temp.player.i, temp.player.j)).traveresd = true;
                        result.add(temp);
                    }
                }
                else if(entity.name == 'C'){
                    if(player.haskey){
                        Node temp = new Node(this.player,this.map,this,"left",this.depth+1);
                        temp.player.j --;
                        result.add(temp);
                    }
                }
                else if(entity.name == 'K'){
                    Node temp = new Node(this.player,this.map,this,"left",this.depth+1);
                    temp.player.j --;
                    temp.player.haskey = true;
                    result.add(temp);
                }
                else if(entity.name == 'B'){
                    Bandit bandit = (Bandit) entity;
                    if(this.player.money>bandit.power){
                        Node temp = new Node(this.player,this.map,this,"left",this.depth+1);
                        temp.player.j--;
                        bandit.takeMoney(temp.player);
                        result.add(temp);
                    }
                }
                else if(entity.name == 'W'){
                    WildAnimall wildAnimall = (WildAnimall) entity;
                    if(this.player.food>wildAnimall.power){
                        Node temp = new Node(this.player,this.map,this,"left",this.depth+1);
                        temp.player.j --;
                        wildAnimall.takeFood(temp.player);
                        result.add(temp);
                    }
                }
                else if(entity.name == 'L'){
                    Loot loot = (Loot)entity;
                    if(loot.used){
                        Node temp = new Node(this.player,this.map,this,"left",this.depth+1);
                        temp.player.j --;
                        result.add(temp);
                    }
                    else{
                        Node temp1 = new Node(this.player,this.map,this,"left, use money",this.depth+1);
                        temp1.player.j --;
                        ((Loot)temp1.map.at(temp1.player.i,temp1.player.j)).useMoney(temp1.player);
                        result.add(temp1);
                        Node temp2 = new Node(this.player,this.map,this,"left, use food",this.depth+1);
                        temp2.player.j --;
                        ((Loot)temp2.map.at(temp2.player.i,temp2.player.j)).useFood(temp2.player);
                        result.add(temp2);
                    }
                }
            }
        }
        if(this.player.i>0) {//player can move up
            BaseEntity entity = this.map.at(this.player.i-1,this.player.j);
            if(entity.name != 'S'){
                if(entity.name == 'G'){
                    Node temp = new Node(this.player,this.map,this,"up",this.depth+1);
                    temp.player.i --;
                    result.add(temp);
                }
                else if(entity.name == 'P' ){
                    if(!((Bridge)entity).traveresd) {
                        Node temp = new Node(this.player, this.map, this, "up",this.depth+1);
                        temp.player.i--;
                        ((Bridge) temp.map.at(temp.player.i, temp.player.j)).traveresd = true;
                        result.add(temp);
                    }
                }
                else if(entity.name == 'C'){
                    if(player.haskey){
                        Node temp = new Node(this.player,this.map,this,"up",this.depth+1);
                        temp.player.i --;
                        result.add(temp);
                    }
                }
                else if(entity.name == 'K'){
                    Node temp = new Node(this.player,this.map,this,"up",this.depth+1);
                    temp.player.i --;
                    temp.player.haskey = true;
                    result.add(temp);
                }
                else if(entity.name == 'B'){
                    Bandit bandit = (Bandit) entity;
                    if(this.player.money>bandit.power){
                        Node temp = new Node(this.player,this.map,this,"up",this.depth+1);
                        temp.player.i--;
                        bandit.takeMoney(temp.player);
                        result.add(temp);
                    }
                }
                else if(entity.name == 'W'){
                    WildAnimall wildAnimall = (WildAnimall) entity;
                    if(this.player.food>wildAnimall.power){
                        Node temp = new Node(this.player,this.map,this,"up",this.depth+1);
                        temp.player.i --;
                        wildAnimall.takeFood(temp.player);
                        result.add(temp);
                    }
                }
                else if(entity.name == 'L'){
                    Loot loot = (Loot)entity;
                    if(loot.used){
                        Node temp = new Node(this.player,this.map,this,"up",this.depth+1);
                        temp.player.i --;
                        result.add(temp);
                    }
                    else{
                        Node temp1 = new Node(this.player,this.map,this,"up, use money",this.depth+1);
                        temp1.player.i --;
                        ((Loot)temp1.map.at(temp1.player.i,temp1.player.j)).useMoney(temp1.player);
                        result.add(temp1);
                        Node temp2 = new Node(this.player,this.map,this,"up, use food",this.depth+1);
                        temp2.player.i --;
                        ((Loot)temp2.map.at(temp2.player.i,temp2.player.j)).useFood(temp2.player);
                        result.add(temp2);
                    }
                }
            }
        }
        if(this.player.i<this.map.rows-1) {//player can move down
            BaseEntity entity = this.map.at(this.player.i+1,this.player.j);
            if(entity.name != 'S'){
                if(entity.name == 'G'){
                    Node temp = new Node(this.player,this.map,this,"down",this.depth+1);
                    temp.player.i ++;
                    result.add(temp);
                }
                else if(entity.name == 'P' ){
                    if(!((Bridge)entity).traveresd) {
                        Node temp = new Node(this.player, this.map, this, "down",this.depth+1);
                        temp.player.i++;
                        ((Bridge) temp.map.at(temp.player.i, temp.player.j)).traveresd = true;
                        result.add(temp);
                    }
                }
                else if(entity.name == 'C'){
                    if(player.haskey){
                        Node temp = new Node(this.player,this.map,this,"down",this.depth+1);
                        temp.player.i ++;
                        result.add(temp);
                    }
                }
                else if(entity.name == 'K'){
                    Node temp = new Node(this.player,this.map,this,"down",this.depth+1);
                    temp.player.i ++;
                    temp.player.haskey = true;
                    result.add(temp);
                }
                else if(entity.name == 'B'){
                    Bandit bandit = (Bandit) entity;
                    if(this.player.money>bandit.power){
                        Node temp = new Node(this.player,this.map,this,"down",this.depth+1);
                        temp.player.i++;
                        bandit.takeMoney(temp.player);
                        result.add(temp);
                    }
                }
                else if(entity.name == 'W'){
                    WildAnimall wildAnimall = (WildAnimall) entity;
                    if(this.player.food>wildAnimall.power){
                        Node temp = new Node(this.player,this.map,this,"down",this.depth+1);
                        temp.player.i ++;
                        wildAnimall.takeFood(temp.player);
                        result.add(temp);
                    }
                }
                else if(entity.name == 'L'){
                    Loot loot = (Loot)entity;
                    if(loot.used){
                        Node temp = new Node(this.player,this.map,this,"down",this.depth+1);
                        temp.player.i ++;
                        result.add(temp);
                    }
                    else{
                        Node temp1 = new Node(this.player,this.map,this,"down, use money",this.depth+1);
                        temp1.player.i ++;
                        ((Loot)temp1.map.at(temp1.player.i,temp1.player.j)).useMoney(temp1.player);
                        result.add(temp1);
                        Node temp2 = new Node(this.player,this.map,this,"down, use food",this.depth+1);
                        temp2.player.i ++;
                        ((Loot)temp2.map.at(temp2.player.i,temp2.player.j)).useFood(temp2.player);
                        result.add(temp2);
                    }
                }
            }
        }
        return result;
    }





    public ArrayList<Node> upsideSuccessor(){
        ArrayList<Node> result = new ArrayList<Node>();
        if(this.player.j<this.map.cols-1) {//player can move right
            BaseEntity entity = this.map.at(this.player.i,this.player.j+1);
            if(entity.name != 'S'){
                if(entity.name == 'G'){
                    Node temp = new Node(this.player,this.map,this,"right",this.depth+1);
                    temp.player.j ++;
                    result.add(temp);
                }
                else if(entity.name == 'P' ){
                    if(((Bridge)entity).traveresd) {
                        Node temp = new Node(this.player, this.map, this, "right",this.depth+1);
                        temp.player.j++;
                        ((Bridge) temp.map.at(temp.player.i, temp.player.j)).traveresd = false;
                        result.add(temp);
                    }
                }
                else if(entity.name == 'C'){
                    if(player.haskey){
                        Node temp = new Node(this.player,this.map,this,"right",this.depth+1);
                        temp.player.j ++;
                        result.add(temp);
                    }
                }
                else if(entity.name == 'K'){
                    Node temp = new Node(this.player,this.map,this,"right",this.depth+1);
                    temp.player.j ++;
                    temp.player.haskey = false;
                    result.add(temp);
                }
                else if(entity.name == 'B'){
                    Bandit bandit = (Bandit) entity;
                    if(this.player.money<bandit.power){
                        Node temp = new Node(this.player,this.map,this,"right",this.depth+1);
                        temp.player.j++;
                        bandit.giveMoney(temp.player);
                        result.add(temp);
                    }
                }
                else if(entity.name == 'W'){
                    WildAnimall wildAnimall = (WildAnimall) entity;
                    if(this.player.food<wildAnimall.power){
                        Node temp = new Node(this.player,this.map,this,"right",this.depth+1);
                        temp.player.j ++;
                        wildAnimall.giveFood(temp.player);
                        result.add(temp);
                    }
                }
                else if(entity.name == 'L'){
                    Loot loot = (Loot)entity;
                    if(!loot.used){
                        Node temp = new Node(this.player,this.map,this,"right",this.depth+1);
                        temp.player.j ++;
                        result.add(temp);
                    }
                    else{
                        Node temp1 = new Node(this.player,this.map,this,"right, use money",this.depth+1);
                        temp1.player.j ++;
                        ((Loot)temp1.map.at(temp1.player.i,temp1.player.j)).backMoney(temp1.player);
                        result.add(temp1);
                        Node temp2 = new Node(this.player,this.map,this,"right, use food",this.depth+1);
                        temp2.player.j ++;
                        ((Loot)temp2.map.at(temp2.player.i,temp2.player.j)).backFood(temp2.player);
                        result.add(temp2);
                    }
                }
            }
        }
        if(this.player.j>0) {//player can move left
            BaseEntity entity = this.map.at(this.player.i,this.player.j-1);
            if(entity.name != 'S'){
                if(entity.name == 'G'){
                    Node temp = new Node(this.player,this.map,this,"left",this.depth+1);
                    temp.player.j --;
                    result.add(temp);
                }
                else if(entity.name == 'P' ){
                    if(((Bridge)entity).traveresd) {
                        Node temp = new Node(this.player, this.map, this, "left",this.depth+1);
                        temp.player.j--;
                        ((Bridge) temp.map.at(temp.player.i, temp.player.j)).traveresd = false;
                        result.add(temp);
                    }
                }
                else if(entity.name == 'C'){
                    if(player.haskey){
                        Node temp = new Node(this.player,this.map,this,"left",this.depth+1);
                        temp.player.j --;
                        result.add(temp);
                    }
                }
                else if(entity.name == 'K'){
                    Node temp = new Node(this.player,this.map,this,"left",this.depth+1);
                    temp.player.j --;
                    temp.player.haskey = false;
                    result.add(temp);
                }
                else if(entity.name == 'B'){
                    Bandit bandit = (Bandit) entity;
                    if(this.player.money<bandit.power){
                        Node temp = new Node(this.player,this.map,this,"left",this.depth+1);
                        temp.player.j--;
                        bandit.giveMoney(temp.player);
                        result.add(temp);
                    }
                }
                else if(entity.name == 'W'){
                    WildAnimall wildAnimall = (WildAnimall) entity;
                    if(this.player.food<wildAnimall.power){
                        Node temp = new Node(this.player,this.map,this,"left",this.depth+1);
                        temp.player.j --;
                        wildAnimall.giveFood(temp.player);
                        result.add(temp);
                    }
                }
                else if(entity.name == 'L'){
                    Loot loot = (Loot)entity;
                    if(!loot.used){
                        Node temp = new Node(this.player,this.map,this,"left",this.depth+1);
                        temp.player.j --;
                        result.add(temp);
                    }
                    else{
                        Node temp1 = new Node(this.player,this.map,this,"left, use money",this.depth+1);
                        temp1.player.j --;
                        ((Loot)temp1.map.at(temp1.player.i,temp1.player.j)).backMoney(temp1.player);
                        result.add(temp1);
                        Node temp2 = new Node(this.player,this.map,this,"left, use food",this.depth+1);
                        temp2.player.j --;
                        ((Loot)temp2.map.at(temp2.player.i,temp2.player.j)).backFood(temp2.player);
                        result.add(temp2);
                    }
                }
            }
        }
        if(this.player.i>0) {//player can move up
            BaseEntity entity = this.map.at(this.player.i-1,this.player.j);
            if(entity.name != 'S'){
                if(entity.name == 'G'){
                    Node temp = new Node(this.player,this.map,this,"up",this.depth+1);
                    temp.player.i --;
                    result.add(temp);
                }
                else if(entity.name == 'P' ){
                    if(((Bridge)entity).traveresd) {
                        Node temp = new Node(this.player, this.map, this, "up",this.depth+1);
                        temp.player.i--;
                        ((Bridge) temp.map.at(temp.player.i, temp.player.j)).traveresd = false;
                        result.add(temp);
                    }
                }
                else if(entity.name == 'C'){
                    if(player.haskey){
                        Node temp = new Node(this.player,this.map,this,"up",this.depth+1);
                        temp.player.i --;
                        result.add(temp);
                    }
                }
                else if(entity.name == 'K'){
                    Node temp = new Node(this.player,this.map,this,"up",this.depth+1);
                    temp.player.i --;
                    temp.player.haskey = false;
                    result.add(temp);
                }
                else if(entity.name == 'B'){
                    Bandit bandit = (Bandit) entity;
                    if(this.player.money<bandit.power){
                        Node temp = new Node(this.player,this.map,this,"up",this.depth+1);
                        temp.player.i--;
                        bandit.giveMoney(temp.player);
                        result.add(temp);
                    }
                }
                else if(entity.name == 'W'){
                    WildAnimall wildAnimall = (WildAnimall) entity;
                    if(this.player.food<wildAnimall.power){
                        Node temp = new Node(this.player,this.map,this,"up",this.depth+1);
                        temp.player.i --;
                        wildAnimall.giveFood(temp.player);
                        result.add(temp);
                    }
                }
                else if(entity.name == 'L'){
                    Loot loot = (Loot)entity;
                    if(!loot.used){
                        Node temp = new Node(this.player,this.map,this,"up",this.depth+1);
                        temp.player.i --;
                        result.add(temp);
                    }
                    else{
                        Node temp1 = new Node(this.player,this.map,this,"up, use money",this.depth+1);
                        temp1.player.i --;
                        ((Loot)temp1.map.at(temp1.player.i,temp1.player.j)).backMoney(temp1.player);
                        result.add(temp1);
                        Node temp2 = new Node(this.player,this.map,this,"up, use food",this.depth+1);
                        temp2.player.i --;
                        ((Loot)temp2.map.at(temp2.player.i,temp2.player.j)).backFood(temp2.player);
                        result.add(temp2);
                    }
                }
            }
        }
        if(this.player.i<this.map.rows-1) {//player can move down
            BaseEntity entity = this.map.at(this.player.i+1,this.player.j);
            if(entity.name != 'S'){
                if(entity.name == 'G'){
                    Node temp = new Node(this.player,this.map,this,"down",this.depth+1);
                    temp.player.i ++;
                    result.add(temp);
                }
                else if(entity.name == 'P' ){
                    if(((Bridge)entity).traveresd) {
                        Node temp = new Node(this.player, this.map, this, "down",this.depth+1);
                        temp.player.i++;
                        ((Bridge) temp.map.at(temp.player.i, temp.player.j)).traveresd = false;
                        result.add(temp);
                    }
                }
                else if(entity.name == 'C'){
                    if(player.haskey){
                        Node temp = new Node(this.player,this.map,this,"down",this.depth+1);
                        temp.player.i ++;
                        result.add(temp);
                    }
                }
                else if(entity.name == 'K'){
                    Node temp = new Node(this.player,this.map,this,"down",this.depth+1);
                    temp.player.i ++;
                    temp.player.haskey = false;
                    result.add(temp);
                }
                else if(entity.name == 'B'){
                    Bandit bandit = (Bandit) entity;
                    if(this.player.money<bandit.power){
                        Node temp = new Node(this.player,this.map,this,"down",this.depth+1);
                        temp.player.i++;
                        bandit.giveMoney(temp.player);
                        result.add(temp);
                    }
                }
                else if(entity.name == 'W'){
                    WildAnimall wildAnimall = (WildAnimall) entity;
                    if(this.player.food<wildAnimall.power){
                        Node temp = new Node(this.player,this.map,this,"down",this.depth+1);
                        temp.player.i ++;
                        wildAnimall.giveFood(temp.player);
                        result.add(temp);
                    }
                }
                else if(entity.name == 'L'){
                    Loot loot = (Loot)entity;
                    if(!loot.used){
                        Node temp = new Node(this.player,this.map,this,"down",this.depth+1);
                        temp.player.i ++;
                        result.add(temp);
                    }
                    else{
                        Node temp1 = new Node(this.player,this.map,this,"down, use money",this.depth+1);
                        temp1.player.i ++;
                        ((Loot)temp1.map.at(temp1.player.i,temp1.player.j)).backMoney(temp1.player);
                        result.add(temp1);
                        Node temp2 = new Node(this.player,this.map,this,"down, use food",this.depth+1);
                        temp2.player.i ++;
                        ((Loot)temp2.map.at(temp2.player.i,temp2.player.j)).backFood(temp2.player);
                        result.add(temp2);
                    }
                }
            }
        }
        return result;
    }
}

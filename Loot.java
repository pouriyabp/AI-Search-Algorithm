public class Loot extends BaseEntity{
    public int money;
    public int food;
    public boolean used;
    public Loot(int money,int food){
        super('L');
        this.money = money;
        this.food = food;
        this.used = false;
    }
    public void useMoney(Player player){
        player.changeMoney(this.money);
        this.used = true;
    }
    public void useFood(Player player){
        player.changeFood(this.food);
        this.used = true;
    }

    public void backMoney(Player player){
        int t= this.money;
        t= t*-1;
        player.changeMoney(t);
        this.used = false;
    }
    public void backFood(Player player){
        int t= this.food;
        t= t*-1;
        player.changeFood(t);
        this.used = false;
    }

    

    @Override
    public Loot copy(){
        Loot loot = new Loot(this.money,this.food);
        loot.used = this.used;
        return loot;
    }
}

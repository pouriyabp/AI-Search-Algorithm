public class Astar
{


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
        int g=0;
        int [] locationOfCastel=findCastel(node);
        int [] locationOfPlayer=new int[2];
        locationOfPlayer [0]= node.player.i;
        locationOfPlayer[1]= node.player.j;

        int tempI= Math.abs(locationOfCastel[0]-locationOfPlayer[0]);
        int tempJ=  Math.abs(locationOfCastel[1]-locationOfPlayer[1]);
        g=tempI+tempJ;
        return g;
    }





}

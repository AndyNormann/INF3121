package oblig5;

public class BoardComponents {
    private Square member[];
    private int amt;
    
    BoardComponents(int size){
        amt = 0;
        member = new Square[size];
    }
    
    public void add(Square s){
        member[amt] = s;
        amt++;
    }
    public void set(Square s, int index){
        member[index] = s;
    }
    public Square get(int index){
        return member[index];
    }
}

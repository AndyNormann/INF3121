package oblig5;
import java.util.ArrayList;

public class SudokuContainer {
    private ArrayList<Board> boards;
    SudokuContainer(){
        boards = new ArrayList<>();
    }
    
    public void insert(Board b){
        boards.add(b);
    }
    public Board get(int index){
        return boards.get(index);
    }
    public int getSolutionCount(){
        return boards.size();
    }
    
    public void printBoards(){
        Board current;
        for (int i = 0; i < boards.size(); i++) {
            current = boards.get(i);
            Square cSquare = current.getSq(0, 0);
            for (int j = 0; j < current.dimension(); j++) {
                for (int k = 0; k < current.dimension(); k++) {
                    /*System.out.printf("%c", cSquare.get());
                    cSquare = cSquare.getNext();*/
                    System.out.printf("%c", current.getSq(j, k).get());
                }
                System.out.printf("\n");
            }
            System.out.printf("\n");
        }
    }
}

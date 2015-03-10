package oblig5;

public class Square {
    private char data;
    private Row myRow;
    private Column myColumn;
    private Box myBox;
    private Board myBoard;
    private int a, b;
    private Square next;
    private SudokuContainer myContainer;
    Square(char c){
        data = c;
        next = null;
    }
    public char get(){
        return data;
    }
    public void set(char c){
        if(data=='.'){
            data = c;
        }
    }
    public void setContainer(SudokuContainer s){
        myContainer = s;
    }
    public void setNext(Square s){
        next = s;
    }
    public Square getNext(){
        return next;
    }
    public void setLoc(int a, int b){
        this.a = a;
        this.b = b;
    }
    public void setBoard(Board b){
        myBoard = b;
    }
    public void setR(Row r){
        myRow = r;
    }
    public void setC(Column c){
        myColumn = c;
    }
    public void setB(Box b){
        myBox = b;
    }
    public Row getRow(){
        return myRow; 
    }
    public Box getBox(){
        return myBox; 
    }
    public Column getCol(){
        return myColumn; 
    }
    public void fill(char[] c, int dim){
        if(data == '.'){
            if(!valid(c, dim)){
                return;
            }
            for (int i = 0; i < c.length; i++) {
                if(test(c[i], dim)){
                    Board board = new Board(myBoard.getHeight(), myBoard.getWidth());
                    board.copyBoard(myBoard);
                    board.structure();
                    board.getSq(a, b).set(c[i]);
                    
                    if(board.getSq(a, b).next != null){
                        board.getSq(a, b).next.fill(c, dim);
                    }else{
                        board.getSq(a, b).set(c[i]);
                        if(board.getSq(a, b).testSolution()){
                            myContainer.insert(board);
                            continue;
                        }
                    }
                }
            }
        }else{
            if(next != null){
                next.fill(c, dim);
            }
        }
        if(testSolution()){
            myContainer.insert(myBoard);
        }
    }
    
    private boolean valid(char[] c, int dim){
        for(int i = 0; i<c.length; i++){
            if(test(c[i], dim)){
                return true;
            }
        }
        return false;
    }
    private boolean test(char c, int dim){
        for (int i = 0; i < dim; i++) {
            if(myRow.get(i).get() == c || myColumn.get(i).get() == c || myBox.get(i).get() == c){
                return false;
            }
        }
        return true;
    }
    private boolean testSolution(){
        for(int i = 0; i<myBoard.dimension(); i++){
            for(int j = 0; j<myBoard.dimension(); j++){
                if(myBoard.getSq(i, j).get() == '.'){
                    return false;
                }
            }
        }
        return true;
    }
}

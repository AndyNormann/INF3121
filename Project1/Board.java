package oblig5;



public class Board {
    private final Square board[][];
    private final Row rows[];
    private final Column columns[];
    private final Box boxes[];
    private int boxHeight;
    private int boxWidth;
    private SudokuContainer myContainer;
    Board(int x, int y){
        boxHeight = x;
        boxWidth = y;
        board = new Square[x*y][x*y];
        rows = new Row[x*y];
        columns = new Column[x*y];
        boxes = new Box[x*y];
    }
    public void setContainer(SudokuContainer s){
        myContainer = s;
    }
    public SudokuContainer getContainer(){
        return myContainer;
    }
    public int getHeight(){
        return boxHeight;
    }
    public int getWidth(){
        return boxWidth;
    }
    
    public int dimension(){
        return boxHeight*boxWidth;
    }
    
    public void structure(){
        //Reading rows and columns
        int amt = 0;
        Row row;
        Column col;
        for (int i = 0; i < boxHeight*boxWidth; i++) {
            row = new Row(boxHeight*boxWidth);
            col = new Column(boxHeight*boxWidth);
            for (int j = 0; j < boxHeight*boxWidth; j++) {
                
                if(i == (boxHeight*boxWidth)-1 && j == (boxHeight*boxWidth)-1){
                    board[i][j].setNext(null);
                }else if(j == (boxHeight*boxWidth)-1){
                    board[i][j].setNext(board[i+1][0]);
                }else{
                    board[i][j].setNext(board[i][j+1]);
                }
                
                board[i][j].setR(row);
                board[j][i].setC(col);
                row.add(board[i][j]);
                col.add(board[j][i]);
                board[i][j].setLoc(i, j);
                board[i][j].setBoard(this);
                board[i][j].setContainer(myContainer);
            }
            rows[amt] = row;
            columns[amt] = col;
            amt++;
        }
        
        //Reading boxes
        Box box;
        amt = 0;
        for (int i = 0; i < boxHeight*boxWidth; i++) {
            box = new Box(boxHeight*boxWidth);
            for (int j = 0; j < boxHeight; j++) {
                for (int k = 0; k < boxWidth; k++) {
                    Square current = board[((i/boxHeight)*boxHeight)+j][((i%boxHeight)*boxWidth)+k];
                    box.add(current);
                    current.setB(box);
                }
            }
            boxes[amt] = box;
        }
        
       
    }
    
    
    
    public void setSq(Square s, int x, int y){
        board[x][y] = s;
    }
    public Square getSq(int x, int y){
        return board[x][y];
    }
    
    public void copyBoard(Board src){
        for(int i = 0; i < dimension(); i++){
            for(int j = 0; j<dimension(); j++){
                this.setSq(new Square(src.getSq(i, j).get()), i, j);
            }
        }
        this.setContainer(src.getContainer());
        this.structure();
    }
    public void printBoard(){
        for(int i = 0; i<dimension(); i++){
            for(int j = 0; j<dimension(); j++){
                System.out.printf("%c", board[i][j].get());
            }
            System.out.printf("\n");
        }
        System.out.printf("\n");
    }
}

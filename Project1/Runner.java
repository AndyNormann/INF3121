package oblig5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JFrame;

public class Runner {

    SudokuContainer container;//The container for solutions
    Board board; // The board we start with
    char letters[];
    
    Runner(String[] args) {
        container = new SudokuContainer();
        letters = new char [] {'1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E'};
        
        switch(args.length){
            case 0:
                GUI gui = new GUI(container, letters);
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gui.pack();
                gui.setVisible(true);
                break;
            case 1:
                fileReader(args[0]);
                solve(Arrays.copyOfRange(letters, 0, container.get(0).dimension()));
                container.printBoards();
                break;
            case 2:
                fileReader(args[0]);
                solve(Arrays.copyOfRange(letters, 0, container.get(0).dimension()));
                container.printBoards();
                writeToFile(args[1]);
                break;
        }
    }
    


    private void fileReader(String filename) {
        //Filereading
        Scanner s;
        File file = new File(filename);
        try {
            s = new Scanner(file);
            //Sets the boards dimensions
            int boxHeight = Integer.parseInt(s.nextLine());
            int boxWidth = Integer.parseInt(s.nextLine());
            board = new Board(boxHeight, boxWidth);
            
            //Reads the board
            for (int i = 0; i < boxHeight * boxWidth; i++) {
                String line = s.nextLine();
                for (int j = 0; j < boxHeight * boxWidth; j++) {
                    char c = line.charAt(j);
                    if(c == '.'){
                        EmptySquare es = new EmptySquare();
                        board.setSq((Square)es, i, j);
                    }else{
                        FilledSquare fs = new FilledSquare(c);
                        board.setSq((Square)fs, i, j);
                    }
                }
            }
            //Sets up rows, colums and boxes
            board.setContainer(container);
            board.structure();
            container.insert(board);

        } catch (FileNotFoundException e) {
            System.out.println("Infile not found");
            System.exit(0);
        }
    }
    
    private void writeToFile(String filename){
        File file = new File(filename);
        
        try{
            FileWriter writer = new FileWriter(file);
            for(int i = 1; i<container.getSolutionCount(); i++){
                for(int j = 0; j<container.get(0).dimension(); j++){
                    for(int k = 0; k<container.get(0).dimension(); k++){
                        writer.write(container.get(i).getSq(j, k).get());
                    }
                    writer.write("/");
                }
                writer.write("\n");
            }
            writer.close();
            
        }catch(IOException e){
            System.out.println("Outfile not found");
        }
    }
    
    private void solve(char chars[]){
        container.get(0).getSq(0, 0).fill(chars, container.get(0).dimension());
    }
    
}

package oblig5;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;



public class GUI extends JFrame implements ActionListener{
    SudokuContainer container;
    JFileChooser choose;
    Board board;
    char[] letters;
    GridLayout layout;
    JPanel panel;
    int show;
    
    GUI(SudokuContainer container, char[] c){
        show = 1;
        this.container = container;
        letters = c;
        this.setLayout(new BorderLayout());
        this.setResizable(true);
        
        choose = new JFileChooser();
        int retval = choose.showOpenDialog(this);
        if(retval == JFileChooser.APPROVE_OPTION){
            File file = choose.getSelectedFile();
            fileReader(file);
        }
        solve(Arrays.copyOfRange(letters, 0, container.get(0).dimension()));
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(container.get(0).dimension(), container.get(0).dimension()));
        JButton buton = new JButton("Next");
        this.add(buton, BorderLayout.NORTH);
        buton.addActionListener(this);
        for(int i = 0; i<container.get(0).dimension(); i++){
            for(int j = 0; j<container.get(0).dimension(); j++){
                String s = "" + container.get(0).getSq(i, j).get();
                JTextField field = new JTextField(s, 1);
                panel.add(field);
            }
        }
        this.add(panel, BorderLayout.SOUTH);    
    }
    
    private void fileReader(File file) {
        //Filereading
        Scanner s;
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
    private void solve(char chars[]){
        container.get(0).getSq(0, 0).fill(chars, container.get(0).dimension());
    }

    public void actionPerformed(ActionEvent e) {
        if(show >= container.getSolutionCount())
            return;
        this.remove(panel);
        JPanel panel = new JPanel();
        panel = new JPanel();
        panel.setLayout(new GridLayout(container.get(0).dimension(), container.get(0).dimension()));
        for(int i = 0; i<container.get(0).dimension(); i++){
            for(int j = 0; j<container.get(0).dimension(); j++){
                String s = "" + container.get(show).getSq(i, j).get();
                JTextField field = new JTextField(s, 1);
                panel.add(field);
            }
        }
        this.add(panel, BorderLayout.SOUTH);
        show++;
        this.pack();
        this.setVisible(true);
    }
}



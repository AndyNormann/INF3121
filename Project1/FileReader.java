package oblig5;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class FileReader {
    File file;
    Scanner s;
    FileReader(String filename){
        file = new File(filename);
        try{
            s = new Scanner(file);
            int boxHeight = Integer.parseInt(s.nextLine());
            int boxWidth = Integer.parseInt(s.nextLine());
            while(s.hasNext()){
                System.out.println(s.next());
            }
        }catch(FileNotFoundException e){
            System.out.println("File not found");
            System.exit(0);
        }
    }
}

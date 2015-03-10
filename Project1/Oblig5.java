/*
Hvis programmet kjøres med et argument, leses en sudoku inn fra den filen og så printes løsninger ut i terminalen.
Kjøres det med 2 argumenter leses det inn fra den ene, skrives ut til terminal og til filen som er det andre argumentet
Uten noen argumenter startes GUI med JFileChooser
*/
package oblig5;
public class Oblig5 {

    public static void main(String[] args) {
        //starts the program
        Runner run = new Runner(args);
    }
    
}

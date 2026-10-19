import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        String filePath = "src\\words.txt";
        ArrayList<String> w = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = bufferedReader.readLine())!=null){
                w.add(line.trim());
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Could not find file....");
        }
        catch (IOException e){
            System.out.println("Something went wrong...");
        }
        Random random = new Random();
        String word = w.get(random.nextInt(w.size()));
        int wrongGuesses = 0;

        Scanner scanner = new Scanner(System.in);
        ArrayList<Character> wordT = new ArrayList<>();
        for(int i = 0; i < word.length(); i++){
            wordT.add('_');
        }
        while (wrongGuesses < 6){
            System.out.println(getHangman(wrongGuesses));
            System.out.println("Word: ");

            for(int  i = 0; i < word.length();i++){
                System.out.print(wordT.get(i) + " ");
            }
            System.out.println();
            System.out.print("Guess a letter : ");
            char guess = scanner.next().toLowerCase().charAt(0);
            if(word.indexOf(guess)>=0){
                System.out.println("Correct guess!");
                for(int i = 0; i < word.length();i++){
                    if(guess == word.charAt(i)){
                        wordT.set(i,guess);
                    }

                }
                if(!wordT.contains('_')){
                    System.out.println("YOU WIN!!");
                    System.out.println("The word was : " + word);
                    break;
                }
            }
            else{
                wrongGuesses++;
                System.out.println("Wrong guess!....");
            }
        }
        if(wrongGuesses >= 6){
            System.out.println(getHangman(wrongGuesses));
            System.out.println("GAME OVER!!");
            System.out.println("The word was : " + word);
        }





        scanner.close();

    }
    static String getHangman(int wrongGuesses){
        return switch (wrongGuesses){
            case 0 -> """
                   
                   
                   
                       """;
            case 1 -> """
                       o
                   
                   
                       """;
            case 2 -> """
                       o
                       |
                   
                       """;
            case 3 -> """
                       o
                      <|
                     
                      """;
            case 4 -> """
                       o
                      <|>
                      """;
            case 5 -> """
                       o
                      <|>
                      (
                      """;
            case 6 -> """
                       o
                      <|>
                      ( )
                      """;
            default -> "";
        };
    }
}

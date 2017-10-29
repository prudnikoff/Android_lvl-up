import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Translate {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String lineEntered;
        do
        {
            lineEntered = scan.nextLine();
            String words[] = lineEntered.split(" ");
            for(String i: words) {
                String translatedWord = translate(i);
                System.out.print(translatedWord + " ");
            }
            System.out.println();
        }while(!lineEntered.equals("0"));
    }
    public static String translate(String word) {
        if (word.equals("0"))
            return "";
        try {
            BufferedReader readFile = new BufferedReader(new FileReader("res//ENRUS.txt"));
            String searchWord;
            while ((searchWord = readFile.readLine()) != null) {
                if (searchWord.equals(word)) {
                    String translatedWord = readFile.readLine();
                    if(translatedWord.contains("\t")) {                         //return 1 word instead string
                        int lastIndex = translatedWord.indexOf("\t");           //Example: you -> ты вы
                        translatedWord = translatedWord.substring(0, lastIndex);//translatedWord = "ты", not "ты вы"
                    }
                    return translatedWord;
                }
            }
        } catch (Exception e) {
            System.out.println("File isn't found");
        }
        return word;
    }
}
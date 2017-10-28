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
            System.out.println(translate(lineEntered));
        }while(!lineEntered.equals("0"));
    }
    public static String translate(String word)
    {
        try {
            BufferedReader readFile = new BufferedReader(new FileReader("res//ENRUS.txt"));
            String searchWord;
            while((searchWord=readFile.readLine())!=null)
            {
                if(searchWord.equals(word))
                {
                    String translatedWord = readFile.readLine();
                    return translatedWord;
                }
            }
        } catch (Exception e) {
            System.out.println("File isn't opened");
        }
        if(!word.equals("0")) {
            return "Something went wrong :(";
        }
        else
            return "";
    }
}

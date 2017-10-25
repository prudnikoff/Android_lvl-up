package com.company;

import com.sun.corba.se.spi.ior.iiop.GIOPVersion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    private static Scanner console = new Scanner(System.in);
    private static String[] dictionary = null;
    private static String[] eExpressions = null;

    public static void main(String[] args) {

        String sentence, newSentence;
        String fileName = "src/ENRUS.TXT";
        Charset cs = Charset.forName("utf-8");

        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader(fileName);
            br = Files.newBufferedReader(Paths.get(fileName), cs);

            //uploading dictionary to RAM
            ArrayList<String> lines = new ArrayList<>();
            String line;
            while((line = br.readLine()) != null) lines.add(line);
            dictionary = new String[lines.size()];
            dictionary = lines.toArray(dictionary);

            //creating an array of english expression the translator knows of
            eExpressions = new String[dictionary.length / 2];
            for (int i = 0; i < dictionary.length; i += 2)
            {
                eExpressions[i / 2] = dictionary[i];
            }

            //starting point for a user
            do {
                sentence = console.nextLine();

                if ((newSentence = Translate(sentence)) != null)
                {
                    System.out.println(newSentence);
                    continue;
                }
                newSentence = "";
                String[] words = sentence.split(" ");
                String attach;
                for (String word: words)
                {
                    attach = Translate(word);
                    if (attach == null) attach = word;
                    else attach = attach.split("\\s+")[0];
                    newSentence += attach + " ";
                }
                System.out.println(newSentence);
            } while(!sentence.equals("0"));
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
                if (fr != null) fr.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    //binary search
    public static String Translate(String word)
    {
        String nextLine;
        int start = 0;
        int end = eExpressions.length - 1;
        int index = (start + end) / 2;
        do {
            nextLine = eExpressions[index];
            if (word.compareToIgnoreCase(nextLine) > 0)
            {
                start = index + 1;
                index = (start + end) / 2;
            } else
            {
                end = index;
                index = (start + end) / 2;
            }
        } while (start != end);
        nextLine = eExpressions[end];
        if (nextLine.equalsIgnoreCase(word)) return dictionary[2 * end + 1];
        return null;
    }

}

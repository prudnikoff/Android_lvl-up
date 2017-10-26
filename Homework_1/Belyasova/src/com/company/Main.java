package com.company;

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
                if (sentence.equals("0")) break;

                if ((newSentence = Translate(sentence)[0]) != null)
                {
                    System.out.println(newSentence);
                    continue;
                }
                newSentence = "";
                String[] words = sentence.split(" ");
                String attach;
                String[] result;
                for (String word: words)
                {
                    result = Translate(word);
                    attach = result[0];
                    if (attach == null)
                    {
                        char[] slice = new char[word.length() - 1];
                        word.getChars(0, word.length() - 1, slice, 0);
                        attach = Translate(String.copyValueOf(slice))[0];
                        if (attach == null)
                        {
                            System.out.println("Maybe you meant " + result[1]);
                            attach = word;
                        }
                        attach = attach.split("\\s+")[0];
                    }
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
    public static String[] Translate(String word)
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
        if (nextLine.equalsIgnoreCase(word)) return new String[] { dictionary[2 * end + 1] };
        return new String[] { null, eExpressions[end] };
    }

}

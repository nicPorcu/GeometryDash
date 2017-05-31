package com.company;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by apcsaper3 on 5/26/17.
 */
public class GameScanner {



        private static ArrayList<Spike> spikes;
        private static final String FILENAME = "/Users/apcsaper3/IdeaProjects/GeometryDash/Level1.txt";


        public GameScanner()
        {
            spikes = new ArrayList<Spike>();
        }


        public static String getLine() {
            try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

                String sCurrentLine;

                if ((sCurrentLine = br.readLine()) != null) {
                    return sCurrentLine;
                }

            } catch (IOException e) {
                System.out.print("Bread");
                e.printStackTrace();

            }
            return "";

        }


    public void addSpike()
    {
        String line= getLine();
        int xPos= Integer.parseInt(line.substring(0, line.indexOf(":")));
        int yPos= Integer.parseInt(line.substring(line.indexOf(":")+1));
        spikes.add(new Spike(xPos, yPos));
    }



    public ArrayList<Spike> getSpikes() {
            return spikes;
        }

}

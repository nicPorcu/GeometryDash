package com.company;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by apcsaper3 on 5/26/17.
 */
public class GameScanner {



        ArrayList<Spike> spikes;

        private static final String FILENAME = "/Users/apcsaper3/IdeaProjects/GeometryDash/Level1.txt";


        public GameScanner()
        {
            spikes = new ArrayList<Spike>();
        }


        public  void addSpikes() {
            try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

                String sCurrentLine;

                while ((sCurrentLine = br.readLine()) != null) {
                    String xPos=sCurrentLine.substring(0,sCurrentLine.indexOf(":"));
                    String yPos=sCurrentLine.substring(sCurrentLine.indexOf(":")+1);
                    spikes.add(new Spike(Integer.parseInt(xPos), Integer.parseInt(yPos)));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public ArrayList<Spike> getSpikes() {
            return spikes;
        }

}

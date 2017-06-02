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
        private static ArrayList<JumpRing> rings;
        private static final String FILENAME = "/Users/apcsaper3/IdeaProjects/GeometryDash/Level1.txt";


        public GameScanner()
        {
            spikes = new ArrayList<Spike>();
            rings=new ArrayList<JumpRing>();
            addSpikes();
        }


        public void  addSpikes() {


            try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

                String sCurrentLine;

                while ((sCurrentLine = br.readLine()) != null) {
                    if(sCurrentLine.indexOf(":")>0)
                    {
                        if(sCurrentLine.indexOf("spike")>=0) {
                            String x = sCurrentLine.substring(sCurrentLine.indexOf("spike")+5, sCurrentLine.indexOf(":"));
                            String y = sCurrentLine.substring(sCurrentLine.indexOf(":") + 1);
                            spikes.add(new Spike(Integer.parseInt(x), Integer.parseInt(y)));
                        }
                        if(sCurrentLine.indexOf("ring")>=0)
                        {
                            String x = sCurrentLine.substring(sCurrentLine.indexOf("ring")+4, sCurrentLine.indexOf(":"));
                            String y = sCurrentLine.substring(sCurrentLine.indexOf(":") + 1);
                            rings.add(new JumpRing(Integer.parseInt(x), Integer.parseInt(y)));
                        }




                    }

                }



            } catch (IOException e) {
                System.out.print("Bread");
                e.printStackTrace();

            }



        }




    public ArrayList<Spike> getSpikes() {
            return spikes;
        }

    public static ArrayList<JumpRing> getRings() {
        return rings;
    }
}

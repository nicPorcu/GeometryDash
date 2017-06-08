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
        private static ArrayList<Portal> portals;
        private static ArrayList<Pillar> pillars;
        private static final String FILENAME = "Level1.txt";
        //private static final String FILENAME = "Level2.txt";

        public GameScanner()
        {
            spikes = new ArrayList<Spike>();
            rings=new ArrayList<JumpRing>();
            portals=new ArrayList<Portal>();
            pillars=new ArrayList<Pillar>();
            addStuff();

        }


        public void  addStuff() {


            try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

                String sCurrentLine;

                while ((sCurrentLine = br.readLine()) != null) {
                    if(sCurrentLine.indexOf(":")>0)
                    {
                        if(sCurrentLine.contains("spike")) {
                            String todo= sCurrentLine.substring(sCurrentLine.indexOf("-")+1);
                            String x= todo.substring(0, todo.indexOf(":"));
                            todo=todo.substring(todo.indexOf(":")+1);
                            String y= todo.substring(0,todo.indexOf(":"));
                            todo=todo.substring(todo.indexOf(":")+1);
                            String up = todo;
                            boolean u = true;
                            if(!up.equals("up")){
                                u = false;
                            }
                            spikes.add(new Spike(Integer.parseInt(x), Integer.parseInt(y), u));
                        }
                        if(sCurrentLine.contains("ring"))
                        {
                            String x = sCurrentLine.substring(sCurrentLine.indexOf("-")+1, sCurrentLine.indexOf(":"));
                            String y = sCurrentLine.substring(sCurrentLine.indexOf(":") + 1);
                            rings.add(new JumpRing(Integer.parseInt(x), Integer.parseInt(y)));
                        }
                        if(sCurrentLine.contains("portal"))
                        {
                            String x = sCurrentLine.substring(sCurrentLine.indexOf("-")+1, sCurrentLine.indexOf(":"));
                            String y = sCurrentLine.substring(sCurrentLine.indexOf(":") + 1);
                            portals.add(new Portal(Integer.parseInt(x), Integer.parseInt(y)));
                        }
                        if(sCurrentLine.contains("pillar"))
                        {
                            String todo= sCurrentLine.substring(sCurrentLine.indexOf("-")+1);
                            String x= todo.substring(0, todo.indexOf(":"));
                            todo=todo.substring(todo.indexOf(":")+1);
                            String y= todo.substring(0,todo.indexOf(":"));
                            todo=todo.substring(todo.indexOf(":")+1);
                            String width= todo.substring(0,todo.indexOf(":"));
                            todo=todo.substring(todo.indexOf(":")+1);
                            String height= todo;
                            pillars.add(new Pillar(Integer.parseInt(x),Integer.parseInt(y),Integer.parseInt(width),Integer.parseInt(height)));

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

    public ArrayList<JumpRing> getRings() {
        return rings;
    }

    public ArrayList<Portal> getPortals() {
        return portals;
    }

    public ArrayList<Pillar> getPillars() {
        return pillars;
    }
}

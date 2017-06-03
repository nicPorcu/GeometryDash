package com.company;

public class Main {
    public static GameScanner a;


    public static void main(String[] args) {
        a=new GameScanner();

        System.out.println(a.getSpikes().get(0).getX()[0]);
        System.out.println(a.getSpikes().get(1).getX()[0]);
        System.out.println(a.getRings().get(0).getRingX());
        System.out.println(a.getPortals().get(0).getY());
        System.out.println(a.getPillars().get(0).getX());
        System.out.println(a.getPillars().get(1).getY());

    }
}

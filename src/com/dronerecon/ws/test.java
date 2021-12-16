package com.dronerecon.ws;

import java.util.ArrayList;

public class test {

    public static void main(String[] args) {

        DBManager DB = new DBManager();


        DB.readAreaGridTiles("23232");

        ArrayList<AreaGridTile> lstTiles = DB.readAreaGridTiles("23232");

        for(AreaGridTile oTile: lstTiles){
            System.out.println("tile: " + oTile.x + "," + oTile.y);
            if(oTile.x < lstTiles.x){
                oTile.x = lstTiles.x;
            }
        }



    }


}

package com.dronerecon.ws;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.security.SecureRandom;

public class DroneDataService extends HttpServlet{


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        response.addHeader("Access-Control-Allow-Origin", "*");

        PrintWriter out = response.getWriter();

        // ##############################
        // 1. Get params passed in.

        // Get the following parameters from the request object and put them into strings:
        // area_id
        // tilex
        // tiley
        // totalcols
        // totalrows
        // ##############################
        String area_id = request.getParameter("area_id");
        String tilex = request.getParameter("tilex");
        String tiley = request.getParameter("tiley");
        String totalcols = request.getParameter("totalcols");
        String totalrows = request.getParameter("totalrows");
        String r = request.getParameter("r");
        String g = request.getParameter("g");

        try {

            // Call PortalDVService
            URL url = new URL("http://127.0.0.1:8080/dronereconportal/PortalDBService?area_id=" + area_id + "&tilex=" + tilex + "&tiley=" + tilex + "&r=" + r + "&g=" + g + "");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // ##############################
        // 2. Default value of beginning direction.

        // Set a string called sDirection to "right".
        // ##############################

        String sDirection = "right";

        // ##############################
        // 3. Calculate next drone move.

        // Determine next tile to move to.
        // Base this on current x and y.
        // Change sDirection if necessary.
        // Drone must serpentine from top left of grid back and forth down.
        // If rows are done, change sDirection to "stop".
        // ##############################

        int iTilex = Integer.parseInt(tilex);
        int iTiley = Integer.parseInt(tiley);
        int iTotalcols = Integer.parseInt(totalcols);
        int iTotalrows= Integer.parseInt(totalrows);

        if (iTiley % 2 == 0) {
            if (iTiley == iTotalrows - 1 && iTilex == iTotalcols - 1) {
                sDirection = "stop";
            }else if(iTilex == iTotalcols - 1) {
                iTiley++;
                sDirection = "left";
            }else{
                iTilex++;
                sDirection = "right";
            }
        }else{
            if (iTiley == iTotalrows - 1 && iTilex == iTotalcols - 1) {
                sDirection = "stop";
            }else if(iTilex == 0){
                iTiley++;
                sDirection = "right";
            }else{
                iTilex--;
                sDirection = "left";

            }
        }


        // ##############################
        // 4. Format & Return JSON string to caller.


        String sReturnJson = "{\"area_id\":\"" + area_id + "\", \"nextTileX\":\"" + iTilex + "\", \"nextTileY\":\"" + iTiley + "\", \"direction\":\"" + sDirection + "\"}";
        out.println(sReturnJson);
    }
}
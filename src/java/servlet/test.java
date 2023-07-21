/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Sudeera Perera
 */
@WebServlet(name = "test", urlPatterns = {"/test"})
@MultipartConfig
public class test extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Part filePart = req.getPart("fileInput");
            System.out.println(filePart);
        String area = req.getParameter("area");
        String topic = req.getParameter("topic");
        System.out.println(area);
        System.out.println(topic);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

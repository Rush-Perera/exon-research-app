/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import connection.HibernateUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import filteration.Filteration;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Map;
import mapping.Country;
import mapping.User;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Sudeera Perera
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int status;
        String message = "";
        Session sess = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sess.beginTransaction();
        JSONObject objSend = new JSONObject();
        System.out.println(req.getParameter("nic"));
        System.out.println(req.getParameter("firstName"));
        
        try {
            
            
            
            BufferedReader reader = req.getReader();
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String jsonData = sb.toString();

            // Parse JSON data
            JSONObject jsonObject = new JSONObject(jsonData);
         
            String nic = Filteration.getFilteredString(jsonObject.getString("nic").toLowerCase());
            String firstName = Filteration.getFilteredString(jsonObject.getString("firstName"));
            String lastName = Filteration.getFilteredString(jsonObject.getString("lastName"));
            String mobile = Filteration.getFilteredNumber(jsonObject.getString("phone"));
            String email = Filteration.getFilteredString(jsonObject.getString("email"));
            String password = Filteration.getFilteredString(jsonObject.getString("password"));

            System.out.println(nic + ", " + firstName + ", " + lastName + ", " + mobile + ", " + email);

            User gup = (User) sess.createQuery("From User Where nic='" + nic + "'").setMaxResults(1).uniqueResult();

            if (gup == null) {

                Country country = new Country();
                country = (Country) sess.createQuery("From Country Where name='Sri Lanka'").setMaxResults(1).uniqueResult();
                gup = new User();
                gup.setNic(nic);
                gup.setFname(firstName);
                gup.setLname(lastName);
                gup.setMobile(mobile);
                gup.setEmail(email);
                gup.setPassword(password);
                gup.setCountry(country);
                sess.save(gup);
                t.commit();
                status = 200;
                message = "Data Successfully Saved";

            } else {
                status = 400;
                message = "User already registed.";
            }

        } catch (Exception e) {
            status = 500;
            message = "error  500 = "+e.getLocalizedMessage()+"";

        } finally {
            sess.close();
        }
        try {
            objSend.put("status", status);
            objSend.put("message", message);
        } catch (JSONException ex) {
            message = message+"error : "+ex.getLocalizedMessage()+"";
        }
        resp.setContentType("application/json");

        resp.getWriter().write(objSend.toString());
    }

}

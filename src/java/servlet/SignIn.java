/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import connection.HibernateUtil;
import filteration.Filteration;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mapping.Country;
import mapping.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Sudeera Perera
 */
@WebServlet(name = "SignIn", urlPatterns = {"/SignIn"})
public class SignIn extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int status;
        String message = "";
        Session sess = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sess.beginTransaction();
        JSONObject objSend = new JSONObject();

        try {
            BufferedReader reader = req.getReader();
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String jsonData = sb.toString();
            
            System.out.println(jsonData);
            
            // Parse JSON data
            JSONObject jsonObject = new JSONObject(jsonData);
            
            System.out.println(jsonObject);
            
            String email = Filteration.getFilteredString(jsonObject.getString("email"));
            String password = Filteration.getFilteredString(jsonObject.getString("password"));
          
            System.out.println(email + "  " + password);
            
            User gup = (User) sess.createQuery("FROM User u WHERE u.email = '"+email+"' AND u.password = '"+password+"'").setMaxResults(1).uniqueResult();
        
            if (gup == null) {
                status = 400;
                message = "Incorrect credentials.";
            } else {
                status = 200;
                message = "Login successful";
                System.out.println("inside");
            }

        } catch (Exception e) {
            status = 500;
            message = "Error occurred"+ e.getMessage();

        } finally {
            sess.close();
        }
        try {
            objSend.put("status", status);
            objSend.put("message", message);
        } catch (JSONException ex) {
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        resp.getWriter().print(objSend.toString());
        resp.getWriter().flush();

    }

}

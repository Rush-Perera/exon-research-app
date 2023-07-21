/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import connection.HibernateUtil;
import filteration.Filteration;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mapping.Country;
import mapping.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Sudeera Perera
 */
@WebServlet(name = "NewRegister", urlPatterns = {"/NewRegister"})
public class NewRegister extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int status;
        String message = "";
        System.out.println("Before");
        Session sess = HibernateUtil.getSessionFactory().openSession();
        System.out.println("After");
        Transaction t = sess.beginTransaction();
        System.out.println("transaction");
        JSONObject objSend = new JSONObject();
        System.out.println("jason obj");
        
        Map<String, String[]> formData = req.getParameterMap();
        
        System.out.println(Arrays.toString(formData.get("nic")));
        System.out.println(Arrays.toString(formData.get("firstName")));
        
        try {
            

            System.out.println("Inside try block");
            System.out.println(req.getParameter("nic"));
            String nic = Filteration.getFilteredString(req.getParameter("nic").toLowerCase());
            String firstName = Filteration.getFilteredString(req.getParameter("firstName"));
            System.out.println("after fname");
            String lastName = Filteration.getFilteredString(req.getParameter("lastName"));
            System.out.println("after lname");
            String mobile = Filteration.getFilteredNumber(req.getParameter("phone"));
            System.out.println("after phone");
            String email = Filteration.getFilteredString(req.getParameter("email"));
            System.out.println("after email");
            String password = Filteration.getFilteredString(req.getParameter("password"));

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
            message = e.getMessage();

        } finally {
            sess.close();
        }
        try {
            objSend.put("status", status);
            objSend.put("message", message);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        resp.setContentType("application/json");

        resp.getWriter().write(objSend.toString());
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

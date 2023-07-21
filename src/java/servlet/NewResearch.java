/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import common.ArraySplitter;
import connection.HibernateUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mapping.Author;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.String;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import mapping.Research;
import org.json.JSONArray;
import javax.servlet.annotation.MultipartConfig;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mapping.Area;
import org.hibernate.Query;

/**
 *
 * @author Sudeera Perera
 */
@WebServlet(name = "NewResearch", urlPatterns = {"/NewResearch"})
@MultipartConfig
public class NewResearch extends HttpServlet {

    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 50 * 1024;
    private int maxMemSize = 4 * 1024;
    private File file;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int status = 0;
        String message = "";
        Session sess = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sess.beginTransaction();
        JSONObject objSend = new JSONObject();
//        boolean isTransactionInProgress = t != null && t.isActive();

        try {

            Part filePart = req.getPart("file");
            Part filePhotoPart = req.getPart("filePhoto");
            String jsonData = req.getParameter("data");

            // Convert jsonData to JSONObject if needed
            JSONObject jsonObject = new JSONObject(jsonData);

            // Now you can access the individual fields in the JSONObject
            String area = jsonObject.getString("area");
            String topic = jsonObject.getString("topic");
            String re_abstract = jsonObject.getString("abstract");

            // Access the array values (keywords and authors) from JSONObject
            JSONArray keywordsArray = jsonObject.getJSONArray("keywords");
            JSONArray authorsArray = jsonObject.getJSONArray("authors");

            // Iterate over the arrays and extract individual values
            for (int i = 0; i < keywordsArray.length(); i++) {
                String keyword = keywordsArray.getString(i);
            }

            System.out.println(keywordsArray);

            for (int i = 0; i < authorsArray.length(); i++) {
                Author author = new Author();
                JSONArray authorArray = authorsArray.getJSONArray(i);
                String nic = authorArray.getString(0);
                String fname = authorArray.getString(1);
                String lname = authorArray.getString(2);
                String institute = authorArray.getString(3);
                String isMainAuthor = authorArray.getString(4);
                // Process the author values as needed
                author.setNic(nic);
                author.setFname(fname);
                author.setLname(lname);
                author.setInstitution(institute);
                author.setType(isMainAuthor);

                System.out.println(authorArray.toString());
                System.out.println(author.toString());
                sess.save(author);
            }

//            sess.close();
            System.out.println(area);
            System.out.println(topic);
            System.out.println(re_abstract);

            String fileName = getFileName(filePart);
            String fileNamePhoto = getFileName(filePhotoPart);

            String uploadDir = "/uploads/";

            // Create the directory if it doesn't exist
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs();
            }

            String filePath = uploadDir + fileName;
            try (InputStream fileContent = filePart.getInputStream()) {
                FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fileContent.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }
                fileOutputStream.close();
            } catch (Exception e) {
                message = e.getMessage();
            }
            System.out.println(filePath);

            String filePath2 = uploadDir + fileNamePhoto;
            try (InputStream fileContent = filePhotoPart.getInputStream()) {
                FileOutputStream fileOutputStream = new FileOutputStream(filePath2);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fileContent.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }
                fileOutputStream.close();
            } catch (Exception e) {
                message = e.getMessage();
            }
            System.out.println(filePath2);

            Research research = new Research();
            Area rarea = (Area) sess.get(Area.class, 1);
            
            Query query = sess.createQuery("select max(id) from Research");
            int maxId = (int) query.setMaxResults(1).uniqueResult();
            
            research.setId(maxId+1);
            research.setArea(rarea);
            research.setKeywords(keywordsArray.toString());
            research.setDocPath(filePath);
            research.setImgPath(filePath2);
            research.setResearchAbstract(re_abstract);
            research.setTopic(topic);
           
            sess.save(research);

            t.commit();

            status = 200;
            message = message + "Success";

        } catch (Exception e) {
            status = 500;
            message = "Error occurred : " + e.getMessage();
        } finally {
            sess.close();
        }

        try {
            objSend.put("status", status);
            objSend.put("message", message);
        } catch (JSONException ex) {
        }
        resp.setContentType("application/json");

        resp.getWriter().write(objSend.toString());

    }

    private Author[] parseAuthors(String[] authorsArray) {
        Author[] authors = new Author[authorsArray.length];

        for (int i = 0; i < authorsArray.length; i++) {
            String[] authorDetails = authorsArray[i].split(",");

            // Assuming the author details are in the format: "name,email,institution"
            String nic = authorDetails[0];
            String fname = authorDetails[1];
            String lname = authorDetails[2];
            String institution = authorDetails[3];
            String authorType = authorDetails[4];

            Session sess = HibernateUtil.getSessionFactory().openSession();
//
//            AuthorType at = (AuthorType) sess.createQuery("From AuthorType Where type='" + authorType + "'").setMaxResults(1).uniqueResult();
//            Author author = new Author(nic, at, fname, lname, institution);
//            authors[i] = author;

            sess.close();
        }

        return authors; // Return the array of Author objects
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }

}

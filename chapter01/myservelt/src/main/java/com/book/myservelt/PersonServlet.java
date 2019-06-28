/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.myservelt;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.PushBuilder;
import org.bson.Document;

/**
 *
 * @author avbravo
 */
@WebServlet(name = "PersonServlet", urlPatterns = {"/PersonServlet"})
public class PersonServlet extends HttpServlet {

    @Inject
    MongoClient myConnection;

    public MongoDatabase getMongoDatabase() {
        try {
            MongoDatabase db = myConnection.getDatabase("book");
            return db;
        } catch (Exception ex) {
            Logger.getLogger(PersonServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

  
        Jsonb jsonb = JsonbBuilder.create();
        PushBuilder pb = request.newPushBuilder();

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PersonServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> List of People</h1>");

            out.println("<table style\"width:100%\">");
            out.println("<tr>");
            out.println("  <th>id</th>");
            out.println("  <th>name</th>");
            out.println("</tr>");
            for (Document doc : getMongoDatabase().getCollection("person").find()) {
                Person person = jsonb.fromJson(doc.toJson(), Person.class);
                out.println("<tr>");
                out.println("  <th>" + person.getId() + "</th>");
                out.println("  <th>" + person.getName() + "</th>");

                if (pb != null) {
                    pb.path(person.getPhoto())
                            .addHeader("content-type", person.getPhoto())
                            .push();
                }
                out.println("  <th><img src='" + person.getPhoto() + "'></th>");
                out.println("</tr>");
            }
            out.println("</table>");

            out.println("</body>");
            out.println("</html>");
        }
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

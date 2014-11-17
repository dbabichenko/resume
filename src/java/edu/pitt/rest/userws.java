/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.rest;

<<<<<<< HEAD
import edu.pitt.resumecore.User;
=======
>>>>>>> mandySprint5
import edu.pitt.utilities.DbUtilities;
import edu.pitt.utilities.Security;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author Jordan Feldman
 */
@WebServlet(name = "userws", urlPatterns = {"/rest/userws"})
public class userws extends HttpServlet {

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
        response.setContentType("application/json;charset=UTF-8");
<<<<<<< HEAD
        DbUtilities db = new DbUtilities();
=======

        DbUtilities db = new DbUtilities();

        //DbUtilities db = null;

>>>>>>> mandySprint5
        if (Security.checkHijackedSession(request.getSession(false), request)) {
            response.sendRedirect("./index.jsp");
        }
        try (PrintWriter out = response.getWriter()) {
<<<<<<< HEAD
            String additionalSQL;
            if(((User) request.getSession().getAttribute("authenticatedUser")).getRoles().contains("staff")){
                additionalSQL = "";
            }
            else{
                additionalSQL = "WHERE enabled = 1";
            }
            String sql = "SELECT lastName, firstName, login, email, IF(peoplesoftID IS NOT NULL, 'true', 'false') AS Student, IF(employeeID IS NOT NULL, 'true', 'false') AS Staff, IF(placeOfWork IS NOT NULL, 'true', 'false') AS Employer, userID, enabled FROM rms.User U LEFT JOIN Student S ON U.userID = S.fk_userID LEFT JOIN Staff ST ON U.userID = ST.fk_userID LEFT JOIN Employer E ON U.userID = E.fk_userID" + additionalSQL;
=======
            String sql = "SELECT lastName, firstName, login, email, IF(peoplesoftID IS NOT NULL, 'true', 'false') AS Student, IF(employeeID IS NOT NULL, 'true', 'false') AS Staff, IF(placeOfWork IS NOT NULL, 'true', 'false') AS Employer, userID, enabled FROM rms.User U LEFT JOIN Student S ON U.userID = S.fk_userID LEFT JOIN Staff ST ON U.userID = ST.fk_userID LEFT JOIN Employer E ON U.userID = E.fk_userID";
>>>>>>> mandySprint5

            if (request.getParameter("lastName") != null) {
                String lastName = request.getParameter("lastName");
                sql += String.format(" WHERE lastName  LIKE '%s%%'", lastName);
            } else if (request.getParameter("login") != null) {
                String login = request.getParameter("login");
                sql += String.format(" WHERE login  LIKE '%s%%'", login);
            } else if (request.getParameter("email") != null) {
                String email = request.getParameter("email");
                sql += String.format(" WHERE email  LIKE '%s%%'", email);
            }

<<<<<<< HEAD
=======

            db = new DbUtilities();

>>>>>>> mandySprint5
            JSONArray ja;
            ja = null;
            try {
                ja = db.getJsonDataTable(sql);
            } catch (JSONException ex) {
                Logger.getLogger(userws.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                out.print(ja);
                System.out.println(sql);
            }

        } catch (SQLException ex) {
            Logger.getLogger(userws.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeMySQLConnection();
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

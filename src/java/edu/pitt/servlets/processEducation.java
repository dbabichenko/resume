/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.servlets;

import edu.pitt.resumecore.Education;
import edu.pitt.resumecore.Resume;
import edu.pitt.resumecore.User;
import edu.pitt.utilities.Security;
import edu.pitt.utilities.StringUtilities;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mandy
 */
@WebServlet(name = "processEducation", urlPatterns = {"/processEducation"})
public class processEducation extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(true);

//        Security security = new Security();
//        if (security.checkHijackedSession(session, request)) {
//            response.sendRedirect("index.jsp");
//        }

        

            User user = (User) session.getAttribute("authenticatedUser");
            Resume resume = new Resume(user.getUserID(),0);
            String schoolName = "";
            String degreeType = "";
            String major = "";
            Double gpa = null;
            String graduationDate = "";

            
            schoolName = request.getParameter("txtSchoolName");
            degreeType = request.getParameter("txtDegreeType");
            major = request.getParameter("txtMajorType");
            gpa = Double.parseDouble(request.getParameter("txtGPA"));
            graduationDate = request.getParameter("txtGraduationDate");
            
//            java.util.Date dt = new java.util.Date();
//
//            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//            String currentTime = sdf.format(dt);
            
            Education education = new Education(schoolName, degreeType, major, gpa, graduationDate);
            resume.addEducation(education);
            
            session.setAttribute("currentResume", resume);

            response.sendRedirect("workExperience.jsp");
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.pitt.servlets;

import edu.pitt.portfoliocore.Portfolio;
import edu.pitt.portfoliocore.Research;
import edu.pitt.utilities.Security;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Processes the creation of a new Research object
 *
 * @author mshirey
 */
public class processResearch extends HttpServlet {

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
            if (Security.checkHijackedSession(session, request)) {
                response.sendRedirect("index.jsp");
            } else {
            Portfolio portfolio = (Portfolio) session.getAttribute("currentPortfolio");
            String researchName = "";
            String journal = "";
            String submissionDate = "";
            String publishedDate = "";
            String researchAbstract = "";
            String publishLink = "";            
            

            //What error handling do we want to do?
            researchName = request.getParameter("txtResearchName");
            journal = request.getParameter("txtJournal");
            submissionDate = request.getParameter("txtSubmissionDate");//Date?
            publishedDate = request.getParameter("txtPublishedDate");//Date?
            researchAbstract = request.getParameter("txtResearchAbstract");
            publishLink = request.getParameter("txtPublishLink");

            Research research = new Research(researchName, journal, submissionDate, publishedDate, researchAbstract);
            Portfolio.addResearch(research);
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



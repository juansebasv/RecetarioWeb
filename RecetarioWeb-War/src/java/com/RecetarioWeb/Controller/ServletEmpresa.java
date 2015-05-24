/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Controller;

import com.RecetarioWeb.Beans.EmpresaBeanRemote;
import com.RecetarioWeb.Entitys.Empresa;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Personal
 */
@WebServlet(name = "ServletEmpresa", urlPatterns = {"/ServletEmpresa"})
public class ServletEmpresa extends HttpServlet {

    @EJB
    private EmpresaBeanRemote empresaBean;

    private Empresa empresa = new Empresa();

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletEmpresa</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletEmpresa at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        String nameEmp = request.getParameter("nameEmp");
        String descEmp = request.getParameter("descEmp");
        empresa.setNombreemp(nameEmp);
        empresa.setDescripcionemp(descEmp);
        empresaBean.registrarEmpresa(empresa);
        response.sendRedirect("../RecetarioWeb-War/home_admin.jsp");
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
        //processRequest(request, response);
        String ubicacionArchivo = "C:/Users/Personal/Desktop/imegenes";

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024);
        factory.setRepository(new File(ubicacionArchivo));

        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            List<FileItem> partes = upload.parseRequest(request);
            for (FileItem item : partes) {
                File file = new File(ubicacionArchivo, item.getName());
                item.write(file);
            }
            System.out.println(":::::::::::::: el archivo fue cargado " + ubicacionArchivo + partes.get(0).getName());
            String image = ubicacionArchivo + "/" + partes.get(0).getName();
            empresa.setImagenemp(image);
        } catch (FileUploadException ex) {
            System.out.println("Error al subir archivo " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error al subir archivo " + ex.getMessage());
        }
        //response.sendRedirect("../RecetarioWeb-War/form_empresa.jsp");
        request.getRequestDispatcher( "/form_empresa.jsp" ).forward( request, response );
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

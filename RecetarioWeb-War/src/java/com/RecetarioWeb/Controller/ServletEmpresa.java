/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Controller;

import com.RecetarioWeb.Beans.EmpresaBeanRemote;
import com.RecetarioWeb.Controller.support.AppConfig;
import com.RecetarioWeb.Controller.support.WebKeys;
import com.RecetarioWeb.Entitys.Empresa;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Alta de empresas. Un POST multipart sube la imagen; un POST normal crea la
 * empresa con nombre y descripcion. No se mantiene estado entre peticiones.
 *
 * @author Personal
 */
@WebServlet(name = "ServletEmpresa", urlPatterns = {"/ServletEmpresa"})
public class ServletEmpresa extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(ServletEmpresa.class.getName());

    @EJB
    private EmpresaBeanRemote empresaBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(WebKeys.VIEW_HOME_ADMIN);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (ServletFileUpload.isMultipartContent(request)) {
            guardarImagen(request);
            request.getRequestDispatcher(WebKeys.VIEW_FORM_EMPRESA).forward(request, response);
            return;
        }

        String nameEmp = request.getParameter("nameEmp");
        String descEmp = request.getParameter("descEmp");
        boolean creada = false;
        if (nameEmp != null && !nameEmp.trim().isEmpty()
                && empresaBean.findByName(nameEmp.trim()) == null) {
            Empresa empresa = new Empresa();
            empresa.setNombreemp(nameEmp.trim());
            empresa.setDescripcionemp(descEmp);
            empresaBean.registrarEmpresa(empresa);
            creada = true;
            LOG.log(Level.INFO, "Empresa creada: ''{0}''", nameEmp.trim());
        }
        response.sendRedirect(WebKeys.VIEW_HOME_ADMIN + (creada ? "?created=empresa" : ""));
    }

    /** Guarda los archivos subidos en el directorio temporal del contenedor. */
    private void guardarImagen(HttpServletRequest request) {
        File destino = new File(System.getProperty("java.io.tmpdir"), AppConfig.empresaUploadDirName());
        if (!destino.exists() && !destino.mkdirs()) {
            LOG.log(Level.WARNING, "No se pudo crear el directorio de imagenes: {0}", destino);
            return;
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(AppConfig.empresaUploadSizeThreshold());
        factory.setRepository(destino);
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> partes = upload.parseRequest(request);
            for (FileItem item : partes) {
                if (!item.isFormField() && item.getName() != null && !item.getName().isEmpty()) {
                    item.write(new File(destino, new File(item.getName()).getName()));
                }
            }
        } catch (Exception ex) {
            LOG.log(Level.WARNING, "Error al subir la imagen de la empresa", ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Alta de empresas";
    }
}

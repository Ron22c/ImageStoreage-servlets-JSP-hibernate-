package com.cranajit.controllers;

import com.cranajit.daoclasses.UserFileUploadDAO;
import com.cranajit.models.UserFileUploadModel;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ImageController", urlPatterns={"/image"})
public class ImageController extends HttpServlet {
    private final String path = "/Users/cranajit/Desktop/LOCKDOWN/JAVA/ImageUploader/web/uploadedImages/";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action != null) {
            switch (action) {
                case "images":
                    request.setAttribute("title", "UPLOAD IMAGE");
                    storeImages(request, response);
                    break;
                case "updatemetadata":
                    updateMetaData(request, response);
                    break;
                case "show":
                    request.setAttribute("title", "IMAGE");
                    showImageDetails(request, response);
                    break;
                case "delete":
                    deleteImageDetails(request, response);
                    break;
                default:
                    request.setAttribute("title", "PAGE NOT FOUND");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("title", "ERROR!!!");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action!=null) {
            switch (action) {
                case "showimages":
                    request.setAttribute("title", "IMAGES");
                    showImages(request, response);
                    break;
            }
        } else {
            request.setAttribute("title", "ERROR!!!");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private void storeImages(HttpServletRequest request, HttpServletResponse response) {
        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
        try {
            List<FileItem> files = upload.parseRequest(request);
            for(FileItem file: files) {
                File fileToUpload = new File(path+file.getName());
                if(!fileToUpload.exists()) {
                    new UserFileUploadDAO().addFileData(new UserFileUploadModel(file.getName()));
                    file.write(fileToUpload);
                }
            }
            showImages(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showImages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserFileUploadModel> photos = new UserFileUploadDAO().getAllImageList();
        request.setAttribute("photos", photos);
        request.setAttribute("path", path);
        request.getRequestDispatcher("views/showimage.jsp").forward(request, response);
    }

    private void updateMetaData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String description =  request.getParameter("description");
        String caption = request.getParameter("caption");

        new UserFileUploadDAO().updateMetadata(id, description, caption);
        response.sendRedirect(request.getContextPath()+"/image?action=showimages");
    }

    private void deleteImageDetails(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("fileid"));
        String deletedFile = new UserFileUploadDAO().deleteImage(id);
        File file = new File(path+deletedFile);
        file.delete();
        response.sendRedirect(request.getContextPath()+"/image?action=showimages");
    }

    private void showImageDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("fileid"));
        UserFileUploadModel model = new UserFileUploadDAO().getImageDetailsById(id);
        request.setAttribute("image", model);
        request.setAttribute("path", path);
        request.getRequestDispatcher("views/imageview.jsp").forward(request, response);
    }
}

package com.cranajit.daoclasses;

import com.cranajit.models.UserFileUploadModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UserFileUploadDAO {
    SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(UserFileUploadModel.class)
            .buildSessionFactory();

    public void addFileData(UserFileUploadModel model) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.save(model);
        session.getTransaction().commit();
        System.out.println("file added successfully: "+model.getFileName());
    }

    public List<UserFileUploadModel> getAllImageList() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        List<UserFileUploadModel> photoList = session.createQuery("FROM com.cranajit.models.UserFileUploadModel").getResultList();
        session.getTransaction().commit();
        return photoList;
    }

    public void updateMetadata(int id, String description, String caption) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("UPDATE photos SET description='"+description+"', caption='"+caption+"' WHERE phone='"+id+"'").executeUpdate();
        session.getTransaction().commit();
        System.out.println("File updated successfully: "+id);
    }

    public String deleteImage(int id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        UserFileUploadModel model;
        model = session.get(UserFileUploadModel.class, id);
        session.delete(model);
        session.getTransaction().commit();
        System.out.println("File deleted successfully: "+id);
        return model.getFileName();
    }

    public UserFileUploadModel getImageDetailsById(int id) {
        Session session = factory.getCurrentSession();
        UserFileUploadModel model = new UserFileUploadModel();
        session.beginTransaction();
        model = session.get(UserFileUploadModel.class, id);
        session.getTransaction().commit();
        return model;
    }
}

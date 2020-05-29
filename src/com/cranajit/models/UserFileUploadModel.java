package com.cranajit.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * create table photos(id int auto_increment primary key, name varchar(45), description varchar(45), caption varchar(45));
 */

@Entity(name = "photos")
@Table(name = "photos")
public class UserFileUploadModel {
    @Id
    @Column(name = "id")
    private int phone;

    @Column(name = "name")
    private String fileName;

    @Column(name = "description")
    private String description;

    @Column(name = "caption")
    private String caption;

    public UserFileUploadModel() {

    }

    public UserFileUploadModel(String fileName, String description, String caption) {
        this.fileName = fileName;
        this.description = description;
        this.caption = caption;
    }

    public UserFileUploadModel(String fileName) {
        this.fileName = fileName;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "UserFileUploadModel{" +
                "phone='" + phone + '\'' +
                ", fileName='" + fileName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

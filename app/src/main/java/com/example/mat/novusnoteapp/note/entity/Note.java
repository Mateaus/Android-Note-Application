package com.example.mat.novusnoteapp.note.entity;

public class Note {

    private String id;
    private String category;
    private String subject;
    private String description;

    public Note(){

    }

    public Note(String id, String category, String subject, String description){
        this.id = id;
        this.category = category;
        this.subject = subject;
        this.description = description;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

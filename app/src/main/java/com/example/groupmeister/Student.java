package com.example.groupmeister;

public class Student {
    private String name;
    private String prefLang1;
    private String prefLang2;
    private String prefProject1;
    private String prefProject2;
    private Student prefPartner;
    private Student notPartner;

    Student(String name){
        this.name = name;
    }

    public void setPrefLang1(String language){
        this.prefLang1 = language;
    }

    public void setPrefLang2(String language){
        this.prefLang2 = language;
    }

    public void setPrefProject1(String project){
        this.prefProject1 = project;
    }

    public void setPrefProject2(String project){
        this.prefProject2 = project;
    }

    public void setPrefPartner(Student partner){
        this.prefPartner = partner;
    }

    public void notPartner(Student partner){
        this.notPartner = partner;
    }

    public String getName(){
        return this.name;
    }
}

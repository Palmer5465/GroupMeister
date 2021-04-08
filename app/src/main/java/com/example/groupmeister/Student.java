package com.example.groupmeister;

public class Student {
    private int id;
    private String name;
    private String prefLang;
    private String prefProject;
    private String prefPartner;
    private String notPartner;

    Student(String name, int id){
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", prefLang='" + prefLang + '\'' +
                ", prefProject='" + prefProject + '\'' +
                ", prefPartner='" + prefPartner + '\'' +
                ", notPartner='" + notPartner + '\'' +
                '}';
    }

    public void setPrefLang(String language){
        this.prefLang = language;
    }

    public String getPrefLang(){
        return this.prefLang;
    }

    public void setPrefProject(String project){
        this.prefProject = project;
    }

    public String getPrefProject(){
        return this.prefProject;
    }

    public void setPrefPartner(String partnerName){
        this.prefPartner = partnerName;
    }

    public String getPrefPartner(){
        return this.prefPartner;
    }

    public void setNotPartner(String partnerName){
        this.notPartner = partnerName;
    }

    public String getNotPartner(){
        return this.notPartner;
    }

    public String getName(){
        return this.name;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

}

package com.example.groupmeister;

public class Student {
    private int id;
    private String name;
    private String notPartner = "None";

    Student(String name, int id){
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", notPartner='" + notPartner + '\'' +
                '}';
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

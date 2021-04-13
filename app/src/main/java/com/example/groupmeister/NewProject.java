package com.example.groupmeister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NewProject extends AppCompatActivity {

    private String courseTitle, notPartner;
    private EditText projectNameText, groupSizeText, notPartnerText;
    private ListView lvStudents;
    private List<Student> students = new ArrayList<Student>();
    private Student clickedStudent;
    private boolean studentIsValid;
    ArrayAdapter studentArrayAdapter;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);
        setTitle("New Project");
        Intent i = getIntent();
        courseTitle = i.getStringExtra("course title");
        projectNameText = findViewById(R.id.projectNameText);
        groupSizeText = findViewById(R.id.groupSizeNumber);
        notPartnerText = findViewById(R.id.notPartnerText);
        lvStudents = findViewById(R.id.lvStudentsProject);
        databaseHelper = new DatabaseHelper(this, courseTitle);
        students = databaseHelper.getStudents();
        studentArrayAdapter = new ArrayAdapter(NewProject.this, android.R.layout.simple_list_item_1, students);
        lvStudents.setAdapter(studentArrayAdapter);

        lvStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clickedStudent = (Student)parent.getItemAtPosition(position);
                notPartner = notPartnerText.getText().toString();
                studentIsValid = validateStudent(notPartner);
                if (clickedStudent.getName().equals(notPartner)) {
                    Toast.makeText(NewProject.this, clickedStudent.getName()+" cannot not work with "+notPartner, Toast.LENGTH_SHORT).show();
                    System.out.println(clickedStudent.getName()+" cannot not work with "+notPartner);
                }else if(!studentIsValid) {
                    Toast.makeText(NewProject.this, notPartner +" is not enrolled in this course", Toast.LENGTH_SHORT).show();
                    System.out.println(notPartner +" is not enrolled in this course");
                }else{
                    students.get(position).setNotPartner(notPartner);
                    lvStudents.setAdapter(studentArrayAdapter);
                    Toast.makeText(NewProject.this, "Preferences changed for " + clickedStudent.getName(), Toast.LENGTH_SHORT).show();
                    System.out.println("Preferences changed for " + clickedStudent.getName());
                }
            }

        });
    }

    public boolean validateStudent(String student){
        for(int index = 0; index < students.size(); index++){
            if (students.get(index).getName().equals(student)){
                return true;
            }
        }
        return false;
    }

    public List<List<Student>> generateGroups(int groupSizes){
        return null;
    }
}
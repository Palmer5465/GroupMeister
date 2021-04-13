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
import java.util.Random;

public class NewProject extends AppCompatActivity {

    private String courseTitle, notPartner;
    private EditText projectNameText, groupSizeText, notPartnerText;
    private ListView lvStudents;
    private List<Student> students = new ArrayList<Student>();
    private List<Student> group = new ArrayList<Student>();
    private Student utilityStudent;
    private int groupSize;
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
                utilityStudent = (Student)parent.getItemAtPosition(position);
                notPartner = notPartnerText.getText().toString();
                studentIsValid = validateStudent(notPartner);
                if (utilityStudent.getName().equals(notPartner)) {
                    Toast.makeText(NewProject.this, utilityStudent.getName()+" cannot not work with "+notPartner, Toast.LENGTH_SHORT).show();
                }else if(!studentIsValid) {
                    Toast.makeText(NewProject.this, notPartner +" is not enrolled in this course", Toast.LENGTH_SHORT).show();
                }else{
                    students.get(position).setNotPartner(notPartner);
                    lvStudents.setAdapter(studentArrayAdapter);
                    Toast.makeText(NewProject.this, "Preferences changed for " + utilityStudent.getName(), Toast.LENGTH_SHORT).show();
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

    public void replace(int index1, int index2){
        utilityStudent = students.get(index1);
        students.set(index1, students.get(index2));
        students.set(index2, utilityStudent);
    }

    public void shuffle(){
        Random rand = new Random();
        int index1;
        int index2;
        for(int rep = 0; rep < 20; rep++) {
            index1 = rand.nextInt(students.size());
            index2 = rand.nextInt(students.size());
            if(index1 == index2 && (index2 + 1) != students.size()){
                index2++;
            }else if(index1 == index2 && index2 != 0){
                index2--;
            }
            replace(index1, index2);
        }
    }

    public void assignGroups(int numGroups){
        Random rand = new Random();
        int indexShuffle;
        int studentNum;
        boolean notPartnerInGroup = false;
        for(int groupNum = 1; groupNum <= numGroups; groupNum++){
            databaseHelper = new DatabaseHelper(NewProject.this, projectNameText.getText().toString()+groupNum);
            studentNum = 1;
            while(studentNum <= groupSize && !students.isEmpty()) {
                group = databaseHelper.getStudents();
                for(int index = 0; index < group.size(); index++){

                    if(group.get(index).getNotPartner().equals(students.get(0).getName()) || students.get(0).getNotPartner().equals(group.get(index).getName())){
                        notPartnerInGroup = true;
                    }
                }
                if(!notPartnerInGroup) {
                    databaseHelper.addStudent(students.get(0));
                    students.remove(0);
                    studentNum++;
                }else{
                    indexShuffle = rand.nextInt(students.size());
                    replace(0, indexShuffle);
                    notPartnerInGroup = false;
                }
            }
        }
    }

    public void generateGroups(View V){
        if(projectNameText.getText().toString().equals("") || groupSizeText.getText().toString().equals("")){
            Toast.makeText(NewProject.this, "Project name and group size required", Toast.LENGTH_SHORT).show();
        }else {
            groupSize = Integer.parseInt(groupSizeText.getText().toString());
            shuffle();
            lvStudents.setAdapter(studentArrayAdapter);
            System.out.println(students.toString());
            int numGroups = students.size() / groupSize;
            if (students.size() % groupSize != 0) {
                numGroups++;
            }
            assignGroups(numGroups);
            Intent i = new Intent(this, CourseProjects.class);
            i.putExtra("project name", projectNameText.getText().toString());
            i.putExtra("courseName", courseTitle);
            i.putExtra("numGroups", numGroups);
            startActivity(i);
        }
    }
}
package com.example.groupmeister;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

//SQLite code implemented below was adapted from Shad Sluiter's SQLite tutorial series on Youtube
public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String STUDENT_TABLE = "STUDENT_TABLE";
    public static final String COLUMN_STUDENT_NAME = "STUDENT_NAME";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NOT_PARTNER = "NOT_PARTNER";

    public DatabaseHelper(@Nullable Context context, String name) {
        super(context, name, null, 1);
    }

    //Called the first time a database is accessed
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + STUDENT_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_STUDENT_NAME + " TEXT, " + COLUMN_NOT_PARTNER + " TEXT)";

        db.execSQL(createTableStatement);
    }

    //Called if the database version number changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_STUDENT_NAME, student.getName());
        cv.put(COLUMN_NOT_PARTNER, student.getNotPartner());

        long insert = db.insert(STUDENT_TABLE, null, cv);
        if (insert == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean deleteStudent(Student student){
        //find given student in database and delete the student
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM "+STUDENT_TABLE+" WHERE "+COLUMN_ID+" = "+ student.getId();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }

    public List<Student> getStudents(){
        int count = 1;
        List<Student> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM "+ STUDENT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
        //Loop through the cursor
            do{
                String studentName = cursor.getString(1);

                Student newStudent = new Student(studentName, count);
                count++;
                returnList.add(newStudent);
            }while(cursor.moveToNext());
        }else{
            //failure, nothing added
        }
        //close both db and cursor when done
        cursor.close();
        db.close();
        return returnList;
    }
}

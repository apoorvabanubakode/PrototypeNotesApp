package com.example.abc.mydatabaseconnectivityapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class viewActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextTitle;
    private EditText editTextContent;
    private EditText editTextId;
    private Button btnPrev;
    private Button btnNext;
    private Button btnSave;
    private Button btnDelete;

    private static final String viewstring = "SELECT * FROM TryNotesTable";

    private SQLiteDatabase db;

    private Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        openDb();
        editTextId = (EditText) findViewById(R.id.editTextId);
        editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        editTextContent = (EditText) findViewById(R.id.editTextContent);

        btnPrev = (Button) findViewById(R.id.buttonPrev);
        btnNext = (Button) findViewById(R.id.buttonNext);
        btnSave = (Button) findViewById(R.id.buttonSave);
        btnDelete = (Button) findViewById(R.id.buttonDelete);

        btnNext.setOnClickListener(this);
        btnPrev.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        c=db.rawQuery(viewstring,null);
        c.moveToFirst();
        viewNotes();


    }

    protected void openDb(){
        db=openOrCreateDatabase("TryNotesDb", Context.MODE_PRIVATE,null);

    }
    protected  void viewNotes(){
        String id=c.getString(0);
        String title=c.getString(1);
        String content=c.getString(2);

        editTextId.setText(id);
        editTextTitle.setText(title);
        editTextContent.setText(content);
    }

    protected void viewAllTile(){
        while(!c.isLast()){
            String title=c.getString(1);
            //incomplete ;; trying to view all titles ;; put this in the new activity_view_all_title wala activity.

        }

    }

    protected void moveNext(){

    }

    protected void movePrev(){

    }

    protected void saveContent(){
        String title = editTextTitle.getText().toString().trim();
        String content = editTextContent.getText().toString().trim();
        String id = editTextId.getText().toString().trim();


        String updatestring="UPDATE TryNotesTable SET title='"+title+"',content='"+content+"'WHERE id="+id+";";
        db.execSQL(updatestring);

        Toast.makeText(getApplicationContext(), "Records Saved Successfully", Toast.LENGTH_LONG).show();
        c = db.rawQuery(viewstring, null);
        c.moveToPosition(Integer.parseInt(id));


    }
    protected void deleteContent(){
       // to be written ajun


    }
    @Override
    public void onClick(View v) {
        if (v == btnNext) {
            moveNext();
        }

        if (v == btnPrev) {
            movePrev();
        }

        if (v == btnSave) {
            saveContent();
        }

        if (v == btnDelete) {
            deleteContent();
        }

    }
}

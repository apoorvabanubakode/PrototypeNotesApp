package com.example.abc.mydatabaseconnectivityapp;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextTitle;
    private EditText editTextContent;
    private Button btnSave;
    private Button btnView;

    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createDatabase();
        editTextTitle=(EditText)findViewById(R.id.EditTextTitle);
        editTextContent=(EditText)findViewById(R.id.EditTextContent);

        btnSave=(Button)findViewById(R.id.SaveButton);
        btnView=(Button)findViewById(R.id.ViewButton);

        btnSave.setOnClickListener(this);
        btnView.setOnClickListener(this);



    }
    protected void createDatabase(){

        db=openOrCreateDatabase("TryNotesDb", Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS TryNotesTable(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, title VARCHAR,content VARCHAR);");


    }

    protected void insertInDb(){

    String title=editTextTitle.getText().toString().trim();
        String content=editTextContent.getText().toString().trim();

        String query="INSERT INTO TryNotesTable(title,content) VALUES('"+title+"','"+content+"');";
        db.execSQL(query);
        Toast.makeText(getApplicationContext(),"Saved Successfully", Toast.LENGTH_LONG).show();

        editTextTitle.setText(" ");
        editTextContent.setText(" ");


    }
    @Override
    public void onClick(View v) {
    if(v==btnSave)
        insertInDb();
        if(v==btnView)
            viewContent();
    }

    protected void viewContent(){
        Intent intent=new Intent(this,viewActivity.class);
        startActivity(intent);
        finish();
    }
}

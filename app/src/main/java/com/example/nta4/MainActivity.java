package com.example.nta4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    EditText titleInput, contentInput;
    Button saveBtn, showListBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleInput = findViewById(R.id.titleInput);
        contentInput = findViewById(R.id.contentInput);
        saveBtn = findViewById(R.id.saveButton);
        showListBtn = findViewById(R.id.showListButton);

        saveBtn.setOnClickListener(v -> {
            String title = titleInput.getText().toString();
            String content = contentInput.getText().toString();
            if (!title.isEmpty() && !content.isEmpty()) {
                try {
                    FileOutputStream fos = openFileOutput(title + ".txt", MODE_PRIVATE);
                    fos.write(content.getBytes());
                    fos.close();
                    Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show();
                    titleInput.setText("");
                    contentInput.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, "Both fields required", Toast.LENGTH_SHORT).show();
            }
        });

        showListBtn.setOnClickListener(v -> startActivity(new Intent(this, NotesListActivity.class)));
    }
}

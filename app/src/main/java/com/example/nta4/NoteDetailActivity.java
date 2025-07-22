package com.example.nta4;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.io.FileOutputStream;

public class NoteDetailActivity extends AppCompatActivity {

    EditText titleEdit, contentEdit;
    Button updateBtn;
    String originalTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        titleEdit = findViewById(R.id.titleEdit);
        contentEdit = findViewById(R.id.contentEdit);
        updateBtn = findViewById(R.id.updateButton);


        originalTitle = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");

        titleEdit.setText(originalTitle);
        contentEdit.setText(content);

        updateBtn.setOnClickListener(v -> {
            String newTitle = titleEdit.getText().toString();
            String newContent = contentEdit.getText().toString();
            deleteFile(originalTitle + ".txt");
            try {
                FileOutputStream fos = openFileOutput(newTitle + ".txt", MODE_PRIVATE);
                fos.write(newContent.getBytes());
                fos.close();
                finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


    }
}


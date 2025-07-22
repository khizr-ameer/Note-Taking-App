package com.example.nta4;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;
import java.io.*;
import java.util.*;

public class NotesListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<String> noteTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        noteTitles = new ArrayList<>();
        String[] files = fileList();
        for (String file : files) {
            if (file.endsWith(".txt")) {
                noteTitles.add(file.replace(".txt", ""));
            }
        }

        recyclerView.setAdapter(new NoteAdapter(noteTitles));
    }

    class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
        List<String> titles;

        NoteAdapter(List<String> titles) {
            this.titles = titles;
        }

        class NoteViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            ImageView deleteIcon;

            public NoteViewHolder(View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.noteTitle);
                deleteIcon = itemView.findViewById(R.id.deleteIcon);

                // Open note when clicked
                itemView.setOnClickListener(v -> {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        String selectedTitle = titles.get(position);
                        StringBuilder content = new StringBuilder();
                        try {
                            BufferedReader br = new BufferedReader(
                                    new InputStreamReader(openFileInput(selectedTitle + ".txt"))
                            );
                            String line;
                            while ((line = br.readLine()) != null) {
                                content.append(line).append("\n");
                            }
                            br.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(NotesListActivity.this, "Error loading note", Toast.LENGTH_SHORT).show();
                        }

                        Intent intent = new Intent(NotesListActivity.this, NoteDetailActivity.class);
                        intent.putExtra("title", selectedTitle);
                        intent.putExtra("content", content.toString().trim());
                        startActivity(intent);
                    }
                });

                // Delete note when delete icon is clicked
                deleteIcon.setOnClickListener(v -> {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        String fileName = titles.get(position) + ".txt";

                        new AlertDialog.Builder(NotesListActivity.this)
                                .setTitle("Delete Note")
                                .setMessage("Are you sure you want to delete this note?")
                                .setPositiveButton("Delete", (dialog, which) -> {
                                    boolean deleted = deleteFile(fileName);
                                    if (deleted) {
                                        titles.remove(position);
                                        notifyItemRemoved(position);
                                        Toast.makeText(NotesListActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(NotesListActivity.this, "Failed to delete note", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton("Cancel", null)
                                .show();
                    }
                });
            }
        }

        @Override
        public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.note_item, parent, false);
            return new NoteViewHolder(view);
        }

        @Override
        public void onBindViewHolder(NoteViewHolder holder, int position) {
            holder.title.setText(titles.get(position));
        }

        @Override
        public int getItemCount() {
            return titles.size();
        }
    }
}

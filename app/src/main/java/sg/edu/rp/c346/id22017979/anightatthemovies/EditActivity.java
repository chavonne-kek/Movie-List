package sg.edu.rp.c346.id22017979.anightatthemovies;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {
    EditText etYear, etGenre, etTitle, etId;
    Button btnDel, btnUpdate,btnBack;
    Spinner spn;
    Movie data;
    String newRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);
        etTitle = findViewById(R.id.etTitle);
        etGenre = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);
        btnDel = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        spn = findViewById(R.id.spn);
        etId = findViewById(R.id.etId);
        btnBack = findViewById(R.id.btnBack);

        Intent intent = getIntent();
        data = (Movie) intent.getSerializableExtra("data");

        int pos = 0;
        String rating = data.getRating();
        etTitle.setText(data.getTitle());
        etGenre.setText(data.getGenre());
        etYear.setText(data.getYear());
        etId.setText(String.valueOf(data.getId()));
        etId.setEnabled(false);

        if(rating.equalsIgnoreCase("g")){
            pos = 0;
        } else if (rating.equalsIgnoreCase("pg")) {
            pos = 1;
        }else if (rating.equalsIgnoreCase("pg13")){
            pos = 2;
        } else if (rating.equalsIgnoreCase("nc16")) {
            pos = 3;
        } else if (rating.equalsIgnoreCase("m18")) {
            pos = 4;
        } else if (rating.equalsIgnoreCase("r21")){
            pos = 5;
        }
        
        spn.setSelection(pos);
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        newRating = "G";
                        break;
                    case 1:
                        newRating = "PG";
                        break;
                    case 2:
                        newRating = "PG13";
                        break;
                    case 3:
                        newRating = "NC16";
                        break;
                    case 4:
                        newRating = "M18";
                        break;
                    case 5:
                        newRating = "R21";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(EditActivity.this);

                String movie = etTitle.getText().toString();
                String genre = etGenre.getText().toString();
                String year = etYear.getText().toString();

                data.setMovie(movie, genre, year, newRating);
                db.updateMovie(data);
                db.close();

                Toast.makeText(EditActivity.this, "Movie Updated", Toast.LENGTH_SHORT).show();

                finish();
                Intent i = new Intent(EditActivity.this,
                        ListActivity.class);
                startActivity(i);
            }

        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movie = data.getTitle();
                AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);

                builder.setTitle("Danger");
                builder.setMessage("Are you sure you want to delete the movie " + movie + "?");
                builder.setCancelable(false);

                builder.setNegativeButton("Cancel", null);
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper db = new DBHelper(EditActivity.this);
                        db.deleteMovie(data.getId());
                        db.close();

                        Toast.makeText(EditActivity.this, "Movie deleted", Toast.LENGTH_SHORT).show();

                        finish();
                        Intent i = new Intent(EditActivity.this,
                                ListActivity.class);
                        startActivity(i);

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);

                builder.setTitle("Danger");
                builder.setMessage("Are you sure you want to discard the changes?");
                builder.setCancelable(false);

                builder.setNegativeButton("Cancel", null);
                builder.setPositiveButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        Intent i = new Intent(EditActivity.this, ListActivity.class);
                        startActivity(i);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
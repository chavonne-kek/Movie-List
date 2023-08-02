package sg.edu.rp.c346.id22017979.anightatthemovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    TextView btnFilter,btnBack;
    ListView lv;
    CustomAdapter aaMovies;
    ArrayList<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        btnFilter = findViewById(R.id.btnFilter);
        btnBack = findViewById(R.id.btnBack);
        lv = findViewById(R.id.lv);
        movieList = new ArrayList<>();

        DBHelper db = new DBHelper(getApplicationContext());
        movieList = db.getMovies();
        db.close();

        aaMovies = new CustomAdapter(this, R.layout.row, movieList);
        lv.setAdapter(aaMovies);
        aaMovies.notifyDataSetChanged();


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Movie data = movieList.get(position);
                Intent intent = new Intent(ListActivity.this,
                        EditActivity.class);
                intent.putExtra("data", data);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(ListActivity.this,
                        MainActivity.class);
                startActivity(i);
            }
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Movie> filteredMovie = db.getFilteredMovie();
                aaMovies.clear();
                aaMovies.addAll(filteredMovie);
                aaMovies.notifyDataSetChanged();

            }
        });
    }

}

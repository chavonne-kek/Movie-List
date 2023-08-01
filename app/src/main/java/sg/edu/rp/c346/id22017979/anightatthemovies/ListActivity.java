package sg.edu.rp.c346.id22017979.anightatthemovies;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
    }
}

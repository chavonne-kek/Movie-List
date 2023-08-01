package sg.edu.rp.c346.id22017979.anightatthemovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Movie> movieList;
    public CustomAdapter(Context context, int resource, ArrayList<Movie> objects){
        super(context, resource,objects);
        parent_context = context;
        layout_id = resource;
        movieList = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvTitle = rowView.findViewById(R.id.tvTitle);
        TextView tvGenre = rowView.findViewById(R.id.tvGenre);
        TextView tvYear = rowView.findViewById(R.id.tvYear);
        ImageView iv = rowView.findViewById(R.id.iv);

        Movie currentVersion = movieList.get(position);
        String rating = currentVersion.getRating();

        tvTitle.setText(currentVersion.getTitle());
        tvGenre.setText(currentVersion.getGenre());
        tvYear.setText(currentVersion.getYear());

        if(rating.equalsIgnoreCase("g")){
            iv.setImageResource(R.drawable.rating_g);
        } else if (rating.equalsIgnoreCase("pg")) {
            iv.setImageResource(R.drawable.rating_pg);
        }else if (rating.equalsIgnoreCase("pg13")){
            iv.setImageResource(R.drawable.rating_pg13);
        } else if (rating.equalsIgnoreCase("nc16")) {
            iv.setImageResource(R.drawable.rating_nc16);
        } else if (rating.equalsIgnoreCase("m18")) {
            iv.setImageResource(R.drawable.rating_m18);
        } else if (rating.equalsIgnoreCase("r21")){
            iv.setImageResource(R.drawable.rating_r21);
        }

        return rowView;
    }
}

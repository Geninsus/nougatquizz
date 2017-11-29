package nougatteam.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nougatteam.myapplication.pojo.ScorePojo;

/**
 * Created by Guillaume on 11/29/2017.
 */

public class scoreAdapter extends ArrayAdapter {
    public scoreAdapter(Context context, ArrayList<ScorePojo> scores) {
        super(context, 0, scores);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ScorePojo score = (ScorePojo) getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_score_list, parent, false);
        }
        // Lookup view for data population
        TextView pseudo = (TextView) convertView.findViewById(R.id.pseudo);
        TextView theme = (TextView) convertView.findViewById(R.id.theme);
        TextView points = (TextView) convertView.findViewById(R.id.points);
        TextView rank = (TextView) convertView.findViewById(R.id.rank);
        // Populate the data into the template view using the data object
        rank.setText(position);
        pseudo.setText(score.name);
        theme.setText(score.theme);
        points.setText(score.score);
        // Return the completed view to render on screen
        return convertView;
    }
}

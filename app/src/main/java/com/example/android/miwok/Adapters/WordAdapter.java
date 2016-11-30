package com.example.android.miwok.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.miwok.Models.Word;
import com.example.android.miwok.R;

import java.util.ArrayList;

/**
 * Created by kristenwoodward on 11/27/16.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorId = 0;

    public WordAdapter(Activity context, ArrayList<Word> words, int colorId) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
        mColorId = colorId;
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID default_text_view
        TextView defaultTranslation = (TextView) listItemView.findViewById(R.id.default_text_view);
        // Get the version name from the current Word object and
        // set this text on the name TextView
        defaultTranslation.setText(currentWord.getDefaultTranslation());

        // Find the TextView in the list_item.xml layout with the ID miwok_text_view
        TextView miwokTranslation = (TextView) listItemView.findViewById(R.id.miwok_text_view);

        // Get the Miwok translation from the current Word object and
        // set this text on the miwokTranslation TextView
        miwokTranslation.setText(currentWord.getMiwokTranslation());

        // set the background color based on the current word
        LinearLayout wordLayout = (LinearLayout) listItemView.findViewById(R.id.words_layout);
        wordLayout.setBackgroundColor(getContext().getColor(mColorId));

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView wordImage = (ImageView) listItemView.findViewById(R.id.word_icon);

        // check to see if there is an image associated with the word
        if (currentWord.hasImage()) {
            // if the word has an image, set the image to iconView
            wordImage.setImageResource(currentWord.getImageId());

            //make sure the view is visible
            wordImage.setVisibility(View.VISIBLE);
        } else
            // if there isn't an image associated with the word, make the ImageView !visible
            wordImage.setVisibility(View.GONE);

        ImageView playButton = (ImageView) listItemView.findViewById(R.id.play_button);
        playButton.setBackgroundColor(getContext().getColor(mColorId));

        if (currentWord.hasAudio()) {
            playButton.setImageResource(R.drawable.ic_play_arrow_white_24dp);
            //make sure the view is visible
            playButton.setVisibility(View.VISIBLE);
        } else
            playButton.setVisibility(View.GONE);


        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }


}

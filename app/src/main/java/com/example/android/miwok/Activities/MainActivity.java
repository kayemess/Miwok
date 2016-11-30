/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.android.miwok.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        // Find the view that shows the Numbers category
        TextView numbers = (TextView) findViewById(R.id.numbers);

        // Set a click listener on that view
        numbers.setOnClickListener(new View.OnClickListener() {

            // this code will be executed when the numbers category is clicked on
            @Override
            public void onClick (View view){
                Intent showNumbersList = new Intent(MainActivity.this, NumbersActivity.class);
                startActivity(showNumbersList);
            }
        });

        // Find the view that shows the Colors category
        TextView colors = (TextView) findViewById(R.id.colors);

        // Set a click listener on that view
        colors.setOnClickListener(new View.OnClickListener() {

            // this code will be executed when the numbers category is clicked on
            @Override
            public void onClick (View view){
                Intent showColorsList = new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(showColorsList);
            }
        });

        // Find the view that shows the Family category
        TextView family = (TextView) findViewById(R.id.family);

        // Set a click listener on that view
        family.setOnClickListener(new View.OnClickListener() {

            // this code will be executed when the numbers category is clicked on
            @Override
            public void onClick (View view){
                Intent showFamilyList = new Intent(MainActivity.this, FamilyActivity.class);
                startActivity(showFamilyList);
            }
        });

        // Find the view that shows the Phrases category
        TextView phrases = (TextView) findViewById(R.id.phrases);

        // Set a click listener on that view
        phrases.setOnClickListener(new View.OnClickListener() {

            // this code will be executed when the numbers category is clicked on
            @Override
            public void onClick (View view){
                Intent showPhrasesList = new Intent(MainActivity.this, PhrasesActivity.class);
                startActivity(showPhrasesList);
            }
        });

    }

}

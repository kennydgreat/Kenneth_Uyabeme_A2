package com.example.kenny.kenneth_uyabeme_a2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ListView;

public class MainActivity extends Activity {
    //Fields
    ListView socialMedialistview ; // ListView containing the social media account info
    CheckBox displayInfoCheckBox; // checkbox to display/hide the account ids
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Getting reference to the social media listview and checkbutton
        socialMedialistview = findViewById(R.id.social_media_list_view);
        displayInfoCheckBox = findViewById(R.id.display_info_check_box);



    }
}

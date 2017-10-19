package com.example.kenny.kenneth_uyabeme_a2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class DetailActivity extends Activity {
    //EditTexts
    EditText socialMediaSiteNameEditText;
    EditText userIDEditText;
    EditText numberOfContactsEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //Getting references to editTexts
        socialMediaSiteNameEditText = findViewById(R.id.social_media_site_edittext);
        userIDEditText = findViewById(R.id.user_id_edit_text);
        numberOfContactsEditText = findViewById(R.id.num_of_contacts_edit_text);
        //Getting the data from mainActivity and populating the editText with current account info
        //(before editing)
        getMainActivityData();

    }
    /*This method
    *1) Gets the intent from MainActivity
    * 2) Get the account data if is was sent
    * 3) sets the appropriate editTexts
     *  */
    public void getMainActivityData(){
        Intent intent = getIntent();
        if (intent != null){
            socialMediaSiteNameEditText.setText(intent.getStringExtra("Social Media Site"));
            userIDEditText.setText(intent.getStringExtra("User ID"));
            numberOfContactsEditText.setText(Integer.toString(intent.getIntExtra("Number of Contacts", 0)));
        }
    }
}

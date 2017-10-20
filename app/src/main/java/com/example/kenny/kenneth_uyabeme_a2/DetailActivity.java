package com.example.kenny.kenneth_uyabeme_a2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailActivity extends Activity {
    //EditTexts
    EditText socialMediaSiteNameEditText;
    EditText userIDEditText;
    EditText numberOfContactsEditText;
    Button saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //Getting references to editTexts and button
        socialMediaSiteNameEditText = findViewById(R.id.social_media_site_edittext);
        userIDEditText = findViewById(R.id.user_id_edit_text);
        numberOfContactsEditText = findViewById(R.id.num_of_contacts_edit_text);
        saveButton = findViewById(R.id.save_button);
        //Getting the data from mainActivity and populating the editText with current account info
        //(before editing)
        getMainActivityData();
        /*Once the save button is click the
        * 1) editText Values are taken and put in the intent
        * 2) the intent is sent to mainActivity
        * 3) this activity is terminated*/
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                data.putExtra("Social Media Site", socialMediaSiteNameEditText.getText().toString());
                data.putExtra("User ID", userIDEditText.getText().toString());
                data.putExtra("Number of Contacts", Integer.valueOf(numberOfContactsEditText.getText().toString()));

                setResult(RESULT_OK, data);
                finish();
            }
        });


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
            numberOfContactsEditText.setText(Integer.toString(intent.getIntExtra("Number of Contacts", MainActivity.DEFUALT_CONTACTS_NUM)));
        }
    }

}

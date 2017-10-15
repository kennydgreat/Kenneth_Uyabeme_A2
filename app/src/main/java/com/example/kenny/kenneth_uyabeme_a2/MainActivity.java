package com.example.kenny.kenneth_uyabeme_a2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    //Fields
    ListView socialMedialistview ; // ListView containing the social media account info
    CheckBox displayInfoCheckBox; // checkbox to display/hide the account ids
    SocialMediaAccountAdapter adapter; // adapter for listView
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Getting reference to the social media listview and checkbutton
        socialMedialistview = findViewById(R.id.social_media_list_view);
        displayInfoCheckBox = findViewById(R.id.display_info_check_box);
        loadSocialMediaAccounts();//loading data into the social media listView
        //Setting checkBox listener
        displayInfoCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {/*If checked make the userID and number of contacts textViews visible
            else make them invisible*/
                if(displayInfoCheckBox.isChecked()){
                    findViewById(R.id.user_id).setVisibility(View.VISIBLE);
                    findViewById(R.id.num_of_contacts_textview).setVisibility(View.VISIBLE);
                }else{
                    findViewById(R.id.user_id).setVisibility(View.INVISIBLE);
                    findViewById(R.id.num_of_contacts_textview).setVisibility(View.INVISIBLE);
                }

            }
        });

    }

    private void loadSocialMediaAccounts(){
        //Getting the data the SocialMediaAccount class
        ArrayList<SocialMediaAccount> accounts = SocialMediaAccount.getSocialMedia();
        adapter = new SocialMediaAccountAdapter(this,
                                                     R.layout.list_media_info,
                                                    accounts);
        socialMedialistview.setAdapter(adapter);
    }
}

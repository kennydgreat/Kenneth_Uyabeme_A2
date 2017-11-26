package com.example.kenny.kenneth_uyabeme_a2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kenny.kenneth_uyabeme_a2.Model.SocialMediaAccount;

import java.util.ArrayList;
/*This the main of activity of the social media app where the user sees the data for each
 *social media account
 */
public class MainActivity extends Activity {
    //Fields
    ListView socialMedialistview ; // ListView containing the social media account info
    CheckBox displayInfoCheckBox; // checkbox to display/hide the account userID and number of contacts
    SocialMediaAccountAdapter adapter; // adapter for listView
    final static int DETAIL_ACTIVITY = 2; //integer ID for second activity (DetailActivity)
    static public final int DEFUALT_CONTACTS_NUM = 0; //if the default number of contacts isn't filled
    int currentItemPosition; // position of the account item clicked in the listView
    SharedPreferences userPreferences; // SharedPreferences object for saves the checkBox  value
    SharedPreferences.Editor editor;// editor to write in the checkBox value
    /*
    * This override of onCreate
    * 1) loads the data from the social media model class into the listview
    * 2) assigns an onclick listener to the display info checkbox which displays the account info if checkbox
    *    is checked
    * 3) assigns an OnItemClick listener to the listVIew which opens DetailActivity to edit the chosen account*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Getting references to the social media listview and checkbutton
        socialMedialistview = findViewById(R.id.social_media_list_view);
        displayInfoCheckBox = findViewById(R.id.display_info_check_box);
        //Initializing userPreferences for this activity and creating an editor
        userPreferences = getSharedPreferences("userPreferences",MODE_PRIVATE);
        editor = userPreferences.edit();
        loadSocialMediaAccounts();//loading data into the social media listView

        /*This block of code is for the displayInfoCheckBox listener is
        1) Setting checkBox listener so the user  ID and number of contacts  are displayed if the
            checkbox is checked (refer to adapter class' getView method to see how this is done)
        2) writing the displayInfoCheckBox Value in SharedPreferences
        */
        displayInfoCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putBoolean("displayInfoCheckBoxValue", displayInfoCheckBox.isChecked());
                editor.apply();
                adapter.notifyDataSetChanged(); /*This triggers the recreation of items' views in the listView.
                getView from the adapter class is called and the ID and number of contacts made visible or not */
            }
        });
        //Creating and setting listView's OnItemClickListener
        ListViewItemListener socialMediaListListener = new ListViewItemListener();
        socialMedialistview.setOnItemClickListener(socialMediaListListener);
    }


    /*This method
    * 1) creates arraylist of SocialMediaAccount objects using the getSocialMedia() method from
    * the SocialMediaAccount class
    * 2) creates and sets the adapter for the listView using the arraylist*/
    private void loadSocialMediaAccounts(){
        ArrayList<SocialMediaAccount> accounts = SocialMediaAccount.getSocialMedia(this);
        adapter = new SocialMediaAccountAdapter(this,
                                                     R.layout.list_media_info,
                                                    accounts);
        socialMedialistview.setAdapter(adapter);
    }

/* ListView item listener inner class. This implementation of onItemClicklistener
* 1) gets the data and position of the current item
* 2)Sets the position variable for updating once user clicks save
* 3) creates an intent and feeds all that information into the intent
* 4) starts up DetailActivity
* */
    private class ListViewItemListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            //Getting the account object of account select
            SocialMediaAccount current_account = (SocialMediaAccount) adapterView.getItemAtPosition(position);
            /*Creating the intent to start DetailActivity and putting in the account data to used
             by DetailActivity populate it's editTexts once it starts up
             *  */
            currentItemPosition = position ;
            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
            intent.putExtra("Social Media Site", current_account.getName())
                    .putExtra("User ID", current_account.getUserId())
                    .putExtra("Number of Contacts", current_account.getNumberOfContacts());
            startActivityForResult(intent, DETAIL_ACTIVITY);

        }
    }
    /**
     * This override of onActivityResult
     * 1) gets the account object of the ListView item that was edited
     * 2) updates the account values with the values sent with the intent
     * 3) notifies the adapter that an item has been updated so the ListView displays the right
     * data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DETAIL_ACTIVITY){
            if (resultCode == RESULT_OK){
                SocialMediaAccount edittedAccount = (SocialMediaAccount) adapter.getItem(currentItemPosition);
                edittedAccount.setName(data.getStringExtra("Social Media Site"));
                edittedAccount.setUserId(data.getStringExtra("User ID"));
                edittedAccount.setNumberOfContacts(data.getIntExtra("Number of Contacts", DEFUALT_CONTACTS_NUM));
                adapter.notifyDataSetChanged();
            }
        }
    }
    /*This override of onPause
    * 1) calls the super class' onPause
    * 2) saves the value of the display info checkBox in shardPerferences*/
    @Override
    protected void onPause(){
        super.onPause();
        editor.putBoolean("displayInfoCheckBoxValue", displayInfoCheckBox.isChecked());
        editor.apply();    }
    /*
    * This override of onResume
    * 1) calls the super class' onResume method
    * 2) sets the displayInfoCheckBox to be what it was the last time the app was open through userPreferences*/
    @Override
    protected void onResume(){
        super.onResume();
       displayInfoCheckBox.setChecked(userPreferences.getBoolean("displayInfoCheckBoxValue", false));
    }
}

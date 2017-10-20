package com.example.kenny.kenneth_uyabeme_a2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    //Fields
    ListView socialMedialistview ; // ListView containing the social media account info
    CheckBox displayInfoCheckBox; // checkbox to display/hide the account ids
    SocialMediaAccountAdapter adapter; // adapter for listView
    final static int DETAIL_ACTIVITY = 2;
    static public final int DEFUALT_CONTACTS_NUM = 0; //if the default number of contacts isn't filled
    int currentItemPosition; // position of the account item clicked in the listView
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Getting reference to the social media listview and checkbutton
        socialMedialistview = findViewById(R.id.social_media_list_view);
        displayInfoCheckBox = findViewById(R.id.display_info_check_box);
        loadSocialMediaAccounts();//loading data into the social media listView

        //Setting checkBox listener so the user  ID and number of contacts  are displayed if the
        //checkbox is checked
        displayInfoCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //For every item in the listview get the child viewgroup( which contains the
                //Text views for each social media account)
                    for (int i = 0; i < socialMedialistview.getCount(); i++) {
                        ViewGroup childView = (ViewGroup) socialMedialistview.getChildAt(i);
                        //Since we know the userid and num of conntacts are the second and third
                        //textView we simply get the second and third (1 and 2 indies)
                        TextView childTextView = (TextView) childView.getChildAt(1);
                        TextView childTextView2 = (TextView) childView.getChildAt(2);
                        //set the visibility based on the whether the checkbox is
                        //checked or not.
                        if (displayInfoCheckBox.isChecked()) {
                            childTextView.setVisibility(View.VISIBLE);
                            childTextView2.setVisibility(View.VISIBLE);
                        } else {
                            childTextView.setVisibility(View.GONE);
                            childTextView2.setVisibility(View.GONE);
                        }
                    }

            }
        });
        displayInfoCheckBox.setChecked(true); //Make the checkbox checked on startup

        ListViewItemListener socialMediaListListener = new ListViewItemListener();
        socialMedialistview.setOnItemClickListener(socialMediaListListener);

    }

    private void loadSocialMediaAccounts(){
        //Getting the data the SocialMediaAccount class
        ArrayList<SocialMediaAccount> accounts = SocialMediaAccount.getSocialMedia();
        adapter = new SocialMediaAccountAdapter(this,
                                                     R.layout.list_media_info,
                                                    accounts);
        socialMedialistview.setAdapter(adapter);
    }
/* ListView item answer class. This listener
* 1) gets the data and position of the current item
* 2)Sets the position variable for updating once user saves
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
     * 1) gets the account object of ListView item that was edited
     * 2) updates the account values sent from the intent
     * 3) notifies the adapter that item has been updated so the ListView displays the right
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
}

package com.example.kenny.kenneth_uyabeme_a2;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kenny.kenneth_uyabeme_a2.Model.SocialMediaAccount;

import java.util.ArrayList;

/**
 * Created by kenny on 2017-10-15.
 * This is the customer adapter class that will help display the data from the SocialMediaAccount POJO
 * in the Social Media Listview
 */

public class SocialMediaAccountAdapter extends ArrayAdapter {
    //Fields
    private Context context;//for getting the LayoutInflator
    private int resource;// this will be use to get the layout xml for the each social media account
    SharedPreferences userPreferences; // declaring a sharedPreferences object
    /*This constructor
    * 1) calls the super class constructor
    * 2) sets the object variables to the respective parameters
    * 3) gets the SharePreferences object using the context variable*/
    public SocialMediaAccountAdapter(Context context, int resource, ArrayList<SocialMediaAccount> accounts ) {
        super(context, resource, accounts);
        this.context = context;
        this.resource = resource;
        //Getting the userPreferences
        userPreferences = context.getSharedPreferences("userPreferences",Context.MODE_PRIVATE);
    }
    @NonNull
    @Override
    /*This override of getView creates a view for each social media account using the list_media_info layout.
    *it
    *1) inflates convertView if it is null
    *2) Gets the data of the account for the item view being created
    *3) sets the respective textViews with the data from the account
    *4) Depending on the value from the display info checkbox gotten from sharedPre
    * -ferences, sets the 2 last textViews to be visible or gone.
    * 5)returns the view that was created
    * */
    public View getView(int position, View convertView, @NonNull ViewGroup parent){
        if (convertView == null){ // inflating the view if it isn't inflated
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(resource, parent,false);
        }
        //Getting the current social media account
        SocialMediaAccount account = (SocialMediaAccount) getItem(position);
        //Making a textView reference for the site name, userID and number of contacts textView of that socialMedia account
        TextView nameTextView = convertView.findViewById(R.id.social_media_site_textview);
        TextView userIdTextView = convertView.findViewById(R.id.user_id);
        TextView numberOfContactsTextView = convertView.findViewById(R.id.num_of_contacts_textview);
        //The populating the TextView with info from the account.
        nameTextView.setText(account.getName());
        userIdTextView.setText(account.getUserId());
        numberOfContactsTextView.setText(String.format("%d",account.getNumberOfContacts()));
        //Setting the visibility of the userID and number of contacts textViews based on checkbox value from sharedPreferences
        if (userPreferences.getBoolean("displayInfoCheckBoxValue", false)){
            userIdTextView.setVisibility(View.VISIBLE);
            numberOfContactsTextView.setVisibility(View.VISIBLE);
        }else{
            userIdTextView.setVisibility(View.GONE);
            numberOfContactsTextView.setVisibility(View.GONE);
        }


        //Returning the modified view
        return convertView;
    }
}

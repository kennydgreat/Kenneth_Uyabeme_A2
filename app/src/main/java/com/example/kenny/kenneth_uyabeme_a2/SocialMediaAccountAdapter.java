package com.example.kenny.kenneth_uyabeme_a2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kenny.kenneth_uyabeme_a2.Model.SocialMediaAccount;

import java.util.ArrayList;

/**
 * Created by kenny on 2017-10-15.
 * This is the adapter that will help display the data from SocialMediaAccount POJO
 * in Social Media Listview
 */

public class SocialMediaAccountAdapter extends ArrayAdapter {
    //Fields
    private Context context;//for getting the LayoutInflator
    private int resource;// this will be use to get the layout xml for the each social media account

    public SocialMediaAccountAdapter(Context context, int resource, ArrayList<SocialMediaAccount> accounts ) {
        super(context, resource, accounts);// calling supeer class constructor
        this.context = context;//setting the context
        this.resource = resource; //setting the resources

    }
    @Override
    //This creates a view for each social media account using the list_media_info layout
    public View getView(int position, View convertView, ViewGroup parent){
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
        numberOfContactsTextView.setText(Integer.toString(account.getNumberOfContacts()));
        //Setting the visibility of the userID and number of contacts textViews based on the visible variable of the account objects
        if (account.isVisible()){
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

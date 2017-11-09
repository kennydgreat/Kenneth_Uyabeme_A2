package com.example.kenny.kenneth_uyabeme_a2.Model;

import java.util.ArrayList;

/**
 * Created by kenny on 2017-10-15.
 * This class is the model which holds the data for the Social Media Accounts apps
 *
 */

public class SocialMediaAccount {
    //Class fields
    private String name;// Name of the social media site
    private String userId; // the site's user ID
    private int numberOfContacts; // the account's number of contacts
    private boolean visible = true ; // variable to keep track of the visibility of the account's textViews
    //Constructor
    public SocialMediaAccount(String name, String userId, int numberOfContacts){
        //setting fields
        this.name = name;
        this.userId = userId;
        this.numberOfContacts = numberOfContacts;
    }

    //Name setter and getter methods
    //---------
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //-------------

    //User ID getter and setters
    //----------------
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    //Number of Contacts getter and setter
    //-----------------
    public int getNumberOfContacts() {
        return numberOfContacts;
    }

    public void setNumberOfContacts(int numberOfContacts) {
        this.numberOfContacts = numberOfContacts;
    }
    //-----------------


    //This creates the social media account data and returns the data(arraylist)
    public static ArrayList<SocialMediaAccount> getSocialMedia(){
        ArrayList<SocialMediaAccount> accounts = new ArrayList<>();
        accounts.add(new SocialMediaAccount("Facebook", "Vein_doe", 300));
        accounts.add(new SocialMediaAccount("Instagram", "Vein_DDD", 25));
        accounts.add(new SocialMediaAccount("Tumblr", "Vein902", 90));

        return accounts;
    }
// Getter and setter for visible variable
    public boolean isVisible() {
        return visible;
    }

    public void setVisibility(boolean visible) {
        this.visible = visible;
    }
}

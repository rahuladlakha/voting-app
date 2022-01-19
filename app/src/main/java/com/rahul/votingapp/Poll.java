package com.rahul.votingapp;

import java.io.Serializable;
import java.util.ArrayList;

public class Poll implements Serializable {
    public String question;
    public String createdBy;
    public String createdOn;
    public ArrayList<Integer> optionsVotes;
    public ArrayList<String> options;

    public Poll(String question, String createdBy, String createdOn, ArrayList<String> options) {
        this.question = question;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        optionsVotes = new ArrayList<Integer>();
        for(int i=0; i<options.size(); i++) {
            optionsVotes.add(0);
        }
        this.options = options;
    }
}

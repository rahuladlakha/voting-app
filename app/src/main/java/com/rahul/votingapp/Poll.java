package com.rahul.votingapp;

import java.io.Serializable;
import java.util.ArrayList;

public class Poll implements Serializable {
    public String question;
    public String createdBy;
    public String createdOn;
    public ArrayList<Integer> optionsVotes;
    public ArrayList<String> options;

    public Poll(){};

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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public ArrayList<Integer> getOptionsVotes() {
        return optionsVotes;
    }

    public void setOptionsVotes(ArrayList<Integer> optionsVotes) {
        this.optionsVotes = optionsVotes;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }
}

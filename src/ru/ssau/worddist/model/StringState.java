package ru.ssau.worddist.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StringState {
    private String state;

    public StringState() {
        this("");
    }

    public StringState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
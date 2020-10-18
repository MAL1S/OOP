package com.company;

public class Data {
    String sentence;
    String subString;
    int number;

    public Data(String sentence) {
        this.sentence = sentence;
    }

    public Data(String sentence, String subString) {
        this.sentence = sentence;
        this.subString = subString;
    }

    public Data(String sentence, int number) {
        this.sentence = sentence;
        this.number = number;
    }

    public String getSentence() {
        return this.sentence;
    }

    public String getSubString() {
        return this.subString;
    }

    public int getNumber() {
        return this.number;
    }
}

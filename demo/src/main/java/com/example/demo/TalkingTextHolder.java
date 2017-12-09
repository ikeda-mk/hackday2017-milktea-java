package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TalkingTextHolder {

    private List<String> textList = new CopyOnWriteArrayList<>();

    public void addText(String text){
        textList.add(text);
    }

    public String getLatestText(){
        if(textList.size() == 0){
            return null;
        }
        for(String text : textList){
            System.out.println(text);
        }
       return  textList.get(textList.size()-1);
    }
}

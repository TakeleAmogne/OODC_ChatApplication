package com.example.kumarsi.chatapplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kumarsi on 06-Oct-16.
 */

public class ChatHistory implements ChatObservable{

    private ArrayList<String> history;
    private Set<HistoryObserver> observers;

    private ChatHistory(){

        this.history = new ArrayList<>();
        this.observers = new HashSet<>();
    }

    public static ChatHistory getInstance(){

        return ChatHistoryHolder.INSTANCE;
    }

    public void register(HistoryObserver ho) {
        observers.add(ho);
    }

    @Override
    public void deregister(HistoryObserver ho) {
        observers.remove(ho);
    }

    @Override
    public void notifyObservers(String msg) {

        for (HistoryObserver ho : this.observers){

            ho.update(msg);
        }
    }

    public static class ChatHistoryHolder{
        private static final ChatHistory INSTANCE = new ChatHistory();
    }

    public void insert(String message){

        history.add(message);
        notifyObservers(message);
    }

    public ArrayList<String> getHistory(){

        return this.history;
    }

    public String toString(){

        String message="";

        for(String m : history){

            message += m.toString() + "\n";
        }

        return message;
    }


}


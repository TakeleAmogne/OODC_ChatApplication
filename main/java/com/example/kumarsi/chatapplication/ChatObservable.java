package com.example.kumarsi.chatapplication;

/**
 * Created by kumarsi on 07-Oct-16.
 */

public interface ChatObservable {

    public void register(HistoryObserver ho);
    public void deregister(HistoryObserver ho);
    void notifyObservers(String s);
}


// Roope Vilo
// Mokhtar Zaher
// Kumar Simkhada

package chatapplication;

import java.util.ArrayList;

/**
 *
 * @author rrvil
 */
public class ChatHistory {
        
    private ArrayList<ChatMessage> history;
    private ChatHistory history2 = null;
    
    public ChatHistory getInstance() {
        if(history2==null) {
            synchronized(ChatHistory.class) {
                if(history2 == null) {
                    history2 = new ChatHistory();
                }
            }  
        }
        return history2;
    }
    
    public ChatHistory(){
        this.history = new ArrayList<>();
    }
    
    public void insert(ChatMessage message){
        
        history.add(message);
    }
    
    public ArrayList<ChatMessage> getHistory() {
        return this.history;
    }
    @Override
    public String toString(){
        String word = "";
        
       for(ChatMessage m : history){
           word += m.toString() + "\n";
       }
        
       
       return word;
    }
}

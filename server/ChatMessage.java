
// Roope Vilo
// Mokhtar Zaher
// Kumar Simkhada

package chatapplication;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author rrvil
 */
public class ChatMessage {
    
    private String message;
    private String username;
    private String timestamp;
       
    public ChatMessage(String username, String timestamp, String message){
        
        this.message = message;
        this.username = username;
        this.timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm").format(new Date());
    }
    
    public String toString(){
                       
        return username + "@" + timestamp + ": " + message;
    }
}



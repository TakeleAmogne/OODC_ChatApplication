
// Roope Vilo
// Mokhtar Zaher
// Kumar Simkhada

package chatapplication;

import java.io.InputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author rrvil
 */

public class CommandInterpreter implements Runnable {
    
    private InputStream in;
    private PrintStream out;

    public CommandInterpreter(InputStream inputStream, PrintStream printStream){
        this.in = inputStream;
        this.out = printStream;
    }
    
    public void run(){
    
        Scanner reader = new Scanner(in);
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm").format(new Date());
        ArrayList<String> userlist = new ArrayList<String>();
        ChatHistory history = new ChatHistory();
        String username = setUsername(userlist);
        
        out.println("Welcome to the chat, " + username);
        out.println("");
                        
        while(true){
            
            out.print("Type: ");
            String input = reader.nextLine();
            
            if(input.equals(":quit")){
                break;
            }
            
            if(!input.startsWith(":") && !input.isEmpty()){
                ChatMessage message = new ChatMessage(username, timestamp, input); 
                out.println(message.toString());
                history.insert(message);
                out.println("");
            }
            
            if(input.equals(":list")){
                out.println("Users online:\n");
                for(String user : userlist){
                    out.println(user);
                }
                out.println("");
            }
            
            if(input.equals(":history")){
                
                for(ChatMessage m : history.getHistory()) {
                    out.println(m.toString());
                }
            }
        }
    }
    
    public String setUsername(ArrayList <String> users){
        
        Scanner reader = new Scanner(in);
        String username = "";
       
        while(true){
            out.println("Set username: ");
            String setName = reader.nextLine();
            
            if(users.contains(setName) || setName.startsWith(":")){
                out.println("Username invalid or already taken.");
            }
            
            else{
                out.println("Your username is: " + setName);
                out.println("");
                username = setName;
                users.add(setName);
                break;
            }
        }
        
        return username;        
    }      
}

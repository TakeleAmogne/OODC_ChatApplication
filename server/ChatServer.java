
// Roope Vilo
// Mokhtar Zaher
// Kumar Simkhada

package chatapplication;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rrvil
 */
public class ChatServer {
        
    public void serve(){
        
        try {
            ServerSocket serverSocket = new ServerSocket(0, 3);
            System.out.println("Socket: " + serverSocket.getLocalPort());
            
            while(true){
                Socket socket = serverSocket.accept();
                CommandInterpreter ci = new CommandInterpreter(socket.getInputStream(),new PrintStream(socket.getOutputStream(), true));
                Thread chatThread = new Thread(ci);
                chatThread.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Thread t = new Thread();
        t.start();
    }    
}

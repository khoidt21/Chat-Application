package com.business;

import com.dal.MessageDAO;
import com.entity.Client;
import com.entity.MessageDetail;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

public class ClientHandler implements Runnable {

    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Socket socket;
    private Client client;
    private JTextArea txtContent;
    private MessageDAO md;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public JTextArea getTxtContent() {
        return txtContent;
    }

    public void setTxtContent(JTextArea txtContent) {
        this.txtContent = txtContent;
    }

    public ClientHandler(Socket socket) {
        this.socket = socket;

    }

    public ClientHandler(Socket socket, Client client) {
        this.socket = socket;
        this.client = client;

    }
    
    public ClientHandler(Socket socket, Client client, ObjectInputStream ois) {
        this.socket = socket;
        this.client = client;
        this.ois = ois;

    }

    public ClientHandler(Socket socket, Client client, JTextArea txtContent) {
        this.socket = socket;
        this.client = client;
        this.txtContent = txtContent;
    }

    @Override
    public void run() {
        //whenever a new connection is arrived, ClientHandler will be created and 
        //handle the communication between client and server
        try{
            md = new MessageDAO();
            oos = new ObjectOutputStream(socket.getOutputStream());
            // receive data from client 
            while(true){
                Object line = ois.readObject();
                if(line instanceof MessageDetail){
                    MessageDetail m = (MessageDetail) line;
                    txtContent.append("\n" + m.getFromUser() + ":" + m.getContent());
                    md.addMessageDetail(m);
                }
            }
        }
        catch(Exception ex){
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    //send message to client
    public void send(Object line) throws Exception {
        /*sending a message to client*/
        if(line instanceof MessageDetail){
            MessageDetail m = (MessageDetail) line;
            txtContent.append("\nMe:" + m.getContent());
            oos.writeObject(line);
            md.addMessageDetail(m);
        }
    }
    
}

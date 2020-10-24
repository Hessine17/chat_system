package chat_system;


import chat_system.RSA.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hamdi
 */
class Client_impl implements Runnable
{
    Socket sockt;
    Scanner in;
    Scanner envoyer=new Scanner(System.in);
    PrintWriter out;
    
    public Client_impl(Socket X) {
        this.sockt=X;
    }


    
    public void run() 
    {
        try
        {
            try
            {
                in=new Scanner(sockt.getInputStream());
                out=new PrintWriter(sockt.getOutputStream());
                out.flush();
                recevoire();

            }finally
            {
                sockt.close(); 
            }
        }
        catch(Exception X){
            System.err.println(X);
        }
        
    }

    public void deconnecte() throws IOException
    {
        out.print(Chat_Client.nomutili+"s'est déconnecté");
        out.flush();
        sockt.close();
        JOptionPane.showMessageDialog(null, "Vous avez déconnecté");
        System.exit(0);
    }


    public void recevoire ()
    { // c'est pour recevoir les message et le noms des autre clients
     while (true){
        if (in.hasNext())
        {
            String MESSAGE=in.nextLine();
            if(MESSAGE.contains("/"))
            {
                String TEMP1=MESSAGE.substring(3);
                       TEMP1=TEMP1.replace("","");
                       TEMP1=TEMP1.replace("","");
                       String []listeutlilisateur =TEMP1.split(", ");
                    //  Chat_Client.lista.setListData(listeutlilisateur);
                      
                
            }
            else
            {
            
            }
        }
    }
    }
    public void envoyer (String X,RSA rsa) throws Exception
    {   
        byte [] signed = rsa.encrypt(X);  
        out.println(Chat_Client.nomutili +":"+X);
        out.flush();
        Chat_Client.Msg.setText("");
    }
    //pour envoyer  le nom de l'utilisateur
    
    
    
    
}

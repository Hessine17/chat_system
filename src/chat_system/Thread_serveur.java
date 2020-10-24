package chat_system;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hamdi
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class Thread_serveur implements Runnable{
    Socket sockt;
    private Scanner input;
    private PrintWriter out;
    String message="";
    
    public Thread_serveur(Socket X)
    {
        this.sockt=X;
    }
   
    

    @Override
    public void run() 
    {
        try
        {
            try
            {
        
            input=new Scanner (sockt.getInputStream());
       
        
            out=new PrintWriter(sockt.getOutputStream());
            
            while(true){
                        
                           
                                               
                        if (!input.hasNext())
                        {
                        return ;
                        }
                        message=input.nextLine();
                        System.out.println("Client:"+ message);
                      //envoi les msg a tous les utilisateur  
                        for(int i =1; i<= Chat_Server.listesocket.size(); i++){
                            Socket tmpsocket=(Socket) Chat_Server.listesocket.get(i-1);
                            PrintWriter  TEMP_out = new PrintWriter(tmpsocket.getOutputStream());              
                            TEMP_out.println(message);             
                            TEMP_out.flush();
                            System.out.println("envoyer vers  :"+tmpsocket.getLocalAddress().getHostName());
                        }
                    } 

                }
            finally
            {
            sockt.close();
            }
        }
        catch (Exception X){System.out.print(X);}
    }
}

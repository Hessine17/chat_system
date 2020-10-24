package chat_system;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hamdi
 */
public class Chat_Server {

    public static ArrayList<Socket> listesocket =new ArrayList<Socket>();
    public static ArrayList<String> listeutlilisateur=new  ArrayList<String>();

public static void main(String[] args) throws IOException 
{
    try{   
        final int PORT=1600;
        ServerSocket SERVER =new  ServerSocket(PORT);
        System.out.println("att les clients ...");
        while (true) 
        {        
            Socket s =SERVER.accept();
            listesocket.add(s);
            System.out.println("client connecter"+s.getLocalAddress().getHostName());
            ajouter(s);
            Thread_serveur CHAT = new Thread_serveur(s);
            Thread t =new Thread (CHAT);
            t.start();
        }
    }
    catch(Exception X){ System.out.println(X);}
}
//a chaque fois on ajoute un utili en envoi la liste de utili a tous les utilisateur
 public  static void ajouter (Socket X)throws IOException{
     Scanner input =new Scanner(X.getInputStream());
     String nomutlilistatreur=input.nextLine();//pour lire de  nom utli
     System.out.println(nomutlilistatreur);
     listeutlilisateur.add(nomutlilistatreur);//ajouter utli a liste
     for(int i=1 ; i<=Chat_Server.listesocket.size();i++)
        {
         Socket tmpsocket =(Socket) Chat_Server.listesocket.get(i-1);
         PrintWriter out =new PrintWriter(tmpsocket.getOutputStream());
         out.println("/"+listeutlilisateur);//envoi liste utili a tous les utlis
         out.flush();
        }
    }
}

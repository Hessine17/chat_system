/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_system;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author hamdi
 */
public class Chat_Client extends javax.swing.JFrame {
        private static Client_impl ChatClient; 
        public static String nomutili="";
        public static RSA rsa_instance ;
   
    public Chat_Client() {
        initComponents();
        
    }

    public static void Connecter() 
    {
        try{
            final int PORT = 1600; 
            final String HOST ="127.0.0.1";
            Socket sockt= new Socket (HOST,PORT);
            System.out.println("tu est conncter sur : " + HOST); 
            ChatClient = new Client_impl(sockt); 
            Thread X= new Thread (ChatClient); //pour contruire un nuveau thread client
            rsa_instance = new RSA(nomutili);
            // envoyer nom pour ajouter a la liste 
            PrintWriter out= new PrintWriter(sockt.getOutputStream());
            out.println(nomutili+"est Connecter");
            out.flush();
            
            
            X.start();
        }
        catch(Exception X)
        {
            System.out.print(X);
            JOptionPane.showMessageDialog(null, "Le serveur ne répond pas");
            System.exit(0);
        }
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Envoye = new javax.swing.JButton();
        ident = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Discu = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        Msg = new javax.swing.JTextField();
        connecter = new javax.swing.JButton();
        déconnecter = new javax.swing.JButton();
        nom_Client = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Envoye.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Envoye.setForeground(new java.awt.Color(0, 102, 204));
        Envoye.setText("Envoyer");
        Envoye.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnvoyeActionPerformed(evt);
            }
        });
        getContentPane().add(Envoye, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 380, 110, 40));
        getContentPane().add(ident, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 80, 190, 40));

        Discu.setColumns(20);
        Discu.setRows(5);
        jScrollPane1.setViewportView(Discu);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 460, 241));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 153));
        jLabel1.setText("Discussion");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 430, 49));

        Msg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MsgActionPerformed(evt);
            }
        });
        getContentPane().add(Msg, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 340, 40));

        connecter.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        connecter.setForeground(new java.awt.Color(0, 102, 204));
        connecter.setText("Se Connecter ");
        connecter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connecterActionPerformed(evt);
            }
        });
        getContentPane().add(connecter, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 140, 190, 50));

        déconnecter.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        déconnecter.setForeground(new java.awt.Color(0, 102, 153));
        déconnecter.setText("Déconnecter");
        déconnecter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                déconnecterActionPerformed(evt);
            }
        });
        getContentPane().add(déconnecter, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 200, 190, 50));

        nom_Client.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        nom_Client.setForeground(new java.awt.Color(204, 51, 0));
        getContentPane().add(nom_Client, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 150, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EnvoyeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnvoyeActionPerformed
        if(! Msg.getText().equals("")){
        
            try {
                ChatClient.envoyer(Msg.getText(),this.rsa_instance);
            } catch (Exception ex) {
                Logger.getLogger(Chat_Client.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }      else {
            JOptionPane.showMessageDialog(null, "svp entre votre nom !!!");
        }
    }//GEN-LAST:event_EnvoyeActionPerformed

    private void connecterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connecterActionPerformed
       if(!ident.getText().equals(""))
        {
            nomutili=ident.getText();
            nom_Client.setText(nomutili);
            Chat_Server.listeutlilisateur.add(nomutili);
            this.setTitle(nomutili + "Chat sys");
            this.setVisible(true);
            Connecter(); 
         //  this.disable();
        }
        else{
            JOptionPane.showMessageDialog(null, "svp entre votre nom !!!");
        }
            
    }//GEN-LAST:event_connecterActionPerformed

    private void déconnecterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_déconnecterActionPerformed
            try {
                ChatClient.deconnecte();
            } catch (IOException ex) {
                Logger.getLogger(Chat_Client.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_déconnecterActionPerformed

    private void MsgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MsgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MsgActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Chat_Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Chat_Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Chat_Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Chat_Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Chat_Client().setVisible(true);
            }
        });
    }
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextArea Discu;
    private javax.swing.JButton Envoye;
    public static javax.swing.JTextField Msg;
    private javax.swing.JButton connecter;
    private javax.swing.JButton déconnecter;
    public static javax.swing.JTextField ident;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nom_Client;
    // End of variables declaration//GEN-END:variables
}

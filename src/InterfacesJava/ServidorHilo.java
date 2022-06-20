/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesJava;
import java.net.*;
import java.io.*; 
import java.util.*;
import java.util.logging.*;
/**
 *
 * @author stark
 */
class ServidorHilo extends Thread {
    private Socket socket;
    private BufferedInputStream bis;
    private BufferedOutputStream bos;
    private int idSessio;
    private byte[] receivedData;
    private int in;
    private String file;
    public ServidorHilo(Socket socket, int id) {
        this.socket = socket;
        this.idSessio = id;
        try {
            bis = new BufferedInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void desconnectar() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void run() {
        try{
            while(true){
                DataInputStream dis=new DataInputStream(socket.getInputStream());
                //Recibimos el nombre del fichero
                file = dis.readUTF();
                file = file.substring(file.indexOf('\\')+1,file.length());
                //Para guardar fichero recibido
                receivedData = new byte[1024];
                bos = new BufferedOutputStream(new FileOutputStream(file));
                while ((in = bis.read(receivedData)) != -1){
                    bos.write(receivedData,0,in);
                }
                bos.close();
                dis.close();
                System.out.println("Archivo Recibido ' " + file +"'" );
                System.out.println("Archivo Recibido ' " + file +"'" );
            }
        }catch(IOException e){
            System.out.println("Esta es la exception "+e.getMessage());
        }   
    }
}
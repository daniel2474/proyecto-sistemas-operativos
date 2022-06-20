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
class Cliente extends Thread{
    private DataInputStream input;
    private BufferedInputStream bis;
    private BufferedOutputStream bos;
    private ArrayList<String> ips;
    private Socket client;
    public Cliente(){
        ips=new ArrayList<String>();
    }
    public  void enviarBroadcast(){
        try{
            DatagramSocket socket=null;
            InetAddress address=InetAddress.getByName("192.168.1.255");
            //InetAddress address=InetAddress.getByName("172.31.15.255");
            //InetAddress address=InetAddress.getByName("172.18.107.255");
                socket=new DatagramSocket();
            socket.setBroadcast(true);
            InetAddress direccion=InetAddress.getLocalHost();
            String broadcastMessage=direccion.getHostAddress();
            byte[] buffer= broadcastMessage.getBytes();
            DatagramPacket packet=new DatagramPacket(buffer,buffer.length,address,4445);
            socket.send(packet);
            socket.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void recibirIPServidor(){
        try{
            // Puerto por el que queremos escuchar 4445
            DatagramSocket escucha = new DatagramSocket(4445);

            // Un array de bytes lo suficientemente grande para contener
            // cualquier dato que podamos recibir.
            byte [] dato = new byte [1024];
            DatagramPacket dgp = new DatagramPacket(dato, dato.length);

            // La llamada se queda bloqueada hasta que recibamos algÃºn
            // mensaje por ese puerto 4445escucha.receive(dgp);          
            escucha.receive(dgp);
            // En datos obtenemos lo que acabamos de recibir.  
            byte[] datos = dgp.getData();
            int n=(int)datos[0];
            System.out.println(n);// Muestra un entero
            
            for(int i=0;i<n;i++){
                escucha.receive(dgp);
                // En datos obtenemos lo que acabamos de recibir.
                byte[] datos2 = dgp.getData();
                String s;
                s=new String(datos2);
                s=validarIP(s.trim());
                ips.add(s.trim());
               
            }
             System.out.println(ips);// Esta afuera por que si no se repitem muchas veces            
        }catch(IOException e){
            System.out.println("ya valio"+e.getMessage());
        }
    }
    
     public Socket getSocket(){
        return client;
    }
    public String validarIP(String s)throws IOException{
        String[] ip = s.split("\\.");
        StringBuilder aux=new StringBuilder();
        // En este momento tenemos un array en el que cada elemento es un color.
        for (int i = 0; i < ip.length-1; i++) {
            aux.append(ip[i]);
            aux.append('.');
        }
        for(int i=0;i<3;i++){
            aux.append(ip[3].charAt(i));
            if(InetAddress.getByName(aux.toString()).isReachable(1000)){
                return aux.toString();
            }
        }
        return null;
    }
    public void enviarArchivo(String filename){ 
        try{
            int in;
            byte[] byteArray;
            final File localFile = new File( filename );
            bis = new BufferedInputStream(new FileInputStream(localFile));
            bos = new BufferedOutputStream(client.getOutputStream());
            //Enviamos el nombre del fichero
            DataOutputStream dos=new DataOutputStream(client.getOutputStream());
            dos.writeUTF(localFile.getName());
            //Enviamos el fichero
            byteArray = new byte[8192];
            while ((in = bis.read(byteArray)) != -1){
                bos.write(byteArray,0,in);
            }
            System.out.println("EL DOCUMENTO SE ENVIO EXITOSAMENTE...");
            bis.close();
            bos.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        } 
    }
    public void run(){
        
        try{int i=0;
            client=new Socket(ips.get(i),1234);
            while(true){
                InetAddress ping;
                String ip = ips.get(i); // Ip de la mÃ¡quina remota
                try{
                    while(true){
                        Thread.sleep(3000);
                        ping = InetAddress.getByName(ip);
                        if(ping.isReachable(5000)){
                            System.out.printf("-");
                        }else {
                            throw new IPException();
                        }
                    }
                }catch(IPException e){
                    System.out.println("no se encontro el servidor");
                }catch (IOException ex) {
                    System.out.println(ex);
                }catch(InterruptedException e){
                    System.out.println(e);
                }
                i++;
                ping=InetAddress.getLocalHost();
                String nuevo=ping.getHostAddress();
                System.out.println(ips);
                //System.out.println(ips.get(i).equals(nuevo));
                if(ips.get(i).equals(nuevo)){
                    while(true){
                        Servidor nuevoServidor=new Servidor();
                        nuevoServidor.initServ();
                        nuevoServidor.start();
                        nuevoServidor.accept();
                        nuevoServidor.leerIPCliente();
                        nuevoServidor.conectarCliente();
                    }
                }
                else{
                    System.out.println("Conectando al nuevo servidor con ip "+ips.get(i)+"...");
                    Thread.sleep(5000);
                    Socket client1 = new Socket(ips.get(i), 1234);
                    enviarIP();
                }            
            }
        }catch(UnknownHostException e){
            System.out.println(e);
        }catch(IOException e){
            System.out.println(e);
        }catch(InterruptedException e){
            System.out.println(e);
        }
    }
    
    public void enviarIP(){
        try{
            PrintStream p = new PrintStream(client.getOutputStream());
    
            InetAddress ip=InetAddress.getLocalHost();
            String nuevo=ip.getHostAddress();
            //Escribo en el canal de escritura del socket
            p.println( nuevo );
        }catch(IOException e){
            System.out.println(e);
        }
        //Espero la respuesta por el canal de lectura
    }
}
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
class Servidor extends Thread{
    private ServerSocket server;
    private Socket connection;
    private Thread t;
    private ArrayList<String> ips;
    private  String broadcastMessage;
    public void initServ(){
        try {
            InetAddress addr = InetAddress.getLocalHost();
            broadcastMessage=addr.getHostAddress();
            ips=new ArrayList<String>();
            ips.add(broadcastMessage);
            // IP
            byte[] ipAddr = addr.getAddress();
        } catch (UnknownHostException uhe) {
        uhe.printStackTrace();
        } 
        try{
            //Servidor Socket en el puerto 5000
            server = new ServerSocket( 1234 );
            System.out.println("Servidor Listo");
        }catch (Exception e ) {
            System.err.println(e);
        }
    }
    public Socket getSocket(){
        return connection;
    }
    public void conectarCliente() throws IOException{
        int id=0;
        while ( true ) {
            //Aceptar conexiones
            connection = server.accept();
            //Buffer de 1024 bytes
            System.out.println("El cliente "+id+" se ha Conectado");
            ((ServidorHilo) new ServidorHilo(connection, id)).start();
            id++;
        }   
    }
    public void run() {
        // Puerto por el que queremos escuchar 4445
        while(true){
            try{
                DatagramSocket escucha = new DatagramSocket(4445);

                // Un array de bytes lo suficientemente grande para contener
                // cualquier dato que podamos recibir.
                byte [] dato = new byte [1024];

                DatagramPacket dgp = new DatagramPacket(dato, dato.length);

                // La llamada se queda bloqueada hasta que recibamos algÃºn
                // mensaje por ese puerto 55557
                escucha.receive(dgp);

                // En datos obtenemos lo que acabamos de recibir.
                byte[] datos = dgp.getData();
                String s2,s=new String(datos);
                s2=s.replaceAll("\\s","");
                ips.add(s2.trim());

                System.out.println(ips);
                DatagramSocket socket=null;

                InetAddress address=InetAddress.getByName("192.168.1.255");  
                //InetAddress address=InetAddress.getByName("172.31.15.255");       
                //InetAddress address=InetAddress.getByName("172.18.107.255");
                socket=new DatagramSocket();
                socket.setBroadcast(true);
                int n=ips.size();
                System.out.println(n);
                byte[] buffer=new byte[1];
                buffer[0]=(byte)n;
                DatagramPacket packet=new DatagramPacket(buffer,buffer.length,address,4445);
                socket.send(packet);
                String[] ipsstring=ips.toArray(new String[ips.size()]);
                for(int i=0;i<ips.size();i++){
                    byte[] buffer2= ipsstring[i].getBytes();
                    DatagramPacket packet2=new DatagramPacket(buffer2,buffer2.length,address,4445);
                    socket.send(packet2);
                        
                }
                socket.close(); 
            }catch(IOException e){

            }   
        }
    }

    public void leerIPCliente(){
        try{
            BufferedReader b = new BufferedReader( new InputStreamReader (connection.getInputStream() ) );
            //PrintStream p = new PrintStream ( socket.getOutputStream() );
            String variable;        
            //Escribo en canal de escritura el mismo mensaje recibido
            variable=b.readLine();
            ips.add(variable);
        }catch(UnknownHostException e){
            System.out.println(e);
        }catch(IOException e){
            System.out.println(e);
        }
    }
    public void accept(){
        try{
            connection=server.accept();
            ((ServidorHilo) new ServidorHilo(connection, 0)).start();
        }catch(IOException e){
            System.out.println(e);
        }
    }

}
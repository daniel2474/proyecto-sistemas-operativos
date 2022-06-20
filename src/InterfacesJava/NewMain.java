/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesJava;

import Ventanas.Interfas;
import java.io.IOException;

/**
 *
 * @author stark
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Servidor server=new Servidor();
 	server.initServ();
 	server.start();
        Interfas a=new Interfas(server.getSocket());
        a.setVisible(true);
	server.conectarCliente();
    }
    
}

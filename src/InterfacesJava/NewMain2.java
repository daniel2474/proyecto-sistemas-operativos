/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesJava;

import Ventanas.Interfas;

/**
 *
 * @author stark
 */
public class NewMain2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Cliente cl=new Cliente();
        cl.enviarBroadcast();
        cl.recibirIPServidor();
        cl.start();
        Interfas interfasInicio = new Interfas(cl.getSocket());
        interfasInicio.setVisible(true);
    }
    
}

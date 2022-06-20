package Clases;

import java.io.*;
import java.lang.*;
import javax.swing.JOptionPane;
public class ClassAdministrador {

    private Archivo ruta;
    private Editor metodos;
  
    public ClassAdministrador(){
       
    }

    public boolean validarAdministrador(String usuario, char[] contrasenia) throws Exception{
         ruta = new Archivo();
        metodos = new Editor();
        
        String nuevaContrasenia = String.valueOf(contrasenia);
        String contenido="C:\\Users\\stark\\OneDrive\\Documentos\\xxxxxxx\\universidad\\semestre 7\\Sistemas Operativos II\\proyecto final\\InterfacesJava\\Administradores\\Administradores.txt";
        String datos="";
       
        datos= metodos.abrirArchivo(contenido);// metodo de Editor
         //System.out.println(datos);
         
         String string = datos;
         String[] parts = string.split("\n");// Aqui separamos los datos en un arreglo
         
        //System.out.println("Estas son los arreglos");
       for(int i=0; i< string.length();i++){
            if( usuario.equals(parts[i]) && (nuevaContrasenia.equals(parts[i+1]))){
               //tem.out.println(String.valueOf(miarray));
                return true;
                
            }
            System.out.println(parts[i]);
        }
        
        return false;
    }
    
    

}
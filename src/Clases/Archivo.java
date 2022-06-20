/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Archivo {
    // Objeto de la clase File que representa de forma abstracta a un archvio fisico en disco
    private File archivo;
    /*
    Contruye un objeto de la clase archivo
    Parametro: la ruta completa del archivo que se va a crear
    
    */
    public Archivo (){
        
    }
    public Archivo(String nombreArchivo){
        archivo = new File(nombreArchivo);// Creamos el archivo
    }
 /*
    Retorna el contenido del archivo 
    Return : String que contiene el contenido 
    ThrosIOEception
    */
    public String darContenido() throws IOException{
        
        String contenido= "";
        
        FileReader fr = new FileReader(archivo);
        
        BufferedReader lector = new BufferedReader(fr);
        
        String linea = lector.readLine();// Lee linea por linea hasta que llega a nulo
       
        while(linea != null){
            //System.out.println("1235");
            contenido += linea + "\n";
            linea = lector.readLine();
        }
        lector.close();
        fr.close();
        return contenido;
    }
    /*
    Guarda el contenido en un archivo nuevo o existente
    parametro
    */
    public void guardar( String contenido )throws IOException {
        PrintWriter escritor = new PrintWriter(archivo); // CReamos un objeto para que usemos metodos para escribir en un archivo
        escritor.write(contenido); // Funcion write escribe el contenido
        escritor.close();
    }

  
}

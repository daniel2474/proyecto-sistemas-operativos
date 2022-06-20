package Clases;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Editor {
    // Representa el archivo que esta cargado en el editor
    private Archivo archivo;
    
    // Construye un objeto de la clase editor
    public Editor(){
        archivo = null;
    }
    /*
    Abrir un archivo de texto existente
    
    post: el atributo archivo ha sido inicializado.
    
    nombreArchivo String que contienen la ruta completa del archivo
    retorna un string que contiene el contenido del archivo
    Throwa Exception cuando hay problemas abriendo el archvo
    */
    
    public String abrirArchivo(String nombreArchivo)throws Exception{
      
        String contenido="";
         // System.out.println("Canelo");
        archivo = new Archivo(nombreArchivo);// Creamos el archivo que voy a abrir
        
        try {
            
            contenido = archivo.darContenido();// 
           // System.out.println("caocn");
        } catch (IOException e) {
            throw new Exception("Error leyendo el archivo",e);
              }
        return contenido;
    }
    
    
    
    /*
    Crea un nuevo archivo de texto en el editor
    post: el archivo es igual e null
    */
    public void crearArchivo(){
        archivo = null;
    }
    
    /*
    Guarda el contenido de un archivo, sea nuevo o existente
    
    parametro: contenido String que tiene el contenido que tiene el contenido que se va a guardar
    parametro: rutaArchivo  String qur contiene la ruta del archivo 
    Throws Exception cuando ocurre un error escribiendo el archivo
    */
    public void guardarArchivo(String contenido, String rutaArchivo)throws Exception{
        if(archivo == null){
            archivo = new Archivo(rutaArchivo);
        }
        try {
            archivo.guardar(contenido);
        } catch (IOException e) {
            throw new Exception ("Error guardando el archivo",e);
             }
    }
    /*
    Indica si el archivo del editor es nuevo o no
    y retorna verdadero si elni archivo es nuvo y false en caso contrario
    */
    public boolean esArchivoNuevo(){
        return archivo == null;
    }
}

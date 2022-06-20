
package Clases;


public class ClassUsuario {
    private Archivo ruta;
    private Editor metodos;
    
    String [] DatosUsuarios;
    String Usuario;
            
    public ClassUsuario(){
    }
  
    public boolean validarUsuario(String usuario, char[] contrasenia) throws Exception{
        ruta = new Archivo();
        metodos = new Editor();
        
        String nuevaContrasenia = String.valueOf(contrasenia);
        String contenido="C:\\Users\\josea\\OneDrive\\Documentos\\NetBeansProjects\\InterfacesJava\\src\\Clases\\Pacientes\\Pacientes.txt";
        String datos="";
       
        datos= metodos.abrirArchivo(contenido);// metodo de Editor
        //System.out.println(datos);
         
         String string = datos;
         String[] parts = string.split("\n");// Aqui separamos los datos en un arreglo
         
         for(int k = 0; k<parts.length;k++){
             this.DatosUsuarios [k]= parts[k];
         }
        
       // System.out.println("Estas son los arreglos");
       /*
       Valida si es que e
       */
        for(int i=0; i< string.length();i++){
             if( usuario.equals(parts[i]) && (nuevaContrasenia.equals(parts[i+1]))){
                this.Usuario= usuario;
                return true;
                
            }
           System.out.println(parts[i]);
        }
        
        return false;
    }
    /*
    Este metodo devuelve los datos de la perosna que entro en la secion
    */
    public String[] posicionesArreglo(){
        String[] datosUsuario = new String[5];
        for(int i=0; i<this.DatosUsuarios.length;i++){
            if(this.Usuario == this.DatosUsuarios[i]){
                datosUsuario[0]=this.DatosUsuarios[i-3];//Nombre
                datosUsuario[1] = this.DatosUsuarios[i-2];// Edad
                 datosUsuario[2] = this.DatosUsuarios[i-1];// Genero
                  datosUsuario[3] = this.DatosUsuarios[i];// NombredePaciente
                   datosUsuario[4] = this.DatosUsuarios[i+1];// Contrasenia
                   System.out.println(datosUsuario[4]);
                    
            }
        }
      return datosUsuario;
    }
    
    
}

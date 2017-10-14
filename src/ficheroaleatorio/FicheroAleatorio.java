/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ficheroaleatorio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author rojo5
 */
public class FicheroAleatorio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        java.io.File ff = new java.io.File("ficheroAleatorio.txt"); 
           java.io.RandomAccessFile aa = new java.io.RandomAccessFile(ff,"rw"); 
           
            char nombre[]= new char[25]; 
             nombre[0]='j'; 
             nombre[1]='e';
             nombre[2]='s';
             nombre[3]='u';
             nombre[4]='s';
            
             
             Registro reg = new Registro(nombre,5.0f,4.0f,4.0f,9.25f); 
              reg.grabarRegistro(aa, reg); 
              reg.leerDatos(aa); 
    }
    
    public static class Registro{ 
        protected char nombre[]; 
        protected float parcial1, parcial2, talleres, proyecto; 
        
        public Registro(){}
        public Registro(char[] nombre, float nota1, float nota2, 
                        float         nota3, float nota4){
        
            this.nombre = new char[25];  
            this.nombre = nombre; 
            parcial1 = nota1;
            parcial2 = nota2;
            talleres = nota3;
            proyecto = nota4; 
        }
        
        public static void grabarRegistro(RandomAccessFile aa, Registro estudiante) throws IOException { 
            
            int longitud = (int)aa.length();
            aa.seek(longitud); 
            
            for (int i=0; i < (estudiante.nombre).length ; i++)
                aa.writeChar(estudiante.nombre[i]); 
            aa.writeFloat(estudiante.parcial1); 
            aa.writeFloat(estudiante.parcial2); 
            aa.writeFloat(estudiante.talleres); 
            aa.writeFloat(estudiante.proyecto); 
            
            
        }
        
        public static void leerDatos(RandomAccessFile aa)throws IOException{
            int pos = 0; 
            int longitud = (int)aa.length();
            int lreg = 66;
            int regs = longitud / lreg;
            System.out.println("Número total de registros en el fichero: " +  regs); 
            System.out.println("Cada registro ocupa: " + lreg); 
            if (regs > 0){ 
                Registro vector[] = leerRegistro(aa, regs, lreg); 
                for(int i=0; i<regs;i++){ 
                     String nom = new String(vector[i].nombre); 
                      System.out.println(nom+" "+vector[i].parcial1+"        "
                      +vector[i].parcial2+" "+vector[i].talleres+"        "
                      +vector[i].proyecto); 
                }
            }
            else  
                System.out.println("Archivo vacio !!!"); 
            
        }
        
        public static Registro[] leerRegistro(RandomAccessFile aa, int regs, int lreg)throws IOException{  
        
            Registro vector[] = new Registro[regs]; 
            aa.seek(0); 
            for (int i=0; i < regs; i++){ 
                char nombre[] = new char[25]; 
                
                for (int k=0; k < nombre.length; k++) 
                    nombre[k] = aa.readChar(); 
                
                float nota1= aa.readFloat(); 
                float nota2= aa.readFloat(); 
                float nota3= aa.readFloat(); 
                float nota4= aa.readFloat(); 
                
                Registro datos = new Registro(nombre, nota1, nota2, nota3,   nota4);
                
                vector[i] = datos;
            }
            return vector; 
        }
 
        
        
       
    
    }
    
}

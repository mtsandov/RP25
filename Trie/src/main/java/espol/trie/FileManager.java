/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.trie;

import java.nio.file.Path;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.*;
/**
 *
 * @author wayar
 */
public class FileManager {
    
 
    public String file_Selection(){
        int respuesta =0;
        do{
        String path = "";
        JFileChooser selectFile = new JFileChooser(); //PARA ABRIR EL EXLORADOR Y BUSCAR NUESTRA IMAGEN
        FileNameExtensionFilter formato = new FileNameExtensionFilter("TXT", "txt");
        selectFile.setFileFilter(formato); // AGREGAMOS LOS FORMATOS PERMITIDOS
        respuesta = selectFile.showOpenDialog(null);
         if(respuesta == selectFile.APPROVE_OPTION ){  // APPROVE_OPTION = BOTON ABRIR
                                           //GRUPO DE ELEMENTOS FUE PRESIONADO
            path = selectFile.getSelectedFile().getPath();    // OBTIENE LA RUTA DEL ARCHIVO A AGREGAR        
            return path;
        }
        }while (respuesta == JFileChooser.CANCEL_OPTION);                             
         return "null";
    }

    
}

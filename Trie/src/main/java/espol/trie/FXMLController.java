package espol.trie;
/*
Put header here


 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import tda.Tree;
import tda.TreeNode;

public class FXMLController implements Initializable {
    
    @FXML
    private TextField txtField;
    @FXML
    private Button searchButton;
    
    private AutoCompletionBinding<String> autoCompletionBinding;
    
    Tree<Character> trie = new Tree(new TreeNode("Root Node"));
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTrieTree();
    
    }

    public void loadTrieTree(){
        try (BufferedReader bf = new BufferedReader(new FileReader("src\\main\\resources\\words.txt"))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                trie.insert(linea);
            }
        } catch (IOException ex) {
            System.out.println("no se pudieron cargar las palabras");
            
            ex.printStackTrace();
        }
    }
    
    
    @FXML
    private void buscarPalabra(MouseEvent event) {
        String word = txtField.getText();
         System.out.println(word);
         try{
         if(word.isEmpty() || word == null){
             throw new NullPointerException();
         }
        } catch (NullPointerException n) {
            AlertBoxes.errorAlert("Error", "No puede dejar ningún campo de texto vacío", "Inténtelo nuevamente");
        }
         
         boolean verificador = trie.search(word);
         
         if(verificador){
             AlertBoxes.infoAlert("Exito!", "Se ha encontrado la palabra :)", "Prueba con otra palabra");
         }else {
         AlertBoxes.errorAlert("Fallo", "No se ha encontrado la palabra", "Inténte con otra palabra");
    }
}
    
    @FXML
    private void insertarPalabra(MouseEvent event){
        String word = txtField.getText();
        try(BufferedWriter bf= new BufferedWriter(new FileWriter("src\\main\\resources\\words.txt",true))){
            if(word.isEmpty() || word == null){
             throw new NullPointerException();
         }
             boolean verificador = trie.search(word);
             if(verificador){
             throw new RuntimeException();
             }else{
             String linea= word;
             bf.newLine();
             bf.write(linea);
             trie.insert(word);
             AlertBoxes.infoAlert("Exito!", "Se ha insertado la palabra :)", "Prueba con otra palabra");
           }
        }catch (IOException e2) {
            AlertBoxes.errorAlert("Fallo", "No se ha podido insertar la palabra", "Inténte con otra palabra");
        }catch (NullPointerException n) {
            AlertBoxes.errorAlert("Error", "No puede dejar ningún campo de texto vacío", "Inténtelo nuevamente");
        }catch (RuntimeException n) {
            AlertBoxes.errorAlert("Fallo", "La palabra ya existe", "Inténtelo nuevamente");
        }
        
    }
    
    

}
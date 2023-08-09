package espol.trie;
/*
Put header here


 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import static java.lang.System.exit;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import java.util.Random;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import tda.Tree;
import tda.TreeNode;

public class FXMLController implements Initializable {
    
    @FXML
    private TextField txtField;
    @FXML
    private Button eliminar;
    @FXML
    private Button estadisticas;
    @FXML
    private Button buscar;
    @FXML
    private Button insertar;
    @FXML
    private Button game;
    @FXML
    private Button check;
    @FXML
    private HBox hbox;
    @FXML
    private Label score;
    @FXML
    private Label puntosLabel;
    
    private static final LinkedList<String> words = new LinkedList<>();
    
    private static final Random RANDOM = new Random();
    private  String letras = "";
    private AutoCompletionBinding<String> autoCompletionBinding;
    
    Tree<Character> trie = new Tree(new TreeNode("Root Node"));
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {      
        loadTrieTree();
        autoCompletarPalabra();
        disableButtons();
        try {
            cargarBotones();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void loadTrieTree(){
        try (BufferedReader bf = new BufferedReader(new FileReader("src\\main\\resources\\words.txt"))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                boolean verificador = trie.search(linea);
                if(!verificador){
                trie.insert(linea);
                words.add(linea);
                }
            }
        } catch (IOException ex) {
            System.out.println("no se pudieron cargar las palabras");
            
            ex.printStackTrace();
        }
    }
    
    
    @FXML
    private void buscarPalabra(MouseEvent event) {
        String word = txtField.getText().toLowerCase();
         System.out.println(word);
         try{
         if(word.isEmpty() || word == null){
             throw new NullPointerException();
         }
        } catch (NullPointerException n) {
            AlertBoxes.errorAlert("Error", "No puede dejar ningún campo de texto vacío", "Inténtelo nuevamente");
        }
         
         boolean verificador = trie.search(word);
         disableButtons();
         hbox.getChildren().clear();
         if(verificador){
             AlertBoxes.infoAlert("Exito!", "Se ha encontrado la palabra :)", "Prueba con otra palabra");
         }else {
         AlertBoxes.errorAlert("Fallo", "No se ha encontrado la palabra", "Inténte con otra palabra");
    }
}
    
    @FXML
    private void insertarPalabra(MouseEvent event){
        String word = txtField.getText().toLowerCase();
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
             words.add(linea);
             loadTrieTree();
             disableButtons();
             hbox.getChildren().clear();
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
    
    @FXML
    private void autoCompletarPalabra(){   
        loadTrieTree();
        String prefix = txtField.getText().toLowerCase(); // obtengo el prefijo del campo de texto
        List<String> wordsCompleted = trie.autoComplete(prefix);
        TextFields.bindAutoCompletion(txtField, wordsCompleted);
    }
    
    @FXML
    private void estadisticas() throws IOException{   
        MainApp.setRoot("Estadisticas","");
    }
    
    @FXML
    private void gameMode(MouseEvent event){
        check.setVisible(true);
        score.setVisible(true);
        puntosLabel.setVisible(true);
        score.setText(String.valueOf(0));
        hbox.getChildren().clear();
            int randomIndex = RANDOM.nextInt(words.size());
            String selectedWord = words.get(randomIndex);
            String shuffledWord = shuffleWord(selectedWord);
            
        for (int i = 0; i < shuffledWord.length(); i++) {
            Label label = new Label();
            label.setStyle("-fx-padding: 10px;" +
                "-fx-font: bold 18px 'System'; -fx-text-fill: white;");
            label.setText(String.valueOf(shuffledWord.charAt(i)));
            hbox.getChildren().add(label);
        }  
        LinkedList<String> scored_words = new LinkedList<>();
        System.out.println(selectedWord);
        check.setOnMouseClicked((MouseEvent e)->{
        String word = txtField.getText().toLowerCase();
        Integer scores = Integer.valueOf(score.getText());
        try{
        for(int i = 0 ; i < shuffledWord.length() ; i++){
       if(!word.contains(String.valueOf(shuffledWord.charAt(i))) || word.length() != shuffledWord.length()){
           System.out.println(word);
           throw new RuntimeException();
       }
      }
        boolean verificador = trie.search(word);
        if(verificador && !scored_words.contains(word)){
            scores++;
            score.setText(String.valueOf(scores));
            scored_words.add(word);
            AlertBoxes.infoAlert("Good!", "Se ha encontrado la palabra tienes 1 punto mas :)", "Prueba con otra palabra");
        }else{throw new RuntimeException();}
        }catch( RuntimeException r){
            AlertBoxes.errorAlert("Fallo", "Ya ingresaste esta palabra o no ha utilizado todos los caracteres correctamente", "Inténte con otra palabra");
        }
        
        });
        
    }
    
    private String randomLetter(String word) {
        int index = RANDOM.nextInt(word.length());
        return Character.toString(word.charAt(index));
    }
    
    private static String shuffleWord(String word) {
        char[] characters = word.toCharArray();
        Random random = new Random();

        for (int i = 0; i < characters.length; i++) {
            int j = random.nextInt(characters.length);
            char temp = characters[i];
            characters[i] = characters[j];
            characters[j] = temp;
        }

        return new String(characters);
    }
    
    private void disableButtons(){
        check.setVisible(false);
        score.setVisible(false);
        puntosLabel.setVisible(false);
    }
    
    private void cargarBotones() throws FileNotFoundException{
         estadisticas.setGraphic(new ImageView(new Image(new FileInputStream("src\\main\\resources\\estadisticas.png" ),20,20,true,false)));  
         buscar.setGraphic(new ImageView(new Image(new FileInputStream("src\\main\\resources\\buscar.png" ),20,20,true,false)));  
         eliminar.setGraphic(new ImageView(new Image(new FileInputStream("src\\main\\resources\\eliminar.png" ),20,20,true,false)));  
         insertar.setGraphic(new ImageView(new Image(new FileInputStream("src\\main\\resources\\insertar.png" ),20,20,true,false)));
         game.setGraphic(new ImageView(new Image(new FileInputStream("src\\main\\resources\\game.png" ),20,20,true,false)));
         check.setGraphic(new ImageView(new Image(new FileInputStream("src\\main\\resources\\check.png" ),20,20,true,false)));
    }

}
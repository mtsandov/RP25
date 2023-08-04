package espol.trie;
/*
Put header here


 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLController implements Initializable {
    
    @FXML
    private Label lblOut;
    @FXML
    private TextField txtField;
    @FXML
    private Button searchButton;
    
    @FXML
    private void btnClickAction(ActionEvent event) {
        lblOut.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void loadTrieTree(){
        try (BufferedReader bf = new BufferedReader(new FileReader("src\\main\\resources\\com\\mycompany\\emojiapp\\words.txt"))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                
            }
        } catch (IOException ex) {
            System.out.println("no se pudieron cargar las palabras");
            
            ex.printStackTrace();
        }
    }
    


    
}

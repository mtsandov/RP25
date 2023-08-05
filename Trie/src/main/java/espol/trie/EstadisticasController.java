/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.trie;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Jurgen
 */
public class EstadisticasController implements Initializable {
    
    @FXML
    private TableView<Data> table;
    
    @FXML
    private TableColumn<Data, String> columna1;
    @FXML
    private TableColumn<Data, Integer> columna2;
    
    
    Integer num_palabras =0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        columna1.setCellValueFactory(new PropertyValueFactory<>("character"));
        columna2.setCellValueFactory(new PropertyValueFactory<>("number"));

        // Agregar datos de ejemplo al TableView
        table.getItems().addAll(
                new Data("Palabras",  cantidad_palabras()),
                new Data("A", contar_palabras().get(0)),
                 new Data("B", contar_palabras().get(1)),
                  new Data("C", contar_palabras().get(2)),
                   new Data("D", contar_palabras().get(3)),
                    new Data("E", contar_palabras().get(4)),
                     new Data("F", contar_palabras().get(5)),
                      new Data("G", contar_palabras().get(6)),
                       new Data("H", contar_palabras().get(7)),
                        new Data("I", contar_palabras().get(8)),
                         new Data("J", contar_palabras().get(9)),
                          new Data("K", contar_palabras().get(10)),
                           new Data("L", contar_palabras().get(11)),
                            new Data("M", contar_palabras().get(12)),
                             new Data("N", contar_palabras().get(13)),
                              new Data("O", contar_palabras().get(14)),
                               new Data("P", contar_palabras().get(15)),
                                new Data("Q", contar_palabras().get(16)),
                                 new Data("R", contar_palabras().get(17)),
                                  new Data("S", contar_palabras().get(18)),
                                   new Data("T", contar_palabras().get(19)),
                                    new Data("U", contar_palabras().get(20)),
                                     new Data("V", contar_palabras().get(21)),
                                      new Data("W", contar_palabras().get(22)),
                                       new Data("X", contar_palabras().get(23)),
                                        new Data("Y", contar_palabras().get(24)),
                                         new Data("Z", contar_palabras().get(25))
                
        );
    }    
    
    
    @FXML
    private void registrar(MouseEvent event) {        

   }
    
    public int cantidad_palabras(){
        try (BufferedReader bf = new BufferedReader(new FileReader("src\\main\\resources\\words.txt"))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                num_palabras++;
            }
        } catch (IOException ex) {
            System.out.println("no se pudieron cargar las palabras");
            
            ex.printStackTrace();
        }
        return num_palabras;
    }
    
    public ArrayList<Integer> contar_palabras(){
        ArrayList<Integer>  cantidades = new ArrayList<>();
        for(int i=0; i <27; i++){
            cantidades.add(0);
        }
        try (BufferedReader bf = new BufferedReader(new FileReader("src\\main\\resources\\words.txt"))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                Character character = linea.charAt(0);
                int indice = 0;
                for(String letra: letras()){
                if(letra.equals(String.valueOf(character))){
                    int valor = cantidades.get(indice);
                    valor += 1;
                    cantidades.set(indice, valor);
                }
                indice ++;
            }
           }
        } catch (IOException ex) {
            System.out.println("no se pudieron cargar las palabras");
            
            ex.printStackTrace();
        }
        
        return cantidades;
    }
    
     @FXML
    private void regresar(MouseEvent event) throws IOException {        
        MainApp.setRoot("primary","");
   }
 
    
    public ArrayList<String> letras(){
        ArrayList<String>  letras = new ArrayList<>();
        letras.add("a");
        letras.add("b");
        letras.add("c");
        letras.add("d");
        letras.add("e");
        letras.add("f");
        letras.add("g");
        letras.add("h");
        letras.add("i");
        letras.add("j");
        letras.add("k");
        letras.add("l");
        letras.add("m");
        letras.add("n");
        letras.add("o");
        letras.add("p");
        letras.add("q");
        letras.add("r");
        letras.add("s");
        letras.add("t");
        letras.add("u");
        letras.add("v");
        letras.add("w");
        letras.add("x");
        letras.add("y");
        letras.add("z");
        return letras;
    }


}
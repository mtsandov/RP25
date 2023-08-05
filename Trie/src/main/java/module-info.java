module espol {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires controlsfx;
    requires javafx.base;
    requires javafx.graphics;
  
    
    opens espol.trie to javafx.fxml;
    exports espol.trie;
      
    
}
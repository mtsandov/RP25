module espol {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires controlsfx;
    requires javafx.base;
    requires javafx.graphics;

    
    opens espol.trie to javafx.fxml;
    opens org.apache.commons.lang3 to espol;
    exports espol.trie;
    exports tda;
      
    
}
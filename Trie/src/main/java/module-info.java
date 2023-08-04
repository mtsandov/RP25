module espol {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires controlsfx;
    
    opens espol.trie to javafx.fxml;
    exports espol.trie;
}
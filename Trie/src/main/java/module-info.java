module espol {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens espol.trie to javafx.fxml;
    exports espol.trie;
}
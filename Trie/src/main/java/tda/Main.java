package tda;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Tree<Character> trie = new Tree(new TreeNode("Root Node"));

        // Insertar palabras en el árbol Trie
        trie.insert("helo");
        trie.insert("hipo");
        trie.insert("hola");
        trie.insert("apple");
        trie.insert("app");
        trie.insert("banana");
        trie.insert("orange");
        trie.insert("mora");
        trie.insert("morado");
        trie.insert("morada");
        trie.insert("morador");
        
        System.out.println(trie.search("orange"));
        
        // Realizar algunas pruebas
        System.out.println("¿Existe 'apple' en el árbol Trie? " + trie.search("apple")); // Debería imprimir true
        System.out.println("¿Existe 'app' en el árbol Trie? " + trie.search("app")); // Debería imprimir true
        System.out.println("¿Existe 'orange' en el árbol Trie? " + trie.search("orange")); // Debería imprimir true
        System.out.println("¿Existe 'ban' en el árbol Trie? " + trie.search("ban")); // Debería imprimir false
        System.out.println("¿Existe 'grape' en el árbol Trie? " + trie.search("grape")); // Debería imprimir false

        // Autocompletar palabras
        System.out.println("Autocompletar 'app': " + trie.autoComplete("ap")); // Debería imprimir ['app', 'apple']
        System.out.println("Autocompletar 'ora': " + trie.autoComplete("ora")); // Debería imprimir ['orange']
         System.out.println("Autocompletar 'h': " + trie.autoComplete("h")); // Debería imprimir ['orange']
         
//         trie.deleteWord("orange"); 
         System.out.println("¿Existe 'orange' en el árbol Trie? " + trie.search("orange")); // Debería imprimir false
         System.out.println("nodo de orange :"+ trie.getParentNode(trie.searchNode("orange")).getContent());
//         System.out.println("Autocompletar 'le': " + trie.autoCompleteBySuffix("ple")); // Debería imprimir ['orange']
        System.out.println(trie.getAllLeafWords());
        System.out.println(trie.searchNode("orang").getChildren());
        trie.eliminarPalabra("orange");
        System.out.println(trie.getAllLeafWords());
        System.out.println("Porcentajde de similitud entre mora y morado: " + trie.findSimilarity("mora", "morado"));
        
        
    }
   
    
    
    
}
 
package tda;

//import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Tree<E> {

private TreeNode<E> root;
    
private List<TreeNode<E>> leaves = new ArrayList<>(); // Lista para almacenar las hojas

    public Tree() {
        this.root = null;
       
    }
    
    public Tree(TreeNode<E> root) {
        this.root = root;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public TreeNode<E> getRoot() {
        if (!this.isEmpty()) {
            return root;
        }
        return null;
    }

    public void setRoot(String content) {
        this.root = new TreeNode<>(content);
    }

    public boolean isLeaf() {
        return root.getChildren().isEmpty();
    }

//    public boolean searchWord(String word) {
//        if (word == null || word.isEmpty()) {
//            return false;
//        }
//        return searchWord(root, word, 0);
//    }

//    private boolean searchWord(TreeNode<E> node, String word, int index) {
//        if (node == null) {
//            return false;
//        }
//        if (index == word.length()) {
//            return node.getContent() != null;
//        }
//        char ch = word.charAt(index);
//        return searchWord(node.getChildren().get(ch), word, index + 1); //REVISAR LINEA, POSIBLE EXCEPCION 
//        //O ERROR - get(ch) devuelve el valor asociado a la clave ch.
//        //Si el node que es el nodo actual es null no existe devuelve false.
//        //Se llega al final de la palabra y el node tiene contenido devuelve true ya que la palabra existe.
//        //Se hace recursion sabiendo que quedan caracteres por recorrer, se busca entre los hijos del node.
//        //Si en los hijos no se encuentra el siguiente caracter, devuelve false porque no existe la palabra.
//    }

     public boolean search(String word) {
    TreeNode<E> current = root;
    for (int i = 0; i < word.length(); i++) {
        char character = word.charAt(i);

        if (!current.getChildren().containsKey((E) Character.valueOf(character))) {
            // Si no se encuentra un enlace para la letra actual, la palabra no está en el árbol
            return false;
        }
        // Moverse al siguiente nodo
        current = current.getChildren().get((E) Character.valueOf(character));
    }
    // Verificar si el último nodo es una hoja y si la palabra está en el árbol
    return current.getContent() != null && current.getContent().compareTo(word)==0;
} 
    
    public void deleteWord(String word) {
        if (word == null || word.isEmpty()) {
            return;
        }
        // Buscando y guardando nodos. Objeccion: No se deberia guardar nodos, sino arboles. Revisar después
        TreeNode<E> node = this.root;
        Stack<TreeNode<E>> stack = new Stack<>();
        for (int i = 0; i < word.length() && node != null; i++) {
            stack.push(node);
            node = node.getChildren().get(word.charAt(i)); //REVISAR LINEA, POSIBLE EXCEPCION O ERROR
            //Se inserta el nodo actual en la pila, para poder acceder a él más tarde.
            //Se obtiene el hijo del nodo actual que tiene como clave el carácter de la palabra en la posición i
            //Se asigna el hijo obtenido al nodo actual, para avanzar en el árbol.
        }
        // No existe
        if (node == null || node.getContent() == null || this.isEmpty()) {
            return;
        }
        // Guarda referencia nodo que contiene la palabra y el índice del último carácter como en el PDF IMAGEN REVISAR
        TreeNode<E> lastNode = node;
        int lastIndex = word.length() - 1;
        // Recorrer el Trie desde el nodo hasta la raíz
        node = lastNode;
        while (!stack.isEmpty()) {
            TreeNode<E> parentNode = stack.pop();
            // Si el nodo no tiene hijos ELIMINARLO DEL MAPA
            if (node.getChildren().isEmpty() && node.getContent() == null) {
                parentNode.getChildren().remove(word.charAt(lastIndex)); //REVISAR LINEA, POSIBLE EXCEPCION O ERROR
                //Se verifica si el nodo actual tiene el mapa de hijos vacío y el contenido nulo, lo que significa que no es parte de ninguna otra palabra y se puede eliminar.
                //Entra al IF se elimina el nodo del mapa de hijos de su padre, pasando como argumento el carácter de la palabra en la posición lastIndex, que es la clave asociada al nodo.
            } else {
                break;
            }
            // ACTUALIZANDO
            node = parentNode;
            lastIndex--;
        }
        // Asignar contenido del nodo que contiene la palabra a null
        lastNode.setContent(null);
    }
    
    public void insert(String word) {
        TreeNode<E> current = root;
        int wordSize = word.length();

        for (int i = 0; i < wordSize; i++) {
            char character = word.charAt(i);           
            // Si el nodo actual no tiene un enlace para el carácter actual, lo crea
            if (!current.getChildren().containsKey((E) Character.valueOf(character)) ) {   
                    current.getChildren().put( (E) Character.valueOf(character), new TreeNode(String.valueOf(character)));
            }
            
            if (i == wordSize - 1) {
                current = current.getChildren().get((E) Character.valueOf(character));
                    current.setContent(word);
                    leaves.add(current);
                    break;
                }
            
            current = current.getChildren().get((E) Character.valueOf(character));
        }
    }

   //METODO PARA AUTOCOMPLETAR
    public List<String> autoComplete(String prefix) {
        List<String> words = new ArrayList<>();
        TreeNode<E> current = root;
        for (int i = 0; i < prefix.length(); i++) {
            char character = prefix.charAt(i);
            if (!current.getChildren().containsKey((E) Character.valueOf(character))) {
                // Si no se encuentra un enlace para la letra actual, no hay palabras que autocompletar
                return words;
            }
            // Moverse al siguiente nodo
            current = current.getChildren().get((E) Character.valueOf(character));
        }
        // Si el prefijo existe en el árbol Trie, realizar recorrido DFS para autocompletar palabras
        autoCompleteDFS(current, words);
        return words;
    }

    // Método auxiliar para realizar el recorrido DFS y autocompletar palabras
    private void autoCompleteDFS(TreeNode<E> node, List<String> words) {
        // Si el nodo es una hoja, se agrega la palabra autocompletada a la lista de palabras
        if (node.isLeaf()) {
            words.add(node.getContent());
        }
        // Recorrer todos los enlaces del nodo actual y llamar recursivamente para los nodos hijos
        for (TreeNode<E> child : node.getChildren().values()) {
            autoCompleteDFS(child,  words);
        }
    }
    
     public TreeNode<E> getParentNode(TreeNode<E> node) {
        return getParentNode(root, node);
    }

    // Método auxiliar recursivo para obtener el nodo padre de un nodo dado
    private TreeNode<E> getParentNode(TreeNode<E> current, TreeNode<E> target) {
        for (TreeNode<E> child : current.getChildren().values()) {
            if (child == target) {
                return current;
            } else {
                TreeNode<E> parent = getParentNode(child, target);
                if (parent != null) {
                    return parent;
                }
            }
        }
        return null;
    }
    
     public TreeNode<E> searchNode(String word) {
    TreeNode<E> current = root;
    for (int i = 0; i < word.length(); i++) {
        char character = word.charAt(i);

        if (!current.getChildren().containsKey((E) Character.valueOf(character))) {
            // Si no se encuentra un enlace para la letra actual, la palabra no está en el árbol
            return null;
        }
        // Moverse al siguiente nodo
        current = current.getChildren().get((E) Character.valueOf(character));
    }
    // Verificar si el último nodo es una hoja y si la palabra está en el árbol
    return current;
} 
     
 
 // Método para buscar palabras similares en el Trie utilizando la distancia de Levenshtein
    public List<String> searchSimilarWords(String word, int maxDistance) {
        List<String> similarWords = new ArrayList<>();
        searchSimilarWords(root, word, maxDistance, new StringBuilder(), similarWords);
        return similarWords;
    }

    // Método auxiliar para buscar palabras similares en el Trie utilizando la distancia de Levenshtein
    private void searchSimilarWords(TreeNode<E> node, String targetWord, int maxDistance,
                                   StringBuilder currentWord, List<String> similarWords) {
        if (node.isLeaf()) {
            String word = currentWord.toString();
            int distance = calculateLevenshteinDistance(word, targetWord);

            if (distance <= maxDistance) {
                similarWords.add(word);
            }
        }

        // Recorrer todos los enlaces del nodo actual y llamar recursivamente para los nodos hijos
        for (TreeNode<E> child : node.getChildren().values()) {
            currentWord.append( child.getContent());
            searchSimilarWords(child, targetWord, maxDistance, currentWord, similarWords);
            currentWord.deleteCharAt(currentWord.length() - 1);
        }
    }

    // Método para calcular la distancia de Levenshtein entre dos cadenas de caracteres
    private int calculateLevenshteinDistance(String str1, String str2) {
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= str2.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                int cost = (str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + cost);
            }
        }

        return dp[str1.length()][str2.length()];
    }

}


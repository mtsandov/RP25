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
    
    public boolean eliminarPalabra(String word){
         TreeNode<E> current = root;
        int wordSize = word.length();

        for (int i = 0; i < wordSize; i++) {
            char character = word.charAt(i);           
            // Si el nodo actual no tiene un enlace para el carácter actual, lo crea
            if (!current.getChildren().containsKey( Character.valueOf(character)) ) {   
                    return false;
            }
            
            if (i == wordSize - 2) {
                   current = current.getChildren().get( Character.valueOf(character));
                   for(int n =0; n<wordSize ; n++){
                       if(current.getChildren().size()==1){
                       current.getChildren().clear();
                       current= this.getParentNode(current);
                   }
                       current.getChildren().remove(word.charAt(0));
                   }
                    return true;
                }
            
            current = current.getChildren().get( Character.valueOf(character));
    }
        return false;
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
     
     // Método para recorrer todas las hojas del Trie
    public List<String> getAllLeafWords() {
        List<String> leafWords = new ArrayList<>();
        getAllLeafWords(root, leafWords);
        return leafWords;
    }

    // Método auxiliar para recorrer todas las hojas del Trie
    private void getAllLeafWords(TreeNode<E> node, List<String> leafWords) {
        if (node.isLeaf()) {
            leafWords.add(node.getContent());
            return;
        }
        // Recorrer todos los enlaces del nodo actual y llamar recursivamente para los nodos hijos
        for (TreeNode<E> child : node.getChildren().values()) {
            getAllLeafWords(child, leafWords);
        }
    }
    
     public double findSimilarity(String x, String y) {
        if (x == null || y == null) {
            throw new IllegalArgumentException("Strings must not be null");
        }
        double maxLength = Double.max(x.length(), y.length());
        if (maxLength > 0) {
            // opcionalmente ignora el caso si es necesario
            return (maxLength - getLevenshteinDistance(x, y)) / maxLength;
        }
        return 1.0;
    }

    // Método para calcular la distancia de Levenshtein entre dos cadenas de caracteres
     public int getLevenshteinDistance(String X, String Y)
    {
        int m = X.length();
        int n = Y.length();
 
        int[][] T = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            T[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            T[0][j] = j;
        }
 
        int cost;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                cost = X.charAt(i - 1) == Y.charAt(j - 1) ? 0: 1;
                T[i][j] = Integer.min(Integer.min(T[i - 1][j] + 1, T[i][j - 1] + 1),
                        T[i - 1][j - 1] + cost);
            }
        }
        return T[m][n];
    }
     
    
 

}


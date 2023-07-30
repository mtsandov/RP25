package tda;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class TreeNode<E> {

    private E content;

    // private List<Tree<E>> children;
    private Map<E, TreeNode<E>> children;
   
 
    private boolean isLeaf; // Indicador de nodo hoja
    private String word; // Palabra insertada (solo presente en nodos hoja)

    // private List<TreeNode<E>> children2; // NOOOO en este curso
/*    public TreeNode(E content) {
        this.content = content;
        this.children = new LinkedList<>();
    }*/
    
    
    public TreeNode(E content) {
        this.content = content;
        this.children = new HashMap<>();
    }

    public String getWord(){
        return word;
    }
    
    
    public E getContent() {
        return content;
    }
    

    public void setContent(E content) {
        this.content = content;
    }

    /*    public List<Tree<E>> getChildren() {
        return children;
    }*/
    public Map<E, TreeNode<E>> getChildren() {
        return children;
    }

    /*    public void setChildren(List<Tree<E>> children) {
        this.children = children;
    }*/

    void setLeaf(boolean b) {
        isLeaf=b;
    }

    void setWord(String word) {
        this.word=word;
    }

    boolean isLeaf() {
        return isLeaf;
    }
}

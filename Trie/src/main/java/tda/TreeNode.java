package tda;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TreeNode<E> {

    private String content;

    private Map<E, TreeNode<E>> children;
   
    
    
    public TreeNode(String content) {
        this.content = content;
        this.children = new HashMap<>();
    }


    public String getContent() {
        return content;
    }
    

    public void setContent(String content) {
        this.content = content;
    }

    public Map<E, TreeNode<E>> getChildren() {
        return children;
    }

    boolean isLeaf() {
        return children.isEmpty();
    }
}

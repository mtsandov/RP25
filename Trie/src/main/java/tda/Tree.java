package ggm.trees;

public class Tree<E> {
    
    private TreeNode<E> root;
    
    public Tree () {
       this.root = null; 
    }
    
    public boolean isEmpty () {
        return this.root == null;
    }
   
    public E getRoot() {
        if (!this.isEmpty()) {
            return root.getContent();
        }
        return null;
    }

    public void setRoot(E content) {
        this.root = new TreeNode<>(content);
    }
    
    
}

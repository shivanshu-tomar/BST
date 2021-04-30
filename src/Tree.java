class BSTNode<T extends Comparable<T>> implements Comparable<BSTNode<T>> {
    T data;
    BSTNode<T> left;
    BSTNode<T> right;

    public BSTNode(T data) {
        this.data = data;
        left = null;
        right = null;
    }

    @Override
    public String toString() {
        return "BSTNode{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    @Override
    public int compareTo(BSTNode<T> o) {
        return o.data.compareTo(this.data);
    }
}
public class Tree<T extends Comparable<T>> {
    BSTNode<T> root;
    static final int COUNT = 5;
    public void insertNode(T data){
        if(root==null){
            root = new BSTNode<T>(data);
        }
        else {
            BSTNode<T> temp=root,parent= null;
            while(temp!=null){
                parent = temp;
                if(data.compareTo(temp.data)<0){
                    temp = temp.left;
                }
                else temp = temp.right;
            }
            if(data.compareTo(parent.data)<0) parent.left = new BSTNode<>(data);
            else parent.right = new BSTNode<>(data);
        }
    }
    public void insertnodeNew(T data){
        if(root==null){
            root = new BSTNode<>(data);
            return;
        }
        BSTNode<T> temp = root;
        while(true){
            if(data.compareTo(temp.data)<0){
                if(temp.left==null) {
                    temp.left= new BSTNode<>(data);
                    return;
                }
                temp = temp.left;
            }
            else {
                if(temp.right==null) {
                    temp.right= new BSTNode<>(data);
                    return;
                }
                temp = temp.right;
            }
        }
    }

    //"Recursive insersertion In this you have to pass root and new root will return"
    public BSTNode<T> insertNode(BSTNode<T> root,T data){
        if(root== null){
            if(this.root==null) this.root =new BSTNode<>(data);
            return new BSTNode<>(data);
        }
        else{
            if(data.compareTo(root.data)<0) root.left = insertNode(root.left,data);
            else root.right = insertNode(root.right,data);
        }
        return root;
    }
    public BSTNode<T> deleteNode(BSTNode<T> root, T data){
        if(root == null){
            return null;
        }
        if(data.compareTo(root.data)<0) root.left = deleteNode(root.left,data);
        else if(data.compareTo(root.data)>0) root.right  = deleteNode(root.right,data);
        else {
            // If node has only one child or a leaf node
            if(root.left ==null||root.right==null){
                BSTNode temp =null;
                temp= root.left==null?root.right:root.left;
                return temp;
            }
            else {
                BSTNode<T> temp = getSuccessor(root);
                root.data =  temp.data;
                root.right = deleteNode(root.right,temp.data);
            }
        }
        return null;
    }
    public BSTNode<T> getSuccessor(BSTNode<T> root) {
        if(root==null){
            return null;
        }
        if(root.right!= null){
            BSTNode<T> temp = root.right;
            while(temp.left != null){
                temp =temp.left;
            }
            return temp;
        }
        return null;
    }
    public BSTNode<T> min(BSTNode<T> root){
        if(root==null) return null;
        if(root.left==null) return root;
        else return min(root.left);
    }
    void print2DUtil(BSTNode<T> root, int space)
    {
        // Base case
        if (root == null)
            return;

        // Increase distance between levels
        space += COUNT;

        // Process right child first
        print2DUtil(root.right, space);

        // Print current node after space
        // count
        System.out.print("\n");
        for (int i = COUNT; i < space; i++)
            System.out.print(" ");
        System.out.print(root.data + "\n");

        // Process left child
        print2DUtil(root.left, space);
    }

    // Wrapper over print2DUtil()
    void print2D(BSTNode<T> root)
    {
        print2DUtil(root, 0);
    }
    public int height(BSTNode<T> root){
        if(root==null) return 0;
        int hl = height(root.left);
        int hr = height(root.right);
        //return (hl>=hr?hl:hr + 1);
        if(hl>=hr) return hl+1;
        else return hr+1;
    }


    public static void main(String[] args) {
        Tree<Integer> mybst = new Tree<>();
        mybst.insertNode(mybst.root, 50);
        mybst.insertNode(mybst.root, 40);
        mybst.insertNode(mybst.root, 45);
        mybst.insertNode(mybst.root, 55);
        mybst.insertNode(mybst.root, 70);
        mybst.insertNode(mybst.root, 80);
        mybst.insertNode(mybst.root, 90);
        mybst.print2D(mybst.root);
        System.out.println(mybst.height(mybst.root));
    }

}

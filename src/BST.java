/**
 * @author ZBK
 * @date 2019/6/6 - 10:48
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @program: BST
 *
 * @description: 二分搜索树
 *
 * @author: Zbk
 *
 * @create: 2019-06-06 10:48
 **/
public class BST<E extends Comparable<E>> {

    private class Node{
        public E e;
        public Node left,right;

        public Node(E e ){
            this.e=e;
            left=null;
            right=null;
        }

    }

    private Node root;
    private int size;

    public BST(){
        root=null;
        size=0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }
    /**
    * @Description: 插入元素e
    * @Param: [e]
    * @return: void
    * @Author: ZBK
    * @Date: 2019/6/6
    */
    public void add(E e){
       root = add(root,e);
    }


    /*public void add(Node node,E e){
        //递归终止条件,最最基本的问题
        if(e.equals(node.e)) return;
        //如果node左子树为空,就直接放在这里
        if(e.compareTo(node.e)<0&&node.left==null){
            node.left=new Node(e);
            size++;
            return;
        }else if (e.compareTo(node.e)>0&&node.right==null){
            node.right=new Node(e);
            size++;
            return;
        }


        if (e.compareTo(node.e)>0) add(node.right,e);
        else add(node.left, e);
    }*/


    /** 
    * @Description: 向以node为根节点的二分搜索树中插入元素e,递归算法
     *              返回插入新节点后二分搜索树的根
    * @Param: [node, e] 
    * @return: BST<E>.Node 
    * @Author: ZBK 
    * @Date: 2019/6/6 
    */ 
    private Node add(Node node,E e){

        if (node==null){
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e)>0){
            node.right = add(node.right, e);
        }else if (e.compareTo(node.e)<0){
            node.left = add(node.left,e);
        }
        //没有判断e.compareTo(node.e)=0的情况下,因为这里我们默认,相等的就不添加了
        //也就是相同的数就不要了.
        return node;
    }

    //看二分搜索树中是否包含元素e
    public boolean contains(E e){
        return contains(root,e);
    }

    /** 
    * @Description: 看以node为根的二分搜索树中是否包含元素e,递归算法 
    * @Param: [root, e] 
    * @return: boolean 
    * @Author: ZBK 
    * @Date: 2019/6/7 
    */ 
    private boolean contains(Node node, E e) {

        if (node==null)
        return false;

        if (e.compareTo(node.e)==0){
            return true;
        }else if (e.compareTo(node.e)>0){
           return contains(node.right,e);
        }else {
            return contains(node.left,e);
        }
    }


    //二分搜索树的前序遍历
    public void preOrder(){
        preOrder(root);
    }

    /**
    * @Description: 前序遍历以node为根的二分搜索树,递归算法
    * @Param: [node]
    * @return: void
    * @Author: ZBK
    * @Date: 2019/6/7
    */
    private void preOrder(Node node) {
        //递归的条件
        if (node==null){
            return;
        }

        //递归的逻辑
        System.out.println(node.e);
        preOrder(node.left);

        preOrder(node.right);
    }

    /** 
    * @Description: 前序遍历非递归方法
    * @Param: []
    * @return: void 
    * @Author: ZBK 
    * @Date: 2019/6/10 
    */ 
    public void preOrderNR(){

        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);

            if (cur.right!=null)
                stack.push(cur.right);
            if (cur.left!=null)
                stack.push(cur.left);
        }
    }

    //后序遍历二分搜索树--用户用
    public void postOrder(){
        postOrder(root);

    }

    /** 
    * @Description: 后序遍历以node为根的二分搜索树,递归算法
    * @Param: [node] 
    * @return: void 
    * @Author: ZBK 
    * @Date: 2019/6/8 
    */ 
    private void postOrder(Node node) {
        if (node==null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    //二分搜索树的中序遍历,用户用
    public void inOrder(){
        inOrder(root);
    }

    //中序遍历以node为根的二分搜索树,递归算法
    private void inOrder(Node node) {

        if (node==null)
            return;

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    /** 
    * @Description: 二分搜索树的层序遍历
    * @Param: [] 
    * @return: void 
    * @Author: ZBK 
    * @Date: 2019/6/10 
    */ 
    public void levelOrder(){

        //这个queue是java自带的,实现要一个具体的底层数据结构,我们选择链表实现
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()){
            Node cur = q.remove();
            System.out.println(cur.e);

            if (cur.left!=null)
                q.add(cur.left);
            if (cur.right!=null)
                q.add(cur.right);
        }

    }

    //寻找二分搜索树的最小值
    public E minimum(){
        if (size==0)
            throw new IllegalArgumentException("BST is empty");

        return minimum(root).e;
    }

    private Node minimum(Node node) {

        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    //寻找二分搜索树最大值
    public E maximum(){
        if (size==0)
            throw new IllegalArgumentException("BST is Empty");
        return maximum(root).e;
    }
    private Node maximum(Node node) {

        if (node.right==null)
            return node;
        return maximum(node.right) ;
    }

    //从二分搜索树中删除最小值的节点,返回最小值
    public E removeMin(){
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    //删除以node为根的二分搜索树的最小节点
    //返回删除后新的二分搜索树的根
    private Node removeMin(Node node) {

        //node.left==null就是指已经遍历到最小值了
        if (node.left==null){
            Node rightNode = node.right;
            node.right=null;
            size--;
            return rightNode;
        }

        //将node.left改为node.left... .right
        node.left=removeMin(node.left);
        return node;
    }

    //从二分搜索树中删除最大值的节点,返回最小值
    public E removeMax(){
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    private Node removeMax(Node node) {

        if (node.right==null){
            Node leftNode = node.left;
            node.left=null;
            size--;
            return leftNode;
        }

        node.right=removeMax(node.right);
        return node;
    }

    /** 
    * @Description: 从二分搜索树中删除元素为e的节点
    * @Param: [e] 
    * @return: void 
    * @Author: ZBK 
    * @Date: 2019/6/10 
    */ 
    public void remove(E e){

       root = remove(root,e);
    }

    /** 
    * @Description: 删除以node为根的二分搜索树中值为e的节点,递归
     *              返回删除节点后新的二分搜索树的根
    * @Param: [node, e] 
    * @return: BST<E>.Node 
    * @Author: ZBK 
    * @Date: 2019/6/10 
    */ 
    private Node remove(Node node, E e) {

        //最小规模的解,说明根本就没找到值为e的节点
        if (node==null)
            return null;

        //如果要删除的元素要比当前node的e要小
        if (e.compareTo(node.e)<0){
            node.left=remove(node.left,e);
            return node;
        }
        //如果要删除的元素比当前node的e要大
        else if (e.compareTo(node.e)>0){
            node.right=remove(node.right, e);
            return node;
        }
        //也就是要删除的元素e,就是当前node的e
        //最小规模解
        else {

            //如果这个要删除的node左子树为空
            if (node.left==null){
                Node rightNode = node.right;
                node.right=null;
                size--;
                return rightNode;
            }

            //如果要删除的节点node的右子树为空
            if (node.right==null){
                Node leftNode = node.left;
                node.left=null;
                size--;
                return leftNode;
            }

            //如果要删除的节点左右都不为null
            //找到比待删除节点大最小节点,就是待删除节点右子树的最小节点
            //用这个节点顶替待删除节点
            else {
                Node successor = minimum(node.right);
                //我们不需要维护size,因为这里无论使用remove方法或者removeMin方法,内部都已经维护了size,让size--了
                //但是remove这个节点还没有消失,是被上一句的successor指着呢,其实不应该size--,因此我们在外面应该size++这样+回来
                //但是下面node.left=node.right=null这里size--,所以抵消掉了
                //successor.right = remove(node.right, successor.e);这个应该也可以
                successor.right = removeMin(node.right);
                //size++;
                successor.left=node.left;
                node.left=node.right=null;
                //size--;

                return successor;
            }
        }
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        generatBSTString(root,0,stringBuilder);
        return stringBuilder.toString();
    }

    /** 
    * @Description: 以nood为节点,深度为depth的前序便利的描述二叉树的字符串
    * @Param: [root, i, stringBuilder] 
    * @return: void 
    * @Author: ZBK 
    * @Date: 2019/6/7 
    */ 
    private void generatBSTString(Node node, int depth, StringBuilder res) {

        if (node==null) {
            //如果为空,其实返回null就可以了,但是为了表达深度,所以我们传入一个可以表达深度的字符串
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth)+node.e+"\n");
        generatBSTString(node.left, depth+1, res);
        generatBSTString(node.right, depth+1, res);

    }

    //表达深度的字符串的方法
    private String generateDepthString(int depth) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<depth;i++){
            stringBuilder.append("--");
        }

        return stringBuilder.toString();

    }


}

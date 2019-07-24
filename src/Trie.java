/**
 * @author ZBK
 * @date 2019/7/21 - 1:58
 */

import java.util.TreeMap;

/**
 * @program: Trie
 *
 * @description: Trie implement by TreeMap of Java
 *
 * @author: Zbk
 *
 * @create: 2019-07-21 01:58
 **/
public class Trie {

    private class Node{
        public boolean isWord;
        //对于每一个节点要有对下一个节点的映射
        //Trie没有使用泛型,而next的存储在的map中只定义了Character,默认我们每一个节点只存储Character这个对象
        //其实是一个假设,Trie完全不限于使用在英文中,比如汉语,韩语,等等都可以,只要能分割成一个一个单元
        public TreeMap<Character,Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }

    //根节点
    private Node root;
    //trie里存储了多少单词
    private int size;

    public Trie(){
        root = new Node();
        size=0;
    }

    //获得Trie中的单词量
    public int gitSize(){
        return size;
    }

    //add添加一个新的字符串而不是字符
    //我们添加一个字符串,要把这个字符串拆成一个一个字符,然后把一个个字符做成一个个节点,添加到树结构中
    public void add(String word){
        Node cur = root;
        for (int i=0;i<word.length();i++) {
            char c  = word.charAt(i);
            //如果这个节点指向的下一个节点不包含c这个字符了,我们就创建一个新节点储存c
            //如果这个节点指向的下一个节点包含这个节点,我们就直接指向这个节点就行了
            //比如存储panda了,下来存进去pan,root指向p,p有了,就不用创建直接指向就好了
            if (cur.next.get(c)==null)
                cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }

        //直到全部每个字符添加进去后,cur相当于来到了这个字符串最后一个字符所处的节点
        //注意这个节点不一定是叶子节点
        //比如已经有了panda,再添加pan,这个过程其实就是查找到了pan中这个n所在的节点,不一定是叶子节点了
        //但是要标识以下这个节点属于单词的末尾了
        //当然,再size++之前,如果结尾这个char,之前是flase也就是不代表单词结尾,我们才size++
        //比如之前添加了pan,再添加的话,那就不能size++了
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    //add的非递归
    public void addR(String word){
        add(root,word,0);
    }

    /** 
    * @Description: add方法的递归写法
     *              向以node为根的Trie中添加word[index...end), 递归算法
    * @Param: [node, word, index]
    * @return: void 
    * @Author: ZBK 
    * @Date: 2019/7/24 
    */ 
    private void add(Node node, String word, int index) {
        //最小解,当index指向word最低端的时候
        if (index == word.length()){
            if (!node.isWord){
                node.isWord=true;
                size++;
            }
            return;
        }

        char c = word.charAt(index);
        if (node.next.get(c)==null)
            node.next.put(c, new Node());
        add(node.next.get(c),word,index+1);
    }

    //查询单词word是否再trie中
    public boolean contains(String word){
        Node cur = root;
        for (int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if (cur.next.get(c)==null){
                return false;
            }else {
                cur = cur.next.get(c);
            }
        }
        //最后要看是否为最终节点,如果isword为false,说明最终虽然有这个单词的字母,但是并不代表有这个单词
        return cur.isWord;
    }

    //递归contains
    public boolean containsR(Node node,String word,int index){

        char c = word.charAt(index);
        if (node.next.get(c)==null){
            return false;
        }else {
            containsR(node.next.get(c),word,index+1);
        }

        if (index==word.length()){
            if (node.next.get(c)==null){
                return false
            }else  if (node.next.get(c)!=null){
                if (node.next.get(c).isWord!=false){
                    return true;
                }
            }
        }else {
            return false;
        }
    }

}

/**
 * @author ZBK
 * @date 2019/6/10 - 22:08
 */

/**
 * @program: Set
 *
 * @description: 基于BST实现的set,因为集合有序不可重复,所以要比较的,因此给定约束条件E要继承Comparable
 *
 * @author: Zbk
 *
 * @create: 2019-06-10 22:08
 **/
public class BSTSet<E extends Comparable<E>> implements Set<E> {
    private BST<E> bst;

    public BSTSet(){
        bst=new BST<>();
    }

    @Override
    public void add(E e) {
        //上章讲了二分搜索树,本身就对添加重复的元素不理会
        bst.add(e);
    }

    @Override
    public void remove(E e) {
        remove(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public int getSize() {
        return bst.size();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }
}

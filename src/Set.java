/**
 * @author ZBK
 * @date 2019/6/10 - 22:06
 */
public interface Set<E> {

    void add(E e);
    void remove(E e);
    boolean contains(E e);
    int getSize();
    boolean isEmpty();


}

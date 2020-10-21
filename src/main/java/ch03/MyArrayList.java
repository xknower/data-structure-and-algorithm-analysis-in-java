/**
 * 顺序存储结构
 * 
 * - 数组实现
 * 
 * @author xknower
 */
package ch03;

public class MyArrayList<AnyType> implements Iterable<AnyType> {

    private static final int DEFAULT_CAPACITY = 10;

    private AnyType[] theItems; // the data
    private int theSize; // the number of items in this collection.

    // ---------- ---------- ---------- ---------- ----------

    /**
     * Construct an empty ArrayList.
     */
    public MyArrayList() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    /**
     * Change the size of this collection to zero.
     */
    private void doClear() {
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    /**
     * Returns the number of items in this collection.
     * 
     * @return the number of items in this collection.
     */
    public int size() {
        return theSize;
    }

    /**
     * Returns true if this collection is empty.
     * 
     * @return true if this collection is empty.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    // ---------- ---------- ---------- ---------- ----------

    /**
     * Returns the item at position idx.
     * 
     * @param idx the index to search in.
     * @throws ArrayIndexOutOfBoundsException if index is out of range.
     */
    public AnyType get(int idx) {
        if (idx < 0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException("Index " + idx + "; size " + size());
        return theItems[idx];
    }

    /**
     * Changes the item at position idx.
     * 
     * @param idx    the index to change.
     * @param newVal the new value.
     * @return the old value.
     * @throws ArrayIndexOutOfBoundsException if index is out of range.
     */
    public AnyType set(int idx, AnyType newVal) {
        if (idx < 0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException("Index " + idx + "; size " + size());
        AnyType old = theItems[idx];
        theItems[idx] = newVal;

        return old;
    }

    @SuppressWarnings("unchecked")
    public void ensureCapacity(int newCapacity) {
        if (newCapacity < theSize)
            return;

        AnyType[] old = theItems;
        theItems = (AnyType[]) new Object[newCapacity];
        for (int i = 0; i < size(); i++)
            theItems[i] = old[i];
    }

    /**
     * Adds an item to this collection, at the end.
     * 
     * @param x any object.
     * @return true.
     */
    public boolean add(AnyType x) {
        add(size(), x);
        return true;
    }

    /**
     * Adds an item to this collection, at the specified index.
     * 
     * @param x any object.
     * @return true.
     */
    public void add(int idx, AnyType x) {
        if (theItems.length == size())
            ensureCapacity(size() * 2 + 1);

        // 将数据 x , 插入数组对应的 idx 坐标
        // 添加数据时, 将 idx -> theSize 的数据, 整体向后移动 一个 。 O(n)
        for (int i = theSize; i > idx; i--)
            theItems[i] = theItems[i - 1];

        theItems[idx] = x;
        theSize++;
    }

    /**
     * Removes an item from this collection.
     * 
     * @param idx the index of the object.
     * @return the item was removed from the collection.
     */
    public AnyType remove(int idx) {
        AnyType removedItem = theItems[idx];

        // 删除数组, 对应 idx 坐标数据
        // 删除数据时, 将 idx -> theSize 的数据, 整体向前移动 一个 。 O(n)
        for (int i = idx; i < size() - 1; i++)
            theItems[i] = theItems[i + 1];
        theSize--;

        return removedItem;
    }

    /**
     * Returns a String representation of this collection.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");

        for (AnyType x : this)
            sb.append(x + " ");
        sb.append("]");

        return new String(sb);
    }

    /**
     * Obtains an Iterator object used to traverse the collection.
     * 
     * @return an iterator positioned prior to the first element.
     */
    @Override
    public java.util.Iterator<AnyType> iterator() {
        return new ArrayListIterator();
    }

    /**
     * This is the implementation of the ArrayListIterator. It maintains a notion of
     * a current position and of course the implicit reference to the MyArrayList.
     */
    private class ArrayListIterator implements java.util.Iterator<AnyType> {
        private int current = 0;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public AnyType next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();

            okToRemove = true;
            return theItems[current++];
        }

        @Override
        public void remove() {
            if (!okToRemove)
                throw new IllegalStateException();

            MyArrayList.this.remove(--current);
            okToRemove = false;
        }
    }

}

class MyArrayListTest {
    public static void main(String[] args) {
        MyArrayList<Integer> lst = new MyArrayList<>();

        // 将数字[0 -> 9], 从数组末端插入
        for (int i = 0; i < 10; i++)
            lst.add(i);

        // -> [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ]

        // 将数字[29 -> 20], 从数组索引 0 插入
        for (int i = 20; i < 30; i++)
            lst.add(0, i);

        // -> [ 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ]

        // 删除头尾数据
        lst.remove(0);
        lst.remove(lst.size() - 1);

        // -> [ 28, 27, 26, 25, 24, 23, 22, 21, 20, 0, 1, 2, 3, 4, 5, 6, 7, 8 ]

        System.out.println(lst);
    }
}

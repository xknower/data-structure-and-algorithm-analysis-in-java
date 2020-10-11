/**
 * 泛型实现
 * 
 * Object read( )         -->  Returns the stored value
 * void write( Object x ) -->  x is stored
 * 
 * @author xknower
 */
package ch01;

public class TestMemoryCell {
    public static void main(String[] args) {
        MemoryCell<Integer> m = new MemoryCell<>();

        m.write(5);
        System.out.println("Contents are: " + m.read());
    }
}
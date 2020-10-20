
/**
 * 泛型实现 - 基本类型的包装 - Integer 包装类演示
 * 
 * @author xknower
 */
package ch01;

public class WrapperDemo {
    public static void main(String[] args) {
        // !Type safety: The method write(Object) belongs to the raw type MemoryCell. References to generic type MemoryCell<AnyType> should be parameterized
        MemoryCell<Integer> m = new MemoryCell<Integer>();

        m.write(new Integer(37));
        Integer wrapperVal = (Integer) m.read();
        int val = wrapperVal.intValue();
        System.out.println("Contnets are: " + val);
    }
}

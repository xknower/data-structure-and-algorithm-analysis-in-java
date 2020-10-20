/**
 * 泛型实现
 * 
 * AnyType -> T
 * 
 * Object read( )         -->  Returns the stored value
 * void write( Object x ) -->  x is stored
 * 
 * @author xknower
 */
package ch01;

public class MemoryCell<AnyType> {

  // Private internal data representation
  private AnyType storedValue;

  // Public methods
  public AnyType read() {
    return storedValue;
  }

  public void write(AnyType x) {
    storedValue = x;
  }
}


/**
 * 布谷鸟散列定义
 * 
 * @author xknower
 */
package ch05;

public interface HashFamily<AnyType> {
    int hash(AnyType x, int which);

    int getNumberOfFunctions();

    void generateNewFunctions();
}

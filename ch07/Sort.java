/**
 * A class that contains several sorting routines, implemented as static
 * methods. Arrays are rearranged with smallest item first, using compareTo.
 * 
 * @author Mark Allen Weiss
 */
package ch07;

public final class Sort {

    private static final int CUTOFF = 3;

    /**
     * 插入排序 : O(N^2), 在第p趟, 将位置p上的元素向左移动, 直到它在前p+1个元素中的正确位置被找到。
     * 
     * 通过比较相距一定间隔的元素来工作, 各趟比较所用的距离随着算法的进行而减小。直到只比较相邻元素的最后一趟排序为止。
     * 
     * Simple insertion sort.
     * 
     * @param a an array of Comparable items.
     */
    public static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType[] a) {
        int j;

        for (int p = 1; p < a.length; p++) {
            AnyType tmp = a[p];
            for (j = p; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--) {
                a[j] = a[j - 1];
            }
            a[j] = tmp;
        }
    }

    /**
     * 希尔排序 (缩减增量排序) : O(N^2)
     * 
     * 使用一个序列, H1, H2, ,,, Ht, 的增量序列(increment sequence)
     * 
     * 希尔排序的一个性质是, 一个 Hk排序的文件(然后将是 Hk-1)保持它的Hk排序性。
     * 
     * Shellsort, using Shell's (poor) increments.
     * 
     * 步骤 : 1. 确定一个分组值 2. 按照该分组间隔进行组内排序 3. 分组间隔减半, 重复前面步骤, 直到分组为 1 排序 (整个数据排序) -
     * 
     * @param a an array of Comparable items.
     */
    public static <AnyType extends Comparable<? super AnyType>> void shellsort(AnyType[] a) {
        int j;

        for (int gap = a.length / 2; gap > 0; gap /= 2)
            for (int p = gap; p < a.length; p++) {
                AnyType tmp = a[p];
                for (j = p; j >= gap && tmp.compareTo(a[j - gap]) < 0; j -= gap) {
                    a[j] = a[j - gap];
                }
                a[j] = tmp;
            }
    }

    /**
     * Internal method for heapsort.
     * 
     * @param i the index of an item in the heap.
     * @return the index of the left child.
     */
    private static int leftChild(int i) {
        return 2 * i + 1;
    }

    /**
     * Internal method for heapsort that is used in deleteMax and buildHeap.
     * 
     * 调整最大值, 并构建堆
     * 
     * @param a an array of Comparable items.
     * @index i the position from which to percolate down.
     * @int n the logical size of the binary heap.
     */
    private static <AnyType extends Comparable<? super AnyType>> void percDown(AnyType[] a, int i, int n) {
        int child;
        AnyType tmp;

        for (tmp = a[i]; leftChild(i) < n; i = child) {
            child = leftChild(i);
            if (child != n - 1 && a[child].compareTo(a[child + 1]) < 0)
                child++;
            if (tmp.compareTo(a[child]) < 0)
                a[i] = a[child];
            else
                break;
        }
        a[i] = tmp;
    }

    /**
     * 堆排序
     * 
     * Standard heapsort.
     * 
     * @param a an array of Comparable items.
     */
    public static <AnyType extends Comparable<? super AnyType>> void heapsort(AnyType[] a) {
        for (int i = a.length / 2 - 1; i >= 0; i--) /* buildHeap */
            percDown(a, i, a.length);
        for (int i = a.length - 1; i > 0; i--) {
            swapReferences(a, 0, i); /* deleteMax */
            percDown(a, 0, i);
        }
    }

    /**
     * 归并排序 : 通过基本操作, 合并两个已排序的表, 来实现排序
     * 
     * Mergesort algorithm.
     * 
     * @param a an array of Comparable items.
     */
    @SuppressWarnings("unchecked")
    public static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType[] a) {
        AnyType[] tmpArray = (AnyType[]) new Comparable[a.length];

        mergeSort(a, tmpArray, 0, a.length - 1);
    }

    /**
     * Internal method that makes recursive calls.
     * 
     * 递归地, 将前半部分数据和后半部分数据各自归并排序, 得到排序后的量两部分数据, 然后使用合并算法将两部分合并到一起
     * 
     * @param a        an array of Comparable items.
     * @param tmpArray an array to place the merged result.
     * @param left     the left-most index of the subarray.
     * @param right    the right-most index of the subarray.
     */
    private static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType[] a, AnyType[] tmpArray,
            int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, tmpArray, left, center);
            mergeSort(a, tmpArray, center + 1, right);
            merge(a, tmpArray, left, center + 1, right);
        }
    }

    /**
     * Internal method that merges two sorted halves of a subarray.
     * 
     * 合并算法 :
     * 
     * 1. 取输入数组 A, B, 输出数组 C , 3 个计数器 Actr, Bctr, Cctr; 初始位置对应数组的开始端
     * 
     * 2. A[Actr], B[Bctr], 拷贝到 C 中的下一个位置, 相关计数器向前推进一步
     * 
     * 3. 当两个输入表有一个完成时, 则将另一个表剩余部分拷贝到 C 中
     * 
     * @param a        an array of Comparable items.
     * @param tmpArray an array to place the merged result.
     * @param leftPos  the left-most index of the subarray.
     * @param rightPos the index of the start of the second half.
     * @param rightEnd the right-most index of the subarray.
     */
    private static <AnyType extends Comparable<? super AnyType>> void merge(AnyType[] a, AnyType[] tmpArray,
            int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        // Main loop
        while (leftPos <= leftEnd && rightPos <= rightEnd)
            if (a[leftPos].compareTo(a[rightPos]) <= 0)
                tmpArray[tmpPos++] = a[leftPos++];
            else
                tmpArray[tmpPos++] = a[rightPos++];

        while (leftPos <= leftEnd) // Copy rest of first half
            tmpArray[tmpPos++] = a[leftPos++];

        while (rightPos <= rightEnd) // Copy rest of right half
            tmpArray[tmpPos++] = a[rightPos++];

        // Copy tmpArray back
        for (int i = 0; i < numElements; i++, rightEnd--)
            a[rightEnd] = tmpArray[rightEnd];
    }

    /**
     * 快速排序
     * 
     * Quicksort algorithm.
     * 
     * 
     * 
     * @param a an array of Comparable items.
     */
    public static <AnyType extends Comparable<? super AnyType>> void quicksort(AnyType[] a) {
        quicksort(a, 0, a.length - 1);
    }

    /**
     * Method to swap to elements in an array.
     * 
     * @param a      an array of objects.
     * @param index1 the index of the first object.
     * @param index2 the index of the second object.
     */
    public static <AnyType> void swapReferences(AnyType[] a, int index1, int index2) {
        AnyType tmp = a[index1];
        a[index1] = a[index2];
        a[index2] = tmp;
    }

    /**
     * Return median of left, center, and right. Order these and hide the pivot.
     * 
     * 返回左、中、右的中间值。
     */
    private static <AnyType extends Comparable<? super AnyType>> AnyType median3(AnyType[] a, int left, int right) {
        int center = (left + right) / 2;
        if (a[center].compareTo(a[left]) < 0)
            swapReferences(a, left, center);
        if (a[right].compareTo(a[left]) < 0)
            swapReferences(a, left, right);
        if (a[right].compareTo(a[center]) < 0)
            swapReferences(a, center, right);

        // Place pivot at position right - 1
        swapReferences(a, center, right - 1);
        return a[right - 1];
    }

    /**
     * Internal quicksort method that makes recursive calls. Uses median-of-three
     * partitioning and a cutoff of 10.
     * 
     * 1. 如果数组S中元素个数是 0 或 1 , 则返回。如果是小数组, 使用插入排序。
     *
     * 2. 取数组中任意一个元素 v, 称之为 枢纽元 (pivot)。
     * 
     * 3. 将S-{v}(S中剩余元素), 划分成两个不相交的集合: S1={x<-S-{v} | x<= v} 和 S2={x<-S-{v} | x>=v}。
     * 
     * 4. 返回 { quicksort(S1) 后跟 v, 继而返回 quicksort(S2) }
     * 
     * @param a     an array of Comparable items.
     * @param left  the left-most index of the subarray.
     * @param right the right-most index of the subarray.
     */
    private static <AnyType extends Comparable<? super AnyType>> void quicksort(AnyType[] a, int left, int right) {
        if (left + CUTOFF <= right) {
            AnyType pivot = median3(a, left, right);

            // Begin partitioning. 开始分区。
            int i = left, j = right - 1;
            for (;;) {
                while (a[++i].compareTo(pivot) < 0) {
                }
                while (a[--j].compareTo(pivot) > 0) {
                }
                if (i < j)
                    swapReferences(a, i, j);
                else
                    break;
            }

            swapReferences(a, i, right - 1); // Restore pivot

            quicksort(a, left, i - 1); // Sort small elements
            quicksort(a, i + 1, right); // Sort large elements
        } else // Do an insertion sort on the subarray. 对小数组使用插入排序。
            insertionSort(a, left, right);
    }

    /**
     * Internal insertion sort routine for subarrays that is used by quicksort.
     * 
     * 快速排序使用的子数组的内部插入排序例程。
     * 
     * @param a     an array of Comparable items.
     * @param left  the left-most index of the subarray.
     * @param right the right-most index of the subarray.
     */
    private static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType[] a, int left, int right) {
        for (int p = left + 1; p <= right; p++) {
            AnyType tmp = a[p];
            int j;

            for (j = p; j > left && tmp.compareTo(a[j - 1]) < 0; j--)
                a[j] = a[j - 1];
            a[j] = tmp;
        }
    }

    /**
     * 快速选择算法
     * 
     * Quick selection algorithm. Places the kth smallest item in a[k-1].
     * 
     * @param a an array of Comparable items.
     * @param k the desired rank (1 is minimum) in the entire array.
     */
    public static <AnyType extends Comparable<? super AnyType>> void quickSelect(AnyType[] a, int k) {
        quickSelect(a, 0, a.length - 1, k);
    }

    /**
     * Internal selection method that makes recursive calls. Uses median-of-three
     * partitioning and a cutoff of 10. Places the kth smallest item in a[k-1].
     * 
     * @param a     an array of Comparable items.
     * @param left  the left-most index of the subarray.
     * @param right the right-most index of the subarray.
     * @param k     the desired index (1 is minimum) in the entire array.
     */
    private static <AnyType extends Comparable<? super AnyType>> void quickSelect(AnyType[] a, int left, int right,
            int k) {
        if (left + CUTOFF <= right) {
            AnyType pivot = median3(a, left, right);

            // Begin partitioning
            int i = left, j = right - 1;
            for (;;) {
                while (a[++i].compareTo(pivot) < 0) {
                }
                while (a[--j].compareTo(pivot) > 0) {
                }
                if (i < j)
                    swapReferences(a, i, j);
                else
                    break;
            }

            swapReferences(a, i, right - 1); // Restore pivot

            if (k <= i)
                quickSelect(a, left, i - 1, k);
            else if (k > i + 1)
                quickSelect(a, i + 1, right, k);
        } else // Do an insertion sort on the subarray
            insertionSort(a, left, right);
    }

}

class SortTest {

    private static final int NUM_ITEMS = 1000;
    private static int theSeed = 1;

    private static void checkSort(Integer[] a) {
        for (int i = 0; i < a.length; i++)
            if (a[i] != i)
                System.out.println("Error at " + i);
        System.out.println("Finished checksort");
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[NUM_ITEMS];
        for (int i = 0; i < a.length; i++)
            a[i] = i;

        for (theSeed = 0; theSeed < 20; theSeed++) {
            Random.permute(a);
            Sort.insertionSort(a);
            checkSort(a);

            Random.permute(a);
            Sort.heapsort(a);
            checkSort(a);

            Random.permute(a);
            Sort.shellsort(a);
            checkSort(a);

            Random.permute(a);
            Sort.mergeSort(a);
            checkSort(a);

            Random.permute(a);
            Sort.quicksort(a);
            checkSort(a);

            Random.permute(a);
            Sort.quickSelect(a, NUM_ITEMS / 2);
            System.out.println(a[NUM_ITEMS / 2 - 1] + " " + NUM_ITEMS / 2);
        }

        Integer[] b = new Integer[10_000_000];
        for (int i = 0; i < b.length; i++)
            b[i] = i;

        Random.permute(b);
        long start = System.currentTimeMillis();
        Sort.quickSelect(b, b.length / 2);
        long end = System.currentTimeMillis();
        System.out.println("Timing for Section 1.1 example: ");
        System.out.println("Selection for N = " + b.length + " takes " + (end - start) + "ms.");
        System.out.println(b[b.length / 2 - 1] + " " + b.length / 2);
    }
}

package com.flipkart.cp.transact.toa.util.common;
import java.util.List;
import java.util.Map;

/**
 * Created by padmanabh.kulkarni on 03/06/15.
 */
public class CollectionUtil {
    /**
     * Helper method to find the element <tt>element</tt> in the <tt>list</tt>
     * and returns the element if found else the functions returns <tt>null</tt>
     * value.
     *
     * <br/>
     * <br/>
     * <i>See if a {@link Map} can be used to solve the problem instead of using
     * this method</i>
     *
     * @param <T>
     *            Generic Class implementing equals method
     * @param list
     *            List of type <T>
     * @param element
     *            The object of type <T> to match in the list
     * @return matching element of type <T> if any matching element is found
     *         else returns <tt>null</tt>
     */
    public static <T> T find(List<T> list, T element) {
        T matchingT = null;
        for (T t : list) {
            if (t.equals(element)) {
                matchingT = t;
                break;
            }
        }
        return matchingT;
    }

    /**
     * Helper method to find if list is null or is empty. If list is null or
     * size is less than 1 it will return true otherwise false.
     *
     * @param list
     *            A Object of {@link List}
     * @return Return <b>true</b> if list is null (or empty) else <b>false</b>
     *         if list contain elements
     */
    public static boolean isEmpty(List<? extends Object> list) {
        if (list == null || list.size() < 1) {
            return true;
        }
        return false;
    }

    /**
     * Helper method to find if list is not null or not empty. If list is null or size is less than 1
     * it will return false otherwise true.
     * @param list  A Object of {@link List}
     * @return Return <b>true</b> if list contain elements else <b>false</b> if list is null (or empty)
     */
    public static boolean isNotEmpty(List< ? extends Object> list){
        return !isEmpty(list);
    }

}


package reference.datastructure.dynamicarray;

import java.util.Iterator;

/*
* 1 - Create a static array with an initial capacity;
* 2 - Add elements to the underlying static array,
* keeping track of the number of elements;
* 3 - If adding another element will exceed the capacity,
* then create a new static array with twice the capacity
* and copy the original elements into it.
*
* This implementation is similar to ArrayList
* */
@SuppressWarnings("unchecked")
public class DynamicArray<T> implements Iterable<T> {

    private T[] arr;
    private int len;      // Length user thinks array is
    private int capacity; // Actual array size

    public DynamicArray() {
        this(16);
    }

    public DynamicArray(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal capacity: " + capacity);
        }
        this.capacity = capacity;
        arr = (T[]) new Object[capacity];
    }

    public int size() {
        return len;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T get(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Illegal index: " + index);
        }
        return arr[index];
    }

    public void set(int index, T elem) {
        if (index < 0) {
            throw new IllegalArgumentException("Illegal index: " + index);
        }
        arr[index] = elem;
    }

    public void clear() {
        for (int i = 0; i < len; i++) {
            arr[i] = null;
        }
        len = 0;
    }

    public void add(T elem) {
        // Resize if needed
        if (len + 1 > capacity) {
            if (capacity == 0) capacity = 1;
            else capacity *= 2; // Double the size
            T[] new_arr = (T[]) new Object[capacity];
            for (int i = 0; i < len; i++) {
                new_arr[i] = arr[i];
            }
            arr = new_arr; // arr has extra null padded
        }
        arr[len++] = elem;
    }

    // Removes the element at the specified index in this list
    public T removeAt(int rm_index) {
        if (rm_index >= len || rm_index < 0) {
            throw new IndexOutOfBoundsException();
        }
        T data = arr[rm_index];
        T[] new_arr = (T[]) new Object[len - 1];
        for (int i = 0, j = 0; i < len; i++, j++) {
            if (i == rm_index) j--; // Skip over rm_index by fixing j temporarily
            else new_arr[j] = arr[i];
        }
        arr = new_arr;
        capacity = --len;
        return data;
    }

    public boolean remove(Object obj) {
        for (int i = 0; i < len; i++) {
            if (arr[i].equals(obj)) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    public int indexOf(Object obj) {
        for (int i = 0; i < len; i++) {
            if (arr[i].equals(obj)) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            // TODO check for concurrent modification
            int index;
            @Override
            public boolean hasNext() {
                return index < len;
            }

            @Override
            public T next() {
                return arr[index++];
            }
        };
    }

    @Override
    public String toString() {
        if (len == 0) return "[]";
        else {
            StringBuilder sb = new StringBuilder(len).append("[");
            for (int i = 0; i < len - 1; i++) {
                sb.append(arr[i] + ", ");
            }
            return sb.append(arr[len - 1] + "]").toString();
        }
    }
}

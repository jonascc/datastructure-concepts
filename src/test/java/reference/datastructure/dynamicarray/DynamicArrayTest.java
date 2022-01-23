package reference.datastructure.dynamicarray;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DynamicArrayTest {

    @Test
    public void should_throw_exception_for_negative_array_size() {
        Exception exc = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            DynamicArray dynArray = new DynamicArray<String>(-1);
        });
    }

    @Test
    public void should_add_element() {
        DynamicArray dynArray = new DynamicArray<String>();
        dynArray.add("A");

        assertThat(dynArray.get(0), is(equalTo("A")));
    }

    @Test
    public void should_remove_at() {
        DynamicArray dynArray = createDynamicArray();

        String removedObj = (String) dynArray.removeAt(1);
        assertThat(removedObj, is(equalTo("B")));
        assertThat(dynArray.size(), is(equalTo(2)));
        assertThat(dynArray.get(1), is(equalTo("C")));
    }

    @Test
    public void should_resize_array_when_capacity_is_exceeded() {
        DynamicArray dynArray = createDynamicArray(2);

        assertThat(dynArray.size(), is(equalTo(3)));
        assertThat(dynArray.get(0), is(equalTo("A")));
        assertThat(dynArray.get(1), is(equalTo("B")));
        assertThat(dynArray.get(2), is(equalTo("C")));
    }

    @Test
    public void should_remove_object() {
        DynamicArray dynArray = createDynamicArray(2);

        boolean removed = dynArray.remove("C");

        assertThat(removed, is(true));
        assertThat(dynArray.size(), is(2));
        assertThat(dynArray.get(0), is("A"));
        assertThat(dynArray.get(1), is("B"));
    }

    @Test
    public void should_return_index_of_the_object() {
        DynamicArray dynArray = createDynamicArray(2);

        var indexA = dynArray.indexOf("A");
        var indexB = dynArray.indexOf("B");
        var indexC = dynArray.indexOf("C");
        var indexc = dynArray.indexOf("c");

        assertThat(indexA, is(0));
        assertThat(indexB, is(1));
        assertThat(indexC, is(2));
        assertThat(indexc, is(-1));
    }

    @Test
    public void should_clear_array() {
        DynamicArray dynArray = createDynamicArray();

        dynArray.clear();

        assertThat(dynArray.size(), is(0));
    }

    private static DynamicArray<String> createDynamicArray(int size) {
        DynamicArray dynArray = new DynamicArray<String>(size);
        dynArray.add("A");
        dynArray.add("B");
        dynArray.add("C");

        return dynArray;
    }

    private static DynamicArray<String> createDynamicArray() {
        DynamicArray dynArray = new DynamicArray<String>();
        dynArray.add("A");
        dynArray.add("B");
        dynArray.add("C");

        return dynArray;
    }

}

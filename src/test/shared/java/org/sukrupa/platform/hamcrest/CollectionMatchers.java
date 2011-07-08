package org.sukrupa.platform.hamcrest;

import org.apache.commons.collections.CollectionUtils;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.apache.commons.collections.CollectionUtils.isEqualCollection;

public class CollectionMatchers {

    public static <T> Matcher<Collection<T>> hasOnly(final T... items) {
        return new TypeSafeMatcher<Collection<T>>() {
            public boolean matchesSafely(Collection<T> actual) {
                return isEqualCollection(actual, asList(items));
            }

            public void describeTo(Description description) {
                description.appendValueList("<<", ", ", ">>", items);
            }
        };
    }

    public static <T> Matcher<Map<String, ?>> hasEntry(final String key, final T value) {
        return new TypeSafeMatcher<Map<String, ?>>() {
            @Override
            protected boolean matchesSafely(Map<String, ?> map) {
                return map.containsKey(key) && map.get(key).equals(value);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("has entry with key [" + key + "] with value [" + value + "]");
            }
        };
    }

    public static <T> Matcher<Map<String, ?>> hasEntry(final String key) {
        return new TypeSafeMatcher<Map<String, ?>>() {
            @Override
            protected boolean matchesSafely(Map<String, ?> map) {
                return map.containsKey(key);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("has entry with key [" + key + "]");
            }
        };
    }
}

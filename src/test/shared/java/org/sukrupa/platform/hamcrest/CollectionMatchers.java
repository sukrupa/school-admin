package org.sukrupa.platform.hamcrest;

import org.apache.commons.collections.CollectionUtils;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Arrays;
import java.util.Collection;

import static java.util.Arrays.asList;
import static org.apache.commons.collections.CollectionUtils.isEqualCollection;

public class CollectionMatchers {

    public static <T> Matcher<Collection<T>> hasOnly(final T... items) {
        return new TypeSafeMatcher<Collection<T>>() {
            public boolean matchesSafely(Collection<T> actual) {
                return isEqualCollection(actual, asList(items));
            }

            public void describeTo(Description description) {
                description.appendText(Arrays.toString(items));
            }
        };
    }
}

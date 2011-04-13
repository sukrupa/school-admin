package org.sukrupa.platform.collection;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.sukrupa.platform.hamcrest.CollectionMatchers;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.sukrupa.platform.hamcrest.CollectionMatchers.hasOnly;

public class CollectionTransformationTest {

    @Test
    public void shouldReturnGenericHashSetFromCollection(){
        Collection collectionofStrings = asList("a", "v", "b", "c");

        HashSet<String> hashSet = CollectionTransformation.genericHashSetFrom(collectionofStrings);

        assertThat(hashSet, hasOnly("a", "v", "b", "c"));
    }


}

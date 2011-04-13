package org.sukrupa.platform.collection;


import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CollectionTransformation {

    @SuppressWarnings("unchecked")
    public static <T> HashSet<T> genericHashSetFrom(Collection collection) {
        return new HashSet<T>(collection);
    }
}

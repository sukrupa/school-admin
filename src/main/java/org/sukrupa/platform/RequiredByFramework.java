package org.sukrupa.platform;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
public @interface RequiredByFramework {
    // This annotation does nothing, but marks things that are required by Spring or other frameworks in order for
    // their magic to work.
}
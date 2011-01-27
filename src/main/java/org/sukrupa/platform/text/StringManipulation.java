package org.sukrupa.platform.text;

import org.apache.commons.lang.StringUtils;

import static java.io.File.separator;
import static java.util.Arrays.asList;

public class StringManipulation {
    public static String join(String... parts) {
        return StringUtils.join(asList(parts), separator);
    }
}

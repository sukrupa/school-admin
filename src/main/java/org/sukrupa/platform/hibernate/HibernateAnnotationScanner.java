package org.sukrupa.platform.hibernate;

import org.hibernate.cfg.*;

/**
 * The standard Hibernate Configuration requires you to add all classes individually
 * This extension allows you to specify "scanPackageForAnnotations"
 *
 * The standard Configuration class confusingly has "addPackage" on it which is a different thing altogether
 * and refers to "package annotations"  (see http://java.sun.com/docs/books/jls/third_edition/html/packages.html)
 */
public class HibernateAnnotationScanner {


    public HibernateAnnotationScanner(Configuration configuration) {

    }

    public void scanPackageForAnnotatedClasses(String packageName) {

    }
}
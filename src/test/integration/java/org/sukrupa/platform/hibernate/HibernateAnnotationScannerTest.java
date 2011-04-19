package org.sukrupa.platform.hibernate;

import org.hibernate.cfg.*;
import org.junit.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HibernateAnnotationScannerTest {

    @Ignore("WIP JimB, trying something out...")
    @Test
    public void shouldAddAllClassesInAPackageToTheHibernateConfig() {
        Configuration configuration = mock(Configuration.class);

        HibernateAnnotationScanner scanner = new HibernateAnnotationScanner(configuration);

        scanner.scanPackageForAnnotatedClasses("org.sukrupa.platform.hibernate");

        verify(configuration).addAnnotatedClass(DummyEntity.class);

    }
}
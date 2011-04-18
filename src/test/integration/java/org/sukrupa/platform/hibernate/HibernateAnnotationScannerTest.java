package org.sukrupa.platform.hibernate;

import org.eclipse.jdt.internal.codeassist.complete.*;
import org.hibernate.cfg.*;
import org.junit.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HibernateAnnotationScannerTest {

    @Ignore("WIP JimB, trying something out...")
    @Test
    public void shouldAddAllClassesInAPackageToTheHibernateConfig() {
        Configuration configuration = mock(Configuration.class);

        PackageScanningConfiguration scanner = new PackageScanningConfiguration(configuration);

        scanner.scanPackageForAnnotatedClasses("org.sukrupa.platform.hibernate");

        verify(configuration).addAnnotatedClass(DummyEntity.class);

    }
}
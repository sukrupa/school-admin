package org.sukrupa.platform.hsqldb.io;

import java.security.*;

import static java.lang.String.*;
import static java.lang.System.*;

public class SystemExitRecorder {
    private final TrappingExitSecurityManager trappingExitSecurityManager = new TrappingExitSecurityManager();

    private SecurityManager originalSecurityManager;

    public void trapSystemExit() {
        originalSecurityManager = getSecurityManager();
        setSecurityManager(trappingExitSecurityManager);
    }

    public void releaseSystemExit() {
        setSecurityManager(originalSecurityManager);
    }

    public void expectExitStatusOf(int expectedStatus) {
        trappingExitSecurityManager.expectStatusOf(expectedStatus);
    }

    public static class TrappingExitSecurityManager extends SecurityManager {
        private int expectedStatus;

        public TrappingExitSecurityManager() {
        }

        public void expectStatusOf(int expectedStatus) {
            this.expectedStatus = expectedStatus;
        }


        @Override public void checkExit(int status) {
            if (expectedStatus == status) {
                throw new ExpectedExitStatusException(expectedStatus);
            }
            throw new UnexpectedExitStatusException(expectedStatus, status);
        }

        @Override public void checkPermission(Permission permission) {
        }

        @Override public void checkPermission(Permission permission, Object o) {
        }

        public static class UnexpectedExitStatusException extends RuntimeException {
            public UnexpectedExitStatusException(int expectedStatus, int actualStatus) {
                super(format("Oops, System.exit(%d) was called but we expected (%d), this exception prevents the JVM from exiting.", actualStatus, expectedStatus));
            }
        }
    }


    public static class ExpectedExitStatusException extends RuntimeException {
        public ExpectedExitStatusException(int status) {
            super(format("As expected, System.exit(%d) was called, this exception prevents the JVM from exiting.", status));
        }
    }
}
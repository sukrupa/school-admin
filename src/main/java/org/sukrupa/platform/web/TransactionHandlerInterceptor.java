package org.sukrupa.platform.web;

import org.hibernate.TransactionException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// FIXME: this class is not thread safe but it has to be!
public class TransactionHandlerInterceptor extends DefaultTransactionDefinition
        implements HandlerInterceptor {

    private final PlatformTransactionManager transactionManager;

    private TransactionStatus status;

    public TransactionHandlerInterceptor(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        status = this.transactionManager.getTransaction(this);
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // do nothing - this is before rendering view
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (ex != null) {
            rollbackOnException(status, ex);
            throw ex;
        }
        this.transactionManager.commit(status);
    }


    private void rollbackOnException(TransactionStatus status, Throwable ex) throws TransactionException {
        try {
            this.transactionManager.rollback(status);
        }
        catch (TransactionSystemException ex2) {
            ex2.initApplicationException(ex);
            throw ex2;
        }
        catch (RuntimeException ex2) {
            throw ex2;
        }
        catch (Error err) {
            throw err;
        }
    }

}

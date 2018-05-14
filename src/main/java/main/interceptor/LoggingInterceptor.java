package main.interceptor;

import main.domain.Log;
import main.service.LogService;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.Arrays;

/**
 * @author Thom van de Pas on 14-5-2018
 */
@Interceptor
@Logging
public class LoggingInterceptor {

    @Inject
    private LogService logService;

    @AroundInvoke
    public Object log(InvocationContext context) throws Exception {
        String className = context.getMethod().getDeclaringClass().getName();
        String methodName = context.getMethod().getName();
        String parameters = Arrays.toString(context.getParameters());

        Log log = new Log(className, methodName, parameters);
        this.logService.createOrUpdate(log);

        return context.proceed();
    }
}

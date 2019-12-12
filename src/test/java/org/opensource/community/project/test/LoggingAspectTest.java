package org.opensource.community.project.test;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.opensource.community.project.LoggingAspect;
import org.opensource.community.project.service.EmployeeService;
public class LoggingAspectTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private ProceedingJoinPoint proceedingJoinPoint;

    @Mock
    private MethodSignature signature;

    private LoggingAspect loggingAspect = new LoggingAspect();

    String[] paramNames = { "param1", "param2" };

    Class[] paramTypes = { String.class, String.class };

    Object[] args = { "arg1", "arg2" };

    @Test
    public void testEntryLogs() throws Throwable {
        Mockito.when(proceedingJoinPoint.getSignature()).thenReturn(signature);
        Mockito.when(proceedingJoinPoint.getArgs()).thenReturn(args);
        Mockito.when(signature.getDeclaringType()).thenReturn(EmployeeService.class);
        Mockito.when(signature.getName()).thenReturn("EmployeeService");
        Mockito.when(signature.getParameterNames()).thenReturn(paramNames);
        Mockito.when(signature.getParameterTypes()).thenReturn(paramTypes);
        loggingAspect.logEntry(proceedingJoinPoint);
        verify(proceedingJoinPoint, times(1)).getSignature();
    }

    @Test
    public void testExitLogs() throws Throwable {
        Mockito.when(proceedingJoinPoint.getSignature()).thenReturn(signature);
        Mockito.when(signature.getDeclaringType()).thenReturn(EmployeeService.class);
        Mockito.when(signature.getName()).thenReturn("EmployeeService");
        Mockito.when(proceedingJoinPoint.getArgs()).thenReturn(args);
        Mockito.when(signature.getParameterNames()).thenReturn(paramNames);
        Mockito.when(signature.getParameterTypes()).thenReturn(paramTypes);
        loggingAspect.logExit(proceedingJoinPoint);
        verify(proceedingJoinPoint, times(1)).getSignature();
    }
}
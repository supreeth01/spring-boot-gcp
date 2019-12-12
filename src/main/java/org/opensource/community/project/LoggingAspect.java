package org.opensource.community.project;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
@Aspect
@Configuration
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Value("${CM_LOGGING_CONFIG}")
    private String loggingType;

    @Before("execution(* org.opensource.community.project..*(..))")
    public void logEntry(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = signature.getDeclaringType().getSimpleName();
        String methodName = signature.getName();
        String[] paramNames = signature.getParameterNames();
        Class[] paramTypes = signature.getParameterTypes();
        Object[] args = joinPoint.getArgs();
        String entryLog = "-> " + className + "." + methodName + "()";
        if (paramNames != null && paramNames.length > 0) {
            if (loggingType != null && loggingType.equalsIgnoreCase(EmployeeConstants.SIMPLIFIED_LOGGING)) {
                List<Object> primitiveArgsList = new ArrayList<Object>();
                entryLog = setSimplifiedLoggingParams(paramNames, paramTypes, entryLog, args, primitiveArgsList);
                LOGGER.debug(entryLog, primitiveArgsList);
            } else if (loggingType != null && loggingType.equalsIgnoreCase(EmployeeConstants.ALL_LOGGING)) {
                entryLog = setAllLoggingParams(paramNames, entryLog);
                LOGGER.debug(entryLog, args);
            } else {
                LOGGER.debug(entryLog);
            }
        } else {
            LOGGER.debug(entryLog);
        }
    }

    @After("execution(* org.opensource.community.project..*(..))")
    public void logExit(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = signature.getDeclaringType().getSimpleName();
        String methodName = signature.getName();
        String exitLog = "<- " + className + "." + methodName + "()";
        String[] paramNames = signature.getParameterNames();
        Class[] paramTypes = signature.getParameterTypes();
        Object[] args = joinPoint.getArgs();
        if (loggingType != null && loggingType.equalsIgnoreCase(EmployeeConstants.SIMPLIFIED_LOGGING)) {
            List<Object> primitiveArgsList = new ArrayList<Object>();
            exitLog = setSimplifiedLoggingParams(paramNames, paramTypes, exitLog, args, primitiveArgsList);
            LOGGER.debug(exitLog, primitiveArgsList);
        } else if (loggingType != null && loggingType.equalsIgnoreCase(EmployeeConstants.ALL_LOGGING)) {
            exitLog = setAllLoggingParams(paramNames, exitLog);
            LOGGER.debug(exitLog, args);
        } else {
            LOGGER.debug(exitLog);
        }
    }

    private boolean isPrimitive(Class<?> paramType) {
        if (paramType != null && (paramType.getName().contains("boolean") || paramType.getName().contains("byte") || paramType.getName().contains("char") || paramType.getName().contains("short") || paramType.getName().contains("int") || paramType.getName().contains("long") || paramType.getName().contains("float") || paramType.getName().contains("double"))) {
            return true;
        }
        return false;
    }

    private String setSimplifiedLoggingParams(String[] paramNames, Class[] paramTypes, String eLog, Object[] args, List<Object> primitiveArgsList) {
        List<String> paramNamesList = Arrays.asList(paramNames);
        int paramSize = paramNamesList.size();
        StringBuilder builder = new StringBuilder(eLog);
        for (int i = 0; i < paramSize; i++) {
            String paramName = paramNamesList.get(i);
            Class<?> paramType = paramTypes[i];
            if (isPrimitive(paramType)) {
                builder = builder.append("[" + paramName + "={}]");
                primitiveArgsList.add(args[i]);
            }
        }
        return builder.toString();
    }

    private String setAllLoggingParams(String[] paramNames, String eLog) {
        List<String> paramNamesList = Arrays.asList(paramNames);
        int paramSize = paramNamesList.size();
        String logValue = eLog;
        StringBuilder builder = new StringBuilder(eLog);
        for (int i = 0; i < paramSize; i++) {
            String paramName = paramNamesList.get(i);
            builder = builder.append("[" + paramName + "={}]");
        }
        return builder.toString();
    }
}
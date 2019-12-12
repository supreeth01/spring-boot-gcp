package org.opensource.community.project.error;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import org.hibernate.TypeMismatchException;
import org.opensource.community.project.dto.DepartmentErrorResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
/**
 * The Class DepartmentHttpErrorMapper.
 */
public class DepartmentHttpErrorMapper {

    /** The Constant logger. */
    static final Logger logger = LoggerFactory.getLogger(DepartmentHttpErrorMapper.class);

    /**
	 * Map exception.
	 *
	 * @param t
	 *            the t
	 * @param response
	 *            the response
	 * @return the department error response DTO
	 */
    public static DepartmentErrorResponseDTO mapException(Throwable t, HttpServletResponse response) {
        logger.info("-> HttpErrorMapper#mapException");
        logger.error("Exception: {}, {}", t, t.getMessage());
        DepartmentErrorResponseDTO err = new DepartmentErrorResponseDTO();
        // System Exceptions
        if (t instanceof ServletRequestBindingException) {
            err.setErrorCode(DEPARTMENT_ERROR_CODES.GEN_INVALID_INPUT);
            err.setErrorMessage("Request or request parameters are incorrect");
            err.setException(t);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        } else if (t instanceof TypeMismatchException) {
            err.setErrorCode(DEPARTMENT_ERROR_CODES.GEN_INVALID_INPUT);
            err.setErrorMessage("Request or request parameters are incorrect");
            err.setException(t);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        } else if (t instanceof HttpMediaTypeNotSupportedException) {
            err.setErrorCode(DEPARTMENT_ERROR_CODES.GEN_INVALID_INPUT);
            err.setErrorMessage("Request or request parameters are incorrect");
            err.setException(t);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        } else if (t instanceof HttpMessageNotReadableException) {
            logger.info("Exception: HttpMessageNotReadableException");
            err.setErrorCode(DEPARTMENT_ERROR_CODES.GEN_INVALID_INPUT);
            err.setErrorMessage(t.getMessage());
            // For some reason, uncommenting below line throws further exception in JSON
            // mapping
            // err.setException(t);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        } else if (t instanceof ConstraintViolationException) {
            logger.info("Exception: ConstraintViolationException: {}", (Object) t.getStackTrace());
            ConstraintViolationException cve = (ConstraintViolationException) t;
            logger.debug("{} {}", cve.getCause(), cve.getConstraintViolations());
            err.setErrorCode(t.getClass().toString());
            err.setErrorMessage(t.getMessage());
            // For some reason, uncommenting below line throws further exception in JSON
            // mapping
            // err.setException(t);
            //err.setStackTrace(t.getStackTrace());
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        } else if (t instanceof MethodArgumentNotValidException) {
            logger.info("Exception: ConstraintViolationException: {}", (Object) t.getStackTrace());
            MethodArgumentNotValidException manve = (MethodArgumentNotValidException) t;
            logger.debug("{} {}", manve.getCause(), manve.getParameter());
            err.setErrorCode(t.getClass().toString());
            err.setErrorMessage(t.getMessage());
            // For some reason, uncommenting below line throws further exception in JSON
            // mapping
            // err.setException(t);
            //err.setStackTrace(t.getStackTrace());
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        } else if (t instanceof BaseDepartmentUncheckedException) {
            // Known (our own) Exceptions
            logger.info("mapException: Exception is of type BaseUncheckedException");
            BaseDepartmentUncheckedException bue = (BaseDepartmentUncheckedException) t;
            logger.debug(bue.getErrorCode());
            logger.debug(bue.getErrorText());
            logger.debug(bue.getCodes().toString());
            logger.debug(bue.getErrorTexts().toString());
            err.setErrorCode(bue.getErrorCode());
            err.setErrorMessage(bue.getErrorText());
            // err.setException(t);
            err.setNestedErrorCodes(bue.getCodes());
            err.setNestedErrorStrings(bue.getErrorTexts());
            if (t instanceof DepartmentNotFoundException) {
                response.setStatus(HttpStatus.NOT_FOUND.value());
            } else if (t instanceof DepartmentPersistanceException) {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
            } else {
                logger.warn("Exception not mapped in HttpErrorMapper: " + t.getClass());
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        } else {
            logger.error("Exception: {}", t);
            err.setErrorCode("ERR_UNKNOWN");
            err.setErrorMessage(t.getClass() + ": " + t.getMessage());
            // err.setException(null);
            err.setException(t);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        logger.info("<- HttpErrorMapper#mapException");
        return err;
    }
}
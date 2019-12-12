package org.opensource.community.project.dto;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
/**
 * The Class AddressErrorResponseDTO.
 */
public class AddressErrorResponseDTO {

    /** The error code. */
    private String errorCode;

    /** The error message. */
    private String errorMessage;

    /** The exception. */
    private Throwable exception;

    /** The nested error codes. */
    private List<String> nestedErrorCodes;

    /** The nested error strings. */
    private List<String> nestedErrorStrings;

    /** The nested exception. */
    private AddressErrorResponseDTO nestedException;

    /**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
    public String getErrorCode() {
        return errorCode;
    }

    /**
	 * Sets the error code.
	 *
	 * @param errorCode the new error code
	 */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
	 * Gets the error message.
	 *
	 * @return the error message
	 */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
	 * Sets the error message.
	 *
	 * @param errorMessage the new error message
	 */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
	 * Gets the exception.
	 *
	 * @return the exception
	 */
    public Throwable getException() {
        return exception;
    }

    /**
	 * Sets the exception.
	 *
	 * @param exception the new exception
	 */
    public void setException(Throwable exception) {
        this.exception = exception;
    }

    /**
	 * Gets the nested exception.
	 *
	 * @return the nested exception
	 */
    public AddressErrorResponseDTO getNestedException() {
        return nestedException;
    }

    /**
	 * Sets the nested exception.
	 *
	 * @param nestedException the new nested exception
	 */
    public void setNestedException(AddressErrorResponseDTO nestedException) {
        this.nestedException = nestedException;
    }

    /**
	 * Gets the nested error codes.
	 *
	 * @return the nested error codes
	 */
    public List<String> getNestedErrorCodes() {
        return nestedErrorCodes;
    }

    /**
	 * Sets the nested error codes.
	 *
	 * @param nestedErrorCodes the new nested error codes
	 */
    public void setNestedErrorCodes(List<String> nestedErrorCodes) {
        this.nestedErrorCodes = nestedErrorCodes;
    }

    /**
	 * Gets the nested error strings.
	 *
	 * @return the nested error strings
	 */
    public List<String> getNestedErrorStrings() {
        return nestedErrorStrings;
    }

    /**
	 * Sets the nested error strings.
	 *
	 * @param nestedErrorStrings the new nested error strings
	 */
    public void setNestedErrorStrings(List<String> nestedErrorStrings) {
        this.nestedErrorStrings = nestedErrorStrings;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return "AddressErrorResponseDTO [errorCode=" + errorCode + ", errorMessage=" + errorMessage + ", exception=" + exception + ", nestedErrorCodes=" + nestedErrorCodes + ", nestedErrorStrings=" + nestedErrorStrings + ", nestedException=" + nestedException + "]";
    }
    /**
	 * Gets the stack trace.
	 *
	 * @return the stack trace
	 */
    /*	public StackTraceElement[] getStackTrace() {
		return stackTrace;
	}

	*/
    /**
	 * Sets the stack trace.
	 *
	 * @param stackTrace the new stack trace
	 */
    /*
	public void setStackTrace(StackTraceElement[] stackTrace) {
		this.stackTrace = stackTrace;
	}
	*/
}
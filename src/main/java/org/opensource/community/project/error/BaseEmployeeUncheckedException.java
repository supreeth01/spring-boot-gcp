package org.opensource.community.project.error;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
/**
 * The Class BaseEmployeeUncheckedException.
 */
public class BaseEmployeeUncheckedException extends RuntimeException {

    /** The Constant serialVersionUID. */
    public static final long serialVersionUID = -1;

    /** The info items. */
    protected List<InfoItem> infoItems = new ArrayList<InfoItem>();

    /**
	 * The Class InfoItem.
	 */
    protected static class InfoItem {

        /** The error code. */
        public String errorCode = null;

        /** The error text. */
        public String errorText = null;

        /**
		 * Instantiates a new info item.
		 *
		 * @param errorCode the error code
		 * @param errorText the error text
		 */
        public InfoItem(String errorCode, String errorText) {
            this.errorCode = errorCode;
            this.errorText = errorText;
        }
    }

    /**
	 * Instantiates a new base employee unchecked exception.
	 *
	 * @param errorCode the error code
	 * @param errorMessage the error message
	 */
    public BaseEmployeeUncheckedException(String errorCode, String errorMessage) {
        super(errorCode + " --> " + errorMessage);
        addInfo(errorCode, errorMessage);
    }

    /**
	 * Instantiates a new base employee unchecked exception.
	 *
	 * @param errorCode the error code
	 * @param errorMessage the error message
	 * @param cause the cause
	 */
    public BaseEmployeeUncheckedException(String errorCode, String errorMessage, Throwable cause) {
        super(errorCode + " --> " + errorMessage, cause);
        addInfo(errorCode, errorMessage);
        if (cause instanceof BaseEmployeeUncheckedException) {
            BaseEmployeeUncheckedException bue = (BaseEmployeeUncheckedException) cause;
            infoItems.addAll(bue.infoItems);
        }
    }

    /**
	 * Adds the info.
	 *
	 * @param errorCode the error code
	 * @param errorText the error text
	 * @return the base employee unchecked exception
	 */
    public BaseEmployeeUncheckedException addInfo(String errorCode, String errorText) {
        this.infoItems.add(new InfoItem(errorCode, errorText));
        return this;
    }

    /**
	 * Gets the codes.
	 *
	 * @return the codes
	 */
    public List<String> getCodes() {
        List<String> codes = new ArrayList<String>();
        for (int i = this.infoItems.size() - 1; i >= 0; i--) {
            codes.add(this.infoItems.get(i).errorCode);
        }
        return codes;
    }

    /**
	 * Gets the error texts.
	 *
	 * @return the error texts
	 */
    public List<String> getErrorTexts() {
        List<String> errorTexts = new ArrayList<String>();
        for (int i = this.infoItems.size() - 1; i >= 0; i--) {
            errorTexts.add(this.infoItems.get(i).errorText);
        }
        return errorTexts;
    }

    /**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
    public String getErrorCode() {
        return infoItems.get(0).errorCode;
    }

    /**
	 * Gets the error text.
	 *
	 * @return the error text
	 */
    public String getErrorText() {
        return infoItems.get(0).errorText;
    }

    /* (non-Javadoc)
	 * @see java.lang.Throwable#toString()
	 */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getCodes().toString());
        builder.append('\n');
        // append additional context information.
        for (int i = this.infoItems.size() - 1; i >= 0; i--) {
            InfoItem info = this.infoItems.get(i);
            builder.append(info.errorCode);
            builder.append(": ");
            builder.append(info.errorText);
            if (i > 0)
                builder.append('\n');
        }
        // append root causes and text from this exception first.
        if (getMessage() != null) {
            builder.append('\n');
            if (getCause() == null) {
                builder.append(getMessage());
            } else if (!getMessage().equals(getCause().toString())) {
                builder.append(getMessage());
            }
        }
        appendException(builder, getCause());
        return builder.toString();
    }

    /**
	 * Append exception.
	 *
	 * @param builder the builder
	 * @param throwable the throwable
	 */
    private void appendException(StringBuilder builder, Throwable throwable) {
        if (throwable == null)
            return;
        appendException(builder, throwable.getCause());
        builder.append(throwable.toString());
        builder.append('\n');
    }
}
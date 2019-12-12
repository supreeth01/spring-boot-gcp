package org.opensource.community.project.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.opensource.community.project.EmployeeConstants;
import org.opensource.community.project.EmployeeDoc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The Class EmployeeDTO.
 */
@JsonInclude(Include.NON_NULL)
public class DepartmentEmployeeDTO implements Serializable {

    @NotNull
    @Getter
    @Setter
    private Long employeeNo;

    @NotNull
    @Getter
    @Setter
    private Long departmentNo;

    @NotNull
    private Date fromDate;

    @NotNull
    private Date toDate;

    public DepartmentEmployeeDTO() {
    }

    public Date getFromDate() {
        return new Date(fromDate != null ? fromDate.getTime() : new Date().getTime());
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = new Date(fromDate != null ? fromDate.getTime() : new Date().getTime());
    }

    public Date getToDate() {
        return new Date(toDate != null ? toDate.getTime() : new Date().getTime());
    }

    public void setToDate(Date toDate) {
        this.toDate = new Date(toDate != null ? toDate.getTime() : new Date().getTime());
    }

    @Override
    public String toString() {
        return "DepartmentEmployee [" + "employeeNo=\"" + employeeNo + "\"," + "departmentNo=\"" + departmentNo + "\"," + " ]";
    }
}

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
public class DepartmentDTO implements Serializable {

    @Getter
    @Setter
    private Long id;

    @NotNull
    @Size(max = 40, message = "Department.Name max len: 40 min len: -1")
    @Getter
    @Setter
    private String name;

    public DepartmentDTO() {
    }

    @Override
    public String toString() {
        return "Department [" + "id=\"" + id + "\"," + "name=\"" + name + "\"," + " ]";
    }
}

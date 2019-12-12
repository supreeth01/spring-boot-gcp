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
public class EmployeeDTO implements Serializable {

    @NotNull
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private AddressDTO address;

    @Getter
    @Setter
    private DepartmentDTO department;

    private Date birthDate;

    @NotNull
    @Size(max = 14, message = "Employee.Name max len: 14 min len: -1")
    @Getter
    @Setter
    private String name;

    @Size(max = 16, message = "Employee.LastName max len: 16 min len: -1")
    @Getter
    @Setter
    private String lastName;

    @Size(max = 1, message = "Employee.Gender max len: 1 min len: -1")
    @Getter
    @Setter
    private String gender;

    private Date hireDate;

    public EmployeeDTO() {
    }

    public Date getBirthDate() {
        return new Date(birthDate != null ? birthDate.getTime() : new Date().getTime());
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = new Date(birthDate != null ? birthDate.getTime() : new Date().getTime());
    }

    public Date getHireDate() {
        return new Date(hireDate != null ? hireDate.getTime() : new Date().getTime());
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = new Date(hireDate != null ? hireDate.getTime() : new Date().getTime());
    }

    @Override
    public String toString() {
        return "Employee [" + "id=\"" + id + "\"," + "name=\"" + name + "\"," + "lastName=\"" + lastName + "\"," + "gender=\"" + gender + "\"," + " ]";
    }
}

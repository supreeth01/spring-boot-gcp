package org.opensource.community.project.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * The Class Employee.
 */
@Entity
@Table(name = "Employees.DepartmentEmployee")
public class DepartmentEmployee implements Serializable {

    @Id
    @Column(name = "EmployeeNo")
    @Getter
    @Setter
    private Long employeeNo;

    @Id
    @Column(name = "DepartmentNo")
    @Getter
    @Setter
    private Long departmentNo;

    @Column(name = "FromDate")
    private Date fromDate;

    @Column(name = "ToDate")
    private Date toDate;

    public DepartmentEmployee() {
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

    public String getIdPropertyName() {
        return "departmentNo";
    }

    public Class<?> getIdPropertyType() {
        return Long.class;
    }

    @Override
    public String toString() {
        return "DepartmentEmployee [" + "employeeNo=\"" + employeeNo + "\"," + "departmentNo=\"" + departmentNo + "\"," + " ]";
    }
}

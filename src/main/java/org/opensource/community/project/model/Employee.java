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
@Table(name = "Employees.Employee")
public class Employee implements Serializable {

    @Id
    @Column(name = "Id")
    @Getter
    @Setter
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "Address", referencedColumnName = "Id", unique = true)
    @Getter
    @Setter
    private Address address;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "Department", referencedColumnName = "Id")
    @Getter
    @Setter
    private Department department;

    @Column(name = "BirthDate")
    private Date birthDate;

    @Column(name = "Name")
    @Getter
    @Setter
    private String name;

    @Column(name = "LastName")
    @Getter
    @Setter
    private String lastName;

    @Column(name = "Gender")
    @Getter
    @Setter
    private String gender;

    @Column(name = "HireDate")
    private Date hireDate;

    public Employee() {
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

    public String getIdPropertyName() {
        return "id";
    }

    public Class<?> getIdPropertyType() {
        return Long.class;
    }

    @Override
    public String toString() {
        return "Employee [" + "id=\"" + id + "\"," + "name=\"" + name + "\"," + "lastName=\"" + lastName + "\"," + "gender=\"" + gender + "\"," + " ]";
    }
}

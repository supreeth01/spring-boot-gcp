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
@Table(name = "Employees.Department")
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    @Getter
    @Setter
    private Long id;

    @Column(name = "Name", unique = true)
    @Getter
    @Setter
    private String name;

    public Department() {
    }

    public String getIdPropertyName() {
        return "id";
    }

    public Class<?> getIdPropertyType() {
        return Long.class;
    }

    @Override
    public String toString() {
        return "Department [" + "id=\"" + id + "\"," + "name=\"" + name + "\"," + " ]";
    }
}

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
public class AddressDTO implements Serializable {

    @Getter
    @Setter
    private Long id;

    @Size(max = 45, message = "Address.AddressDetails max len: 45 min len: -1")
    @Getter
    @Setter
    private String addressDetails;

    @Size(max = 45, message = "Address.City max len: 45 min len: -1")
    @Getter
    @Setter
    private String city;

    @Size(max = 45, message = "Address.State max len: 45 min len: -1")
    @Getter
    @Setter
    private String state;

    public AddressDTO() {
    }

    @Override
    public String toString() {
        return "Address [" + "id=\"" + id + "\"," + "addressDetails=\"" + addressDetails + "\"," + "city=\"" + city + "\"," + "state=\"" + state + "\"," + " ]";
    }
}

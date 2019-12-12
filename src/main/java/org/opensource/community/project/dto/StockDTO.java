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
public class StockDTO implements Serializable {

    @NotNull
    @Getter
    @Setter
    private Long stockId;

    @NotNull
    @Size(max = 20, message = "Stock.StockName max len: 20 min len: -1")
    @Getter
    @Setter
    private String stockName;

    @NotNull
    @Size(max = 10, message = "Stock.StockCode max len: 10 min len: -1")
    @Getter
    @Setter
    private String stockCode;

    public StockDTO() {
    }

    @Override
    public String toString() {
        return "Stock [" + "stockId=\"" + stockId + "\"," + "stockName=\"" + stockName + "\"," + "stockCode=\"" + stockCode + "\"," + " ]";
    }
}

package com.hackerrank.stocktrade.ApplicationConfig;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@ToString
@Data
@EqualsAndHashCode(callSuper = false)
public class ApiResponse {
    @JsonProperty("data")
    private Object data;

    public ApiResponse(Object data) {
        this.data = data;
    }
}

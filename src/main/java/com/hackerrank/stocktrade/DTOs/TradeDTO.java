package com.hackerrank.stocktrade.DTOs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeDTO {
    private Long tradeId;
    private String type;
    private Long userId;
    private String symbol;
    private Integer shares;
    private Float price;
}

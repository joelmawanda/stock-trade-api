package com.hackerrank.stocktrade.model;


import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@ToString
@Entity(name = "trades")
@Table(name = "trades")
public class Trade {
    @Id
    @GeneratedValue
    private Long tradeId;
    private String type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id")
    private User user;
    private String symbol;
    private Integer shares;
    private Float price;

    @CreationTimestamp
    private Timestamp timestamp;
    
    public Trade() {
    }

    public Trade(Long tradeId, String type, User user, String symbol, Integer shares, Float price, Timestamp timestamp) {
        this.tradeId = tradeId;
        this.type = type;
        this.user = user;
        this.symbol = symbol;
        this.shares = shares;
        this.price = price;
        this.timestamp = timestamp;
    }
}

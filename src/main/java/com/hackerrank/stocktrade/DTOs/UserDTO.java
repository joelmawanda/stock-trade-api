package com.hackerrank.stocktrade.DTOs;

import com.hackerrank.stocktrade.model.Trade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long userId;
    private String name;
    private List<Trade> trades = new ArrayList<>();
}

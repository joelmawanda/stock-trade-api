package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.ApplicationConfig.ApiResponse;
import com.hackerrank.stocktrade.DTOs.TradeDTO;
import com.hackerrank.stocktrade.DTOs.UserDTO;
import com.hackerrank.stocktrade.exception.NoTradeFoundException;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;
import com.hackerrank.stocktrade.repository.UserRepository;
import com.hackerrank.stocktrade.service.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/trades")
public class TradesController {

    private final TradeService tradeService;

    public TradesController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> addANewTrade(@RequestBody TradeDTO tradeDTO){

        log.info("This is the user: " + tradeDTO);

        User user = userRepository.findById(tradeDTO.getUserId()).orElse(null);

        if (user == null) {
            throw new NoTradeFoundException("User ID does not exist");
        }
        Trade new_trade = tradeService.addANewTrade(tradeDTO.getType(), tradeDTO.getSymbol(), tradeDTO.getShares(), tradeDTO.getPrice(), user);

        return new ResponseEntity<>(new ApiResponse(new_trade), HttpStatus.CREATED);

    }


    @GetMapping("/{tradeId}")
    public ResponseEntity<?> getTradeById (@PathVariable("tradeId") @NotNull(message = "Id cannot be null") Long tradeId) throws NoTradeFoundException {
        TradeDTO trade = tradeService.getTradeById(tradeId);
        return new ResponseEntity<>(new ApiResponse(trade), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> listAllTradesWithSorting (Sort sort) {
        List<TradeDTO> trade = tradeService.listAllTradesWithSorting(sort);
        return new ResponseEntity<>(new ApiResponse(trade), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<?> addANewUser(@RequestBody User userDTO){

        User new_user = tradeService.addANewUser(userDTO);

        return new ResponseEntity<>(new ApiResponse(new_user), HttpStatus.CREATED);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getTradeByUserId (@PathVariable("userId") @NotNull(message = "Id cannot be null") Long userId) throws NoTradeFoundException {
        UserDTO user = tradeService.getTradeByUserId(userId);
        return new ResponseEntity<>(new ApiResponse(user), HttpStatus.OK);
    }

    @PutMapping("/users/update")
    public ResponseEntity<?> updateUser(@RequestParam("userId") @NotNull(message = "UserId cannot be null") Long userId, @RequestBody User user) {
            User updatedUser = tradeService.updateUser(userId, user);
            return new ResponseEntity<>(new ApiResponse(updatedUser), HttpStatus.OK);
    }
}

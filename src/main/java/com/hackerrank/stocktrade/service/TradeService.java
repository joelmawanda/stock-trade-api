package com.hackerrank.stocktrade.service;

import com.hackerrank.stocktrade.ApplicationConfig.ServiceUtils;
import com.hackerrank.stocktrade.DTOs.TradeDTO;
import com.hackerrank.stocktrade.DTOs.UserDTO;
import com.hackerrank.stocktrade.exception.NoTradeFoundException;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;
import com.hackerrank.stocktrade.repository.TradeRepository;
import com.hackerrank.stocktrade.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TradeService {

    private final TradeRepository tradeRepository;

    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceUtils serviceUtils;

    public void eraseAllTrades (){
        tradeRepository.deleteAll();
    }

    public Trade addANewTrade(String type, String symbol, Integer shares, Float price, User user) throws DuplicateKeyException {
        log.info("[Inside the addANewTrade method]: Adding a new trade");

        Trade newTrade = new Trade();
        newTrade.setType(type);
        newTrade.setSymbol(symbol);
        newTrade.setShares(shares);
        newTrade.setPrice(price);
        newTrade.setUser(user);
        log.info("New trade added successfully");
        return tradeRepository.save(newTrade);
    }

    public TradeDTO getTradeById(Long tradeId) throws NoTradeFoundException {
        log.info("[Inside the getTradeById method]: Retrieving trade with id " + tradeId + " from the database");
        Optional<Trade> optionalTrade = tradeRepository.findById(tradeId);

        Trade trade = optionalTrade.orElseThrow(() -> new NoTradeFoundException("ID does not exist"));

        // Convert Trade entity to TradeDTO using ModelMapper
        TradeDTO tradeDTO = modelMapper.map(trade, TradeDTO.class);

        return tradeDTO;
    }

    public List<TradeDTO> listAllTradesWithSorting(Sort sort) {
        List<Trade> trades = tradeRepository.findAll();
        return trades.stream().map(trade -> modelMapper.map(trade, TradeDTO.class))
                .collect(Collectors.toList());
    }

    public User addANewUser(User userDTO) throws DuplicateKeyException {
        log.info("[Inside the addANewUser method]: Adding a new user");
        log.info("New user added successfully");
        return userRepository.save(userDTO);
    }

    public UserDTO getTradeByUserId(Long userId) throws NoTradeFoundException {
        log.info("[Inside the getTradeByUserId method]: Retrieving trade with user id " + userId + " from the database");
        Optional<User> optionalUser = userRepository.findById(userId);

        User user = optionalUser.orElseThrow(() -> new NoTradeFoundException("ID does not exist"));

        // Convert Trade entity to TradeDTO using ModelMapper
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return userDTO;

    }

    public User updateUser(Long userId, User user) throws NoTradeFoundException {

        User oldUser = userRepository.findById(userId).orElse(null);

        if (oldUser == null) {
            throw new NoTradeFoundException("User id: " + userId + " does not exist");
        }

        //update the user object by copying the properties from the new object to the old object
        serviceUtils.copyNonNullProperties(user, oldUser);

        return userRepository.save(oldUser);

    }

}


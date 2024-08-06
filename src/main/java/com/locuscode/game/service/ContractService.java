package com.locuscode.game.service;

import com.locuscode.game.graphql.data.UsersData;
import com.locuscode.game.persistence.model.ClaimedEvent;
import com.locuscode.game.persistence.model.StakedEvent;

import java.util.List;

public interface ContractService {
    List<StakedEvent> getStakedEventsByUser(String user);
    List<ClaimedEvent> getClaimedEventsByUsername(String user);
    UsersData getAllDataFromUser(String user);
}

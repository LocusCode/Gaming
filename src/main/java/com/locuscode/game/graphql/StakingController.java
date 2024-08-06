package com.locuscode.game.graphql;

import com.locuscode.game.graphql.data.UsersData;
import com.locuscode.game.persistence.model.ClaimedEvent;
import com.locuscode.game.persistence.model.StakedEvent;
import com.locuscode.game.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class StakingController {

    @Autowired
    private ContractService contractService;

    @QueryMapping
    public List<StakedEvent> stakedTokens(@Argument String publicAddress) {
        return contractService.getStakedEventsByUser(publicAddress);
    }

    @QueryMapping
    public List<ClaimedEvent> stakingRewards(@Argument String publicAddress) {
        return contractService.getClaimedEventsByUsername(publicAddress);
    }

    @QueryMapping
    public UsersData usersData(@Argument String publicAddress) {
        return contractService.getAllDataFromUser(publicAddress);
    }
}


package com.locuscode.game.service.impl;

import com.locuscode.game.contract.Staking;
import com.locuscode.game.graphql.data.UsersData;
import com.locuscode.game.persistence.model.ClaimedEvent;
import com.locuscode.game.persistence.model.StakedEvent;
import com.locuscode.game.persistence.repository.ClaimedEventRepository;
import com.locuscode.game.persistence.repository.StakedEventRepository;
import com.locuscode.game.service.ContractService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.tx.gas.DefaultGasProvider;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private Web3j web3j;

    @Autowired
    private Credentials creds;

    @Autowired
    private StakedEventRepository stakedEventRepository;

    @Autowired
    private ClaimedEventRepository claimedEventRepository;

    private final String STAKING_CONTRACT_ADDRESS = "0xe7f1725E7734CE288F8367e1Bb143E90bb3F0512";
    //private final String STAKING_TOKEN_ADDRESS = "0x5FbDB2315678afecb367f032d93F642f64180aa3"; //Code to manage the token if I would like to

    @PostConstruct
    public void init() {
        listenToEvents();
    }

    public void listenToEvents() {
        //StakingToken token = StakingToken.load(STAKING_TOKEN_ADDRESS, web3j, creds, new DefaultGasProvider()); //If I would like to get the token infos as well.
        Staking staking = Staking.load(STAKING_CONTRACT_ADDRESS, web3j, creds, new DefaultGasProvider());

        staking.stakedEventFlowable(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST)
                .subscribe(event -> {
                    StakedEvent stakedEvent = new StakedEvent();
                    stakedEvent.setUsername(event.user);
                    stakedEvent.setAmount(event.amount.toString());

                    StakedEvent x = stakedEventRepository.save(stakedEvent);
                    System.out.println(x.getUsername());
                });

        staking.claimedEventFlowable(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST)
                .subscribe(event -> {
                    ClaimedEvent claimedEvent = new ClaimedEvent();
                    claimedEvent.setUsername(event.user);
                    claimedEvent.setReward(event.reward.toString());
                    claimedEventRepository.save(claimedEvent);
                });
    }

    public List<StakedEvent> getStakedEventsByUser(String user) {
        return stakedEventRepository.findByUsername(user.toLowerCase());
    }

    public List<ClaimedEvent> getClaimedEventsByUsername(String user) {
        return claimedEventRepository.findByUsername(user.toLowerCase());
    }

    public UsersData getAllDataFromUser(String user){
        List<StakedEvent> stakedEvents = getStakedEventsByUser(user);
        List<ClaimedEvent> claimedEvents = getClaimedEventsByUsername(user);
        return UsersData.builder()
                .claimedEvents(claimedEvents)
                .stakedEvents(stakedEvents)
                .build();
    }
}

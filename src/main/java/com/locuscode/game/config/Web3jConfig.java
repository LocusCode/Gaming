package com.locuscode.game.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class Web3jConfig {
    @Bean
    public Web3j web3j() {
        return Web3j.build(new HttpService("http://localhost:8545"));
    }

    @Bean
    public Credentials creds(){
       return  Credentials.create("0xac0974bec39a17e36ba4a6b4d238ff944bacb478cbed5efcae784d7bf4f2ff80");
    }
}

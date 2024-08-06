# Overview

This documentation provides an overview of the Staking.sol smart contract and the accompanying Java code used to interact with it. It includes a basic understanding of how the smart contract works and how the Java code integrates with it.

## Staking.sol
Overview

The Staking.sol smart contract is an Ethereum smart contract written in Solidity that enables users to stake ERC20 tokens and earn rewards. It includes functionality for staking tokens, claiming rewards, and calculating rewards based on the staking duration.

Key Components

State Variables

IERC20 public stakingToken: The ERC20 token that users will stake.
uint256 public rewardRate: The reward rate for staking, expressed as a percentage.
uint256 public minStakingPeriod: The minimum period a user must stake tokens before they can claim rewards, specified in seconds.
mapping(address => Stake) public stakes: Stores the amount and start time of each user's stake.
mapping(address => uint256) public rewards: Tracks the total rewards accumulated by each user.
Structs

struct Stake: Contains amount (the staked amount) and startTime (the timestamp when the staking started).
Events

event Staked: Emitted when a user stakes tokens.
event Claimed: Emitted when a user claims rewards.
Constructor

Initializes the contract with the address of the staking token.
Functions

- stake(uint256 _amount): Allows users to stake a specified amount of tokens.
- claim(): Allows users to claim their staked tokens and any earned rewards after the minimum staking period.
- calculateReward(address _user): Calculates the reward for a given user based on their staking duration.

### How It Works
Staking: Users call the stake function to deposit tokens into the contract. The contract records the amount staked and the start time.

Claiming Rewards: After the minimum staking period, users can call the claim function to retrieve their staked tokens along with any accumulated rewards.

Reward Calculation: The calculateReward function provides a way to estimate rewards based on the duration of staking.


## Java Code
Overview

The Java code interacts with the Staking.sol smart contract to manage staking data. It includes functionality for querying staked tokens, staking rewards, and user data using GraphQL.

Key Components
Dependencies

- web3j: A Java library used for interacting with Ethereum smart contracts.
- graphql-java: A Java library for implementing GraphQL.

### GraphQL Configuration

Schema Definition: Defines the GraphQL schema including queries for stakedTokens, stakingRewards, and usersData.
Resolvers: Implement the logic to fetch data from the smart contract and return it in response to GraphQL queries.
Service Class

ContractService: Handles interactions with the Ethereum smart contract using web3j. It includes methods to fetch staked events, claimed rewards, and all users' data.
GraphQL Resolver

StakingResolver: Maps GraphQL queries to methods in the ContractService class. It provides data for staked tokens, staking rewards, and overall user data.

### How It Works
GraphQL Endpoint: The Spring Boot application exposes a GraphQL endpoint where clients can query data.

Service Integration: The ContractService class interacts with the smart contract to retrieve data. It listens for contract events and performs queries.

Resolvers: Handle GraphQL queries and map them to the appropriate service methods, returning the requested data to the client.

### Accessing the GraphQL Endpoint

URL: The GraphQL endpoint is available at http://localhost:8080/graphql.

### Queries:
stakedTokens(publicAddress: String): Returns the staked tokens for a given address.

stakingRewards(publicAddress: String): Returns the staking rewards for a given address.

usersData: Returns the staked tokens and rewards for all users.

### Example Query

```
query {
    stakedTokens(publicAddress: "0xf39Fd6e51aad88F6F4ce6aB8827279cffFb92266") {
        username
        amount
    }
}
```

## Points to be noted

Since it is using h2db which is an in memory database, everytime we load the application
we read the blockchain from the begining and start populating the database.

Once we do it in real life, we should check the database for what we already have there.

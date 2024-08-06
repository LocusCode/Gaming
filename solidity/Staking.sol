// SPDX-License-Identifier: MIT
pragma solidity ^0.8.21;

import "@openzeppelin/contracts/token/ERC20/IERC20.sol";
import "@openzeppelin/contracts/access/Ownable.sol";

contract Staking is Ownable {
    IERC20 public stakingToken;
    uint256 public rewardRate = 100;
    uint256 public minStakingPeriod = 1 weeks; //We can set the timing we need
    address public _owner;
    struct Stake {
        uint256 amount;
        uint256 startTime;
    }

    mapping(address => Stake) public stakes;
    mapping(address => uint256) public rewards;

    event Staked(address indexed user, uint256 amount);
    event Claimed(address indexed user, uint256 reward);

    constructor(IERC20 _stakingToken, address initialOwner) Ownable(initialOwner){
        stakingToken = _stakingToken;
        _owner = initialOwner;
    }

    function stake(uint256 _amount) external {
        require(_amount > 0, "Cannot stake 0 tokens");

        stakes[msg.sender] = Stake({
            amount: _amount,
            startTime: block.timestamp
        });

        stakingToken.transferFrom(msg.sender, address(this), _amount);
        emit Staked(msg.sender, _amount);
    }

    function claim() external {
        Stake storage userStake = stakes[msg.sender];
        require(userStake.amount > 0, "No tokens staked");
        require(block.timestamp >= userStake.startTime + minStakingPeriod, "Staking period not met");

        uint256 reward = userStake.amount * rewardRate / 100;
        rewards[msg.sender] += reward;

        stakingToken.transfer(msg.sender, userStake.amount);
        userStake.amount = 0;

        emit Claimed(msg.sender, reward);
    }

    function calculateReward(address _user) external view returns (uint256) {
        Stake storage userStake = stakes[_user];
        if (userStake.amount == 0) return 0;

        uint256 stakingDuration = block.timestamp - userStake.startTime;
        return userStake.amount * rewardRate * stakingDuration / (100 * 365 days);
    }
}

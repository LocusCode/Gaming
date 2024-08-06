package com.locuscode.game.contract;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.6.0.
 */
@SuppressWarnings("rawtypes")
public class Staking extends Contract {
    public static final String BINARY = "0x6080604052606460025562093a806003553480156200001d57600080fd5b5060405162001233380380620012338339818101604052810190620000439190620002c8565b80600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1603620000b95760006040517f1e4fbdf7000000000000000000000000000000000000000000000000000000008152600401620000b0919062000320565b60405180910390fd5b620000ca816200015560201b60201c565b5081600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080600460006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050506200033d565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508173ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a35050565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60006200024b826200021e565b9050919050565b60006200025f826200023e565b9050919050565b620002718162000252565b81146200027d57600080fd5b50565b600081519050620002918162000266565b92915050565b620002a2816200023e565b8114620002ae57600080fd5b50565b600081519050620002c28162000297565b92915050565b60008060408385031215620002e257620002e162000219565b5b6000620002f28582860162000280565b92505060206200030585828601620002b1565b9150509250929050565b6200031a816200023e565b82525050565b60006020820190506200033760008301846200030f565b92915050565b610ee6806200034d6000396000f3fe608060405234801561001057600080fd5b50600436106100b45760003560e01c80637b0a47ee116100715780637b0a47ee1461016a5780638da5cb5b14610188578063a694fc3a146101a6578063b2bdfa7b146101c2578063d82e3962146101e0578063f2fde38b14610210576100b4565b8063069c34f6146100b95780630700037d146100d757806316934fc4146101075780634e71d92d14610138578063715018a61461014257806372f702f31461014c575b600080fd5b6100c161022c565b6040516100ce919061098c565b60405180910390f35b6100f160048036038101906100ec9190610a0a565b610232565b6040516100fe919061098c565b60405180910390f35b610121600480360381019061011c9190610a0a565b61024a565b60405161012f929190610a37565b60405180910390f35b61014061026e565b005b61014a6104c5565b005b6101546104d9565b6040516101619190610abf565b60405180910390f35b6101726104ff565b60405161017f919061098c565b60405180910390f35b610190610505565b60405161019d9190610ae9565b60405180910390f35b6101c060048036038101906101bb9190610b30565b61052e565b005b6101ca6106d0565b6040516101d79190610ae9565b60405180910390f35b6101fa60048036038101906101f59190610a0a565b6106f6565b604051610207919061098c565b60405180910390f35b61022a60048036038101906102259190610a0a565b61079a565b005b60035481565b60066020528060005260406000206000915090505481565b60056020528060005260406000206000915090508060000154908060010154905082565b6000600560003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020905060008160000154116102f8576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016102ef90610bba565b60405180910390fd5b600354816001015461030a9190610c09565b42101561034c576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161034390610c89565b60405180910390fd5b6000606460025483600001546103629190610ca9565b61036c9190610d1a565b905080600660003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008282546103bd9190610c09565b92505081905550600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663a9059cbb3384600001546040518363ffffffff1660e01b8152600401610425929190610d4b565b6020604051808303816000875af1158015610444573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906104689190610dac565b50600082600001819055503373ffffffffffffffffffffffffffffffffffffffff167fd8138f8a3f377c5259ca548e70e4c2de94f129f5a11036a15b69513cba2b426a826040516104b9919061098c565b60405180910390a25050565b6104cd610820565b6104d760006108a7565b565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60025481565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b60008111610571576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161056890610e25565b60405180910390fd5b604051806040016040528082815260200142815250600560003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000820151816000015560208201518160010155905050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166323b872dd3330846040518463ffffffff1660e01b815260040161063b93929190610e45565b6020604051808303816000875af115801561065a573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061067e9190610dac565b503373ffffffffffffffffffffffffffffffffffffffff167f9e71bc8eea02a63969f509818f2dafb9254532904319f9dbda79b67bd34a5f3d826040516106c5919061098c565b60405180910390a250565b600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600080600560008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002090506000816000015403610750576000915050610795565b60008160010154426107629190610e7c565b905063bbf81e0081600254846000015461077c9190610ca9565b6107869190610ca9565b6107909190610d1a565b925050505b919050565b6107a2610820565b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16036108145760006040517f1e4fbdf700000000000000000000000000000000000000000000000000000000815260040161080b9190610ae9565b60405180910390fd5b61081d816108a7565b50565b61082861096b565b73ffffffffffffffffffffffffffffffffffffffff16610846610505565b73ffffffffffffffffffffffffffffffffffffffff16146108a55761086961096b565b6040517f118cdaa700000000000000000000000000000000000000000000000000000000815260040161089c9190610ae9565b60405180910390fd5b565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508173ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a35050565b600033905090565b6000819050919050565b61098681610973565b82525050565b60006020820190506109a1600083018461097d565b92915050565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60006109d7826109ac565b9050919050565b6109e7816109cc565b81146109f257600080fd5b50565b600081359050610a04816109de565b92915050565b600060208284031215610a2057610a1f6109a7565b5b6000610a2e848285016109f5565b91505092915050565b6000604082019050610a4c600083018561097d565b610a59602083018461097d565b9392505050565b6000819050919050565b6000610a85610a80610a7b846109ac565b610a60565b6109ac565b9050919050565b6000610a9782610a6a565b9050919050565b6000610aa982610a8c565b9050919050565b610ab981610a9e565b82525050565b6000602082019050610ad46000830184610ab0565b92915050565b610ae3816109cc565b82525050565b6000602082019050610afe6000830184610ada565b92915050565b610b0d81610973565b8114610b1857600080fd5b50565b600081359050610b2a81610b04565b92915050565b600060208284031215610b4657610b456109a7565b5b6000610b5484828501610b1b565b91505092915050565b600082825260208201905092915050565b7f4e6f20746f6b656e73207374616b656400000000000000000000000000000000600082015250565b6000610ba4601083610b5d565b9150610baf82610b6e565b602082019050919050565b60006020820190508181036000830152610bd381610b97565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b6000610c1482610973565b9150610c1f83610973565b9250828201905080821115610c3757610c36610bda565b5b92915050565b7f5374616b696e6720706572696f64206e6f74206d657400000000000000000000600082015250565b6000610c73601683610b5d565b9150610c7e82610c3d565b602082019050919050565b60006020820190508181036000830152610ca281610c66565b9050919050565b6000610cb482610973565b9150610cbf83610973565b9250828202610ccd81610973565b91508282048414831517610ce457610ce3610bda565b5b5092915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601260045260246000fd5b6000610d2582610973565b9150610d3083610973565b925082610d4057610d3f610ceb565b5b828204905092915050565b6000604082019050610d606000830185610ada565b610d6d602083018461097d565b9392505050565b60008115159050919050565b610d8981610d74565b8114610d9457600080fd5b50565b600081519050610da681610d80565b92915050565b600060208284031215610dc257610dc16109a7565b5b6000610dd084828501610d97565b91505092915050565b7f43616e6e6f74207374616b65203020746f6b656e730000000000000000000000600082015250565b6000610e0f601583610b5d565b9150610e1a82610dd9565b602082019050919050565b60006020820190508181036000830152610e3e81610e02565b9050919050565b6000606082019050610e5a6000830186610ada565b610e676020830185610ada565b610e74604083018461097d565b949350505050565b6000610e8782610973565b9150610e9283610973565b9250828203905081811115610eaa57610ea9610bda565b5b9291505056fea2646970667358221220e7b6c130536c3879aff5f54224aa3a3da81d11ce57a1e485f4dec79df682236a64736f6c63430008180033";

    private static String librariesLinkedBinary;

    public static final String FUNC__OWNER = "_owner";

    public static final String FUNC_CALCULATEREWARD = "calculateReward";

    public static final String FUNC_CLAIM = "claim";

    public static final String FUNC_MINSTAKINGPERIOD = "minStakingPeriod";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_REWARDRATE = "rewardRate";

    public static final String FUNC_REWARDS = "rewards";

    public static final String FUNC_STAKE = "stake";

    public static final String FUNC_STAKES = "stakes";

    public static final String FUNC_STAKINGTOKEN = "stakingToken";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final Event CLAIMED_EVENT = new Event("Claimed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event STAKED_EVENT = new Event("Staked", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected Staking(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Staking(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Staking(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Staking(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<ClaimedEventResponse> getClaimedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(CLAIMED_EVENT, transactionReceipt);
        ArrayList<ClaimedEventResponse> responses = new ArrayList<ClaimedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ClaimedEventResponse typedResponse = new ClaimedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.reward = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ClaimedEventResponse getClaimedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(CLAIMED_EVENT, log);
        ClaimedEventResponse typedResponse = new ClaimedEventResponse();
        typedResponse.log = log;
        typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.reward = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<ClaimedEventResponse> claimedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getClaimedEventFromLog(log));
    }

    public Flowable<ClaimedEventResponse> claimedEventFlowable(DefaultBlockParameter startBlock,
            DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CLAIMED_EVENT));
        return claimedEventFlowable(filter);
    }

    public static List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static OwnershipTransferredEventResponse getOwnershipTransferredEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
        OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
        typedResponse.log = log;
        typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getOwnershipTransferredEventFromLog(log));
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public static List<StakedEventResponse> getStakedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(STAKED_EVENT, transactionReceipt);
        ArrayList<StakedEventResponse> responses = new ArrayList<StakedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            StakedEventResponse typedResponse = new StakedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static StakedEventResponse getStakedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(STAKED_EVENT, log);
        StakedEventResponse typedResponse = new StakedEventResponse();
        typedResponse.log = log;
        typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<StakedEventResponse> stakedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getStakedEventFromLog(log));
    }

    public Flowable<StakedEventResponse> stakedEventFlowable(DefaultBlockParameter startBlock,
            DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(STAKED_EVENT));
        return stakedEventFlowable(filter);
    }

    public RemoteFunctionCall<String> _owner() {
        final Function function = new Function(FUNC__OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> calculateReward(String _user) {
        final Function function = new Function(FUNC_CALCULATEREWARD, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _user)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> claim() {
        final Function function = new Function(
                FUNC_CLAIM, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> minStakingPeriod() {
        final Function function = new Function(FUNC_MINSTAKINGPERIOD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final Function function = new Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> rewardRate() {
        final Function function = new Function(FUNC_REWARDRATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> rewards(String param0) {
        final Function function = new Function(FUNC_REWARDS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> stake(BigInteger _amount) {
        final Function function = new Function(
                FUNC_STAKE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> stakes(String param0) {
        final Function function = new Function(FUNC_STAKES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple2<BigInteger, BigInteger>>(function,
                new Callable<Tuple2<BigInteger, BigInteger>>() {
                    @Override
                    public Tuple2<BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<String> stakingToken() {
        final Function function = new Function(FUNC_STAKINGTOKEN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Staking load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new Staking(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Staking load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Staking(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Staking load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new Staking(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Staking load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Staking(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Staking> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider, String _stakingToken, String initialOwner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _stakingToken), 
                new org.web3j.abi.datatypes.Address(160, initialOwner)));
        return deployRemoteCall(Staking.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<Staking> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider, String _stakingToken, String initialOwner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _stakingToken), 
                new org.web3j.abi.datatypes.Address(160, initialOwner)));
        return deployRemoteCall(Staking.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Staking> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit, String _stakingToken, String initialOwner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _stakingToken), 
                new org.web3j.abi.datatypes.Address(160, initialOwner)));
        return deployRemoteCall(Staking.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Staking> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit, String _stakingToken, String initialOwner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _stakingToken), 
                new org.web3j.abi.datatypes.Address(160, initialOwner)));
        return deployRemoteCall(Staking.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

    public static class ClaimedEventResponse extends BaseEventResponse {
        public String user;

        public BigInteger reward;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class StakedEventResponse extends BaseEventResponse {
        public String user;

        public BigInteger amount;
    }
}

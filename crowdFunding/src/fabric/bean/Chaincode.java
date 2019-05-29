package fabric.bean;

/**
 * Fabric������chaincode��Ϣ����������channel����Ϣ
 * 
 * @author aberic
 *
 * @date 2017��10��18�� - ����2:07:42
 * @email abericyang@gmail.com
 */
public class Chaincode {

    /** ��ǰ��Ҫ���ʵ����ܺ�Լ����Ƶ������ */
    private String channelName; // ffetest
    /** ���ܺ�Լ���� */
    private String chaincodeName; // ffetestcc
    /** ���ܺ�Լ��װ·�� */
    private String chaincodePath; // github.com/hyperledger/fabric/xxx/chaincode/go/example/test
    /** ���ܺ�Լ�汾�� */
    private String chaincodeVersion; // 1.0
    /** ִ�����ܺ�Լ�����ȴ�ʱ�� */
    private int invokeWatiTime = 100000;
    /** ִ�����ܺ�Լʵ���ȴ�ʱ�� */
    private int deployWatiTime = 120000;

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChaincodeName() {
        return chaincodeName;
    }

    public void setChaincodeName(String chaincodeName) {
        this.chaincodeName = chaincodeName;
    }

    public String getChaincodePath() {
        return chaincodePath;
    }

    public void setChaincodePath(String chaincodePath) {
        this.chaincodePath = chaincodePath;
    }

    public String getChaincodeVersion() {
        return chaincodeVersion;
    }

    public void setChaincodeVersion(String chaincodeVersion) {
        this.chaincodeVersion = chaincodeVersion;
    }

    public int getInvokeWatiTime() {
        return invokeWatiTime;
    }

    public void setInvokeWatiTime(int invokeWatiTime) {
        this.invokeWatiTime = invokeWatiTime;
    }

    public int getDeployWatiTime() {
        return deployWatiTime;
    }

    public void setDeployWatiTime(int deployWatiTime) {
        this.deployWatiTime = deployWatiTime;
    }

}
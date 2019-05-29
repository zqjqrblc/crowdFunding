package fabric;

import java.io.File;

import org.apache.log4j.Logger;

import fabric.bean.Chaincode;
import fabric.bean.Orderers;
import fabric.bean.Peers;

public class FabricConfig {

    private static Logger log = Logger.getLogger(FabricConfig.class);

    /** �ڵ���������� */
    private Peers peers;
    /** ������������� */
    private Orderers orderers;
    /** ���ܺ�Լ���� */
    private Chaincode chaincode;
    /** channel-artifacts����·����Ĭ��channel-artifacts����·��/xxx/WEB-INF/classes/fabric/channel-artifacts/ */
    private String channelArtifactsPath;
    /** crypto-config����·����Ĭ��crypto-config����·��/xxx/WEB-INF/classes/fabric/crypto-config/ */
    private String cryptoConfigPath;
    private boolean registerEvent = false;

    public FabricConfig() {
        // Ĭ��channel-artifacts����·�� /xxx/WEB-INF/classes/fabric/channel-artifacts/
        channelArtifactsPath = getChannlePath() + "/channel-artifacts/";
        // Ĭ��crypto-config����·�� /xxx/WEB-INF/classes/fabric/crypto-config/
        cryptoConfigPath = getChannlePath() + "/crypto-config/";
    }

    /**
     * Ĭ��fabric����·��
     * 
     * @return D:/installSoft/apache-tomcat-9.0.0.M21-02/webapps/xxx/WEB-INF/classes/fabric/channel-artifacts/
     */
    private String getChannlePath() {
        String directorys = ChaincodeManager.class.getClassLoader().getResource("fabric").getFile();
        log.debug("directorys = " + directorys);
        File directory = new File(directorys);
        log.debug("directory = " + directory.getPath());

        return directory.getPath();
        // return "src/main/resources/fabric/channel-artifacts/";
    }

    public Peers getPeers() {
        return peers;
    }

    public void setPeers(Peers peers) {
        this.peers = peers;
    }

    public Orderers getOrderers() {
        return orderers;
    }

    public void setOrderers(Orderers orderers) {
        this.orderers = orderers;
    }

    public Chaincode getChaincode() {
        return chaincode;
    }

    public void setChaincode(Chaincode chaincode) {
        this.chaincode = chaincode;
    }

    public String getChannelArtifactsPath() {
        return channelArtifactsPath;
    }

    public void setChannelArtifactsPath(String channelArtifactsPath) {
        this.channelArtifactsPath = channelArtifactsPath;
    }

    public String getCryptoConfigPath() {
        return cryptoConfigPath;
    }

    public void setCryptoConfigPath(String cryptoConfigPath) {
        this.cryptoConfigPath = cryptoConfigPath;
    }

    public boolean isRegisterEvent() {
        return registerEvent;
    }

    public void setRegisterEvent(boolean registerEvent) {
        this.registerEvent = registerEvent;
    }

}
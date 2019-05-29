package utils;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.TransactionException;

import fabric.ChaincodeManager;
import fabric.FabricConfig;
import fabric.bean.Chaincode;
import fabric.bean.Orderers;
import fabric.bean.Peers;

public class FabricManager {

    private ChaincodeManager manager;

    private static FabricManager instance = null;

    public static FabricManager obtain()
            throws CryptoException, InvalidArgumentException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, TransactionException, IOException {
        if (null == instance) {
            synchronized (FabricManager.class) {
                if (null == instance) {
                    instance = new FabricManager();
                }
            }
        }
        return instance;
    }

    private FabricManager()
            throws CryptoException, InvalidArgumentException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, TransactionException, IOException {
        manager = new ChaincodeManager(getConfig());
    }

    /**
     * ��ȡ�ڵ������������
     * 
     * @return �ڵ������������
     */
    public ChaincodeManager getManager() {
        return manager;
    }

    /**
     * ���ݽڵ��������ͻ�ȡ�ڵ����������
     * 
     * @param type
     *            �������������ͣ�1��ִ�У�2����ѯ��
     * @return �ڵ����������
     */
    private FabricConfig getConfig() {
        FabricConfig config = new FabricConfig();
        config.setOrderers(getOrderers());
        config.setPeers(getPeers());
        config.setChaincode(getChaincode("xxx", "xxxcc", "github.com/hyperledger/fabric/chaincode/go/release/xxx", "1.0"));
        config.setChannelArtifactsPath(getChannleArtifactsPath());
        config.setCryptoConfigPath(getCryptoConfigPath());
        return config;
    }

    private Orderers getOrderers() {
        Orderers orderer = new Orderers();
        orderer.setOrdererDomainName("example.com");
        orderer.addOrderer("orderer1.example.com", "grpc://x.x.x.x:7050");
        orderer.addOrderer("orderer0.example.com", "grpc://x.x.x.xx:7050");
        orderer.addOrderer("orderer2.example.com", "grpc://x.x.x.xxx:7050");
        return orderer;
    }

    /**
     * ��ȡ�ڵ��������
     * 
     * @return �ڵ��������
     */
    private Peers getPeers() {
        Peers peers = new Peers();
        peers.setOrgName("XXX");
        peers.setOrgMSPID("XXXMSP");
        peers.setOrgDomainName("xxx.example.com");
        peers.addPeer("peer1.xxx.example.com", "peer1.xxx.example.com", "grpc://x.x.x.x:7051", "grpc://x.x.x.x:7053", "http://x.x.x.x:7054");
        return peers;
    }

    /**
     * ��ȡ���ܺ�Լ
     * 
     * @param channelName
     *            Ƶ������
     * @param chaincodeName
     *            ���ܺ�Լ����
     * @param chaincodePath
     *            ���ܺ�Լ·��
     * @param chaincodeVersion
     *            ���ܺ�Լ�汾
     * @return ���ܺ�Լ
     */
    private Chaincode getChaincode(String channelName, String chaincodeName, String chaincodePath, String chaincodeVersion) {
        Chaincode chaincode = new Chaincode();
        chaincode.setChannelName(channelName);
        chaincode.setChaincodeName(chaincodeName);
        chaincode.setChaincodePath(chaincodePath);
        chaincode.setChaincodeVersion(chaincodeVersion);
        chaincode.setInvokeWatiTime(100000);
        chaincode.setDeployWatiTime(120000);
        return chaincode;
    }

    /**
     * ��ȡchannel-artifacts����·��
     * 
     * @return /WEB-INF/classes/fabric/channel-artifacts/
     */
    private String getChannleArtifactsPath() {
        String directorys = FabricManager.class.getClassLoader().getResource("fabric").getFile();
        //log.debug("directorys = " + directorys);
        File directory = new File(directorys);
        //log.debug("directory = " + directory.getPath());

        return directory.getPath() + "/channel-artifacts/";
    }

    /**
     * ��ȡcrypto-config����·��
     * 
     * @return /WEB-INF/classes/fabric/crypto-config/
     */
    private String getCryptoConfigPath() {
        String directorys = FabricManager.class.getClassLoader().getResource("fabric").getFile();
        //log.debug("directorys = " + directorys);
        File directory = new File(directorys);
        //log.debug("directory = " + directory.getPath());

        return directory.getPath() + "/crypto-config/";
    }

}
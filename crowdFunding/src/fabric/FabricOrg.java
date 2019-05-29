package fabric;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hyperledger.fabric.sdk.Peer;
import org.hyperledger.fabric.sdk.User;
import org.hyperledger.fabric_ca.sdk.HFCAClient;

import fabric.bean.Orderers;

/**
 * ������֯����
 * 
 * @author aberic
 *
 * @date 2017��9��7�� - ����4:35:40
 * @email abericyang@gmail.com
 */
class FabricOrg {

    private static Logger log = Logger.getLogger(FabricOrg.class);

    /** ���� */
    private String name;
    /** ��Աid */
    private String mspid;
    /** ca �ͻ��� */
    private HFCAClient caClient;

    /** �û����� */
    Map<String, User> userMap = new HashMap<>();
    /** ���ؽڵ㼯�� */
    Map<String, String> peerLocations = new HashMap<>();
    /** ����������񼯺� */
    Map<String, String> ordererLocations = new HashMap<>();
    /** �����¼����� */
    Map<String, String> eventHubLocations = new HashMap<>();
    /** �ڵ㼯�� */
    Set<Peer> peers = new HashSet<>();
    /** ���˹���Ա�û� */
    private FabricUser admin;
    /** ���� ca */
    private String caLocation;
    /** ca ���� */
    private Properties caProperties = null;

    /** ���˵��ڵ����Ա�û� */
    private FabricUser peerAdmin;

    /** �������� */
    private String domainName;

    public FabricOrg(fabric.bean.Peers peers, Orderers orderers, FabricStore fabricStore, String cryptoConfigPath)
            throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, IOException {
        this.name = peers.getOrgName();
        this.mspid = peers.getOrgMSPID();
        for (int i = 0; i < peers.get().size(); i++) {
            addPeerLocation(peers.get().get(i).getPeerName(), peers.get().get(i).getPeerLocation());
            addEventHubLocation(peers.get().get(i).getPeerEventHubName(), peers.get().get(i).getPeerEventHubLocation());
            setCALocation(peers.get().get(i).getCaLocation());
        }
        for (int i = 0; i < orderers.get().size(); i++) {
            addOrdererLocation(orderers.get().get(i).getOrdererName(), orderers.get().get(i).getOrdererLocation());
        }
        setDomainName(peers.getOrgDomainName()); // domainName=tk.anti-moth.com

        // Set up HFCA for Org1
        // setCAClient(HFCAClient.createNewInstance(peers.getCaLocation(), getCAProperties()));

        setAdmin(fabricStore.getMember("admin", peers.getOrgName())); // ���ø���֯�Ĺ���Ա

        File skFile = Paths.get(cryptoConfigPath, "/peerOrganizations/", peers.getOrgDomainName(), String.format("/users/Admin@%s/msp/keystore", peers.getOrgDomainName())).toFile();
        File certificateFile = Paths.get(cryptoConfigPath, "/peerOrganizations/", peers.getOrgDomainName(),
                String.format("/users/Admin@%s/msp/signcerts/Admin@%s-cert.pem", peers.getOrgDomainName(), peers.getOrgDomainName())).toFile();
        log.debug("skFile = " + skFile.getAbsolutePath());
        log.debug("certificateFile = " + certificateFile.getAbsolutePath());
        setPeerAdmin(fabricStore.getMember(peers.getOrgName() + "Admin", peers.getOrgName(), peers.getOrgMSPID(), findFileSk(skFile), certificateFile)); // һ��������û������Դ���ͨ�������ӶԵȵ㣬����װ����
    }

    public String getName() {
        return name;
    }

    /**
     * ��ȡ���˹���Ա�û�
     * 
     * @return ���˹���Ա�û�
     */
    public FabricUser getAdmin() {
        return admin;
    }

    /**
     * �������˹���Ա�û�
     * 
     * @param admin
     *            ���˹���Ա�û�
     */
    public void setAdmin(FabricUser admin) {
        this.admin = admin;
    }

    /**
     * ��ȡ��Աid
     * 
     * @return ��Աid
     */
    public String getMSPID() {
        return mspid;
    }

    /**
     * ���ñ���ca
     * 
     * @param caLocation
     *            ����ca
     */
    public void setCALocation(String caLocation) {
        this.caLocation = caLocation;
    }

    /**
     * ��ȡ����ca
     * 
     * @return ����ca
     */
    public String getCALocation() {
        return this.caLocation;
    }

    /**
     * ��ӱ��ؽڵ�
     * 
     * @param name
     *            �ڵ�key
     * @param location
     *            �ڵ�
     */
    public void addPeerLocation(String name, String location) {
        peerLocations.put(name, location);
    }

    /**
     * ��ӱ�����֯
     * 
     * @param name
     *            ��֯key
     * @param location
     *            ��֯
     */
    public void addOrdererLocation(String name, String location) {
        ordererLocations.put(name, location);
    }

    /**
     * ��ӱ����¼�
     * 
     * @param name
     *            �¼�key
     * @param location
     *            �¼�
     */
    public void addEventHubLocation(String name, String location) {
        eventHubLocations.put(name, location);
    }

    /**
     * ��ȡ���ؽڵ�
     * 
     * @param name
     *            �ڵ�key
     * @return �ڵ�
     */
    public String getPeerLocation(String name) {
        return peerLocations.get(name);
    }

    /**
     * ��ȡ������֯
     * 
     * @param name
     *            ��֯key
     * @return ��֯
     */
    public String getOrdererLocation(String name) {
        return ordererLocations.get(name);
    }

    /**
     * ��ȡ�����¼�
     * 
     * @param name
     *            �¼�key
     * @return �¼�
     */
    public String getEventHubLocation(String name) {
        return eventHubLocations.get(name);
    }

    /**
     * ��ȡһ�������޸ĵı��ؽڵ�key����
     * 
     * @return �ڵ�key����
     */
    public Set<String> getPeerNames() {
        return Collections.unmodifiableSet(peerLocations.keySet());
    }

    /**
     * ��ȡһ�������޸ĵı��ؽڵ㼯��
     * 
     * @return �ڵ㼯��
     */
    public Set<Peer> getPeers() {
        return Collections.unmodifiableSet(peers);
    }

    /**
     * ��ȡһ�������޸ĵı�����֯key����
     * 
     * @return ��֯key����
     */
    public Set<String> getOrdererNames() {
        return Collections.unmodifiableSet(ordererLocations.keySet());
    }

    /**
     * ��ȡһ�������޸ĵı�����֯����
     * 
     * @return ��֯����
     */
    public Collection<String> getOrdererLocations() {
        return Collections.unmodifiableCollection(ordererLocations.values());
    }

    /**
     * ��ȡһ�������޸ĵı����¼�key����
     * 
     * @return �¼�key����
     */
    public Set<String> getEventHubNames() {
        return Collections.unmodifiableSet(eventHubLocations.keySet());
    }

    /**
     * ��ȡһ�������޸ĵı����¼�����
     * 
     * @return �¼�����
     */
    public Collection<String> getEventHubLocations() {
        return Collections.unmodifiableCollection(eventHubLocations.values());
    }

    /**
     * ���� ca �ͻ���
     * 
     * @param caClient
     *            ca �ͻ���
     */
    public void setCAClient(HFCAClient caClient) {
        this.caClient = caClient;
    }

    /**
     * ��ȡ ca �ͻ���
     * 
     * @return ca �ͻ���
     */
    public HFCAClient getCAClient() {
        return caClient;
    }

    /**
     * ���û�����������û�
     * 
     * @param user
     *            �û�
     */
    public void addUser(FabricUser user) {
        userMap.put(user.getName(), user);
    }

    /**
     * ���û����ϸ������ƻ�ȡ�û�
     * 
     * @param name
     *            ����
     * @return �û�
     */
    public User getUser(String name) {
        return userMap.get(name);
    }

    /**
     * ��ڵ㼯������ӽڵ�
     * 
     * @param peer
     *            �ڵ�
     */
    public void addPeer(Peer peer) {
        peers.add(peer);
    }

    /**
     * ���� ca ����
     * 
     * @param caProperties
     *            ca ����
     */
    public void setCAProperties(Properties caProperties) {
        this.caProperties = caProperties;
    }

    /**
     * ��ȡ ca ����
     * 
     * @return ca ����
     */
    public Properties getCAProperties() {
        return caProperties;
    }

    /**
     * �������˵��ڵ����Ա�û�
     * 
     * @param peerAdmin
     *            ���˵��ڵ����Ա�û�
     */
    public void setPeerAdmin(FabricUser peerAdmin) {
        this.peerAdmin = peerAdmin;
    }

    /**
     * ��ȡ���˵��ڵ����Ա�û�
     * 
     * @return ���˵��ڵ����Ա�û�
     */
    public FabricUser getPeerAdmin() {
        return peerAdmin;
    }

    /**
     * ������������
     * 
     * @param doainName
     *            ��������
     */
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    /**
     * ��ȡ��������
     * 
     * @return ��������
     */
    public String getDomainName() {
        return domainName;
    }

    /**
     * ��ָ��·���л�ȡ��׺Ϊ _sk ���ļ����Ҹ�·�������ҽ��и��ļ�
     * 
     * @param directorys
     *            ָ��·��
     * @return File
     */
    private File findFileSk(File directory) {

        FilenameFilter fileNameFilter = new FilenameFilter() {
  
           @Override
           public boolean accept(File dir, String name) {
              if(name.lastIndexOf('.')>0)
              {
                 // get last index for '.' char
                 int lastIndex = name.lastIndexOf('.');
                 
                 // get extension
                 String str = name.substring(lastIndex);
                 
                 // match path name extension
                 if(str.equals("_sk"))
                 {
                    return true;
                 }
              }
              return false;
           }
        };
        File[] matches = directory.listFiles(fileNameFilter);//((dir, name) -> name.endsWith("_sk"));
        if (null == matches) {
            throw new RuntimeException(String.format("Matches returned null does %s directory exist?", directory.getAbsoluteFile().getName()));
        }
        if (matches.length != 1) {
            throw new RuntimeException(String.format("Expected in %s only 1 sk file but found %d", directory.getAbsoluteFile().getName(), matches.length));
        }
        return matches[0];
    }

}
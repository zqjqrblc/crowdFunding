package fabric.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Fabric������peer��Ϣ��������cli��org��ca��couchdb�Ƚڵ��������������������Ϣ����
 * 
 * @author aberic
 *
 * @date 2017��10��18�� - ����1:49:03
 * @email abericyang@gmail.com
 */
public class Peers {

    /** ��ǰָ������֯���� */
    private String orgName; // Org1
    /** ��ǰָ������֯���� */
    private String orgMSPID; // Org1MSP
    /** ��ǰָ������֯���ڸ����� */
    private String orgDomainName; //org1.example.com
    /** orderer ������������� */
    private List<Peer> peers;

    public Peers() {
        peers = new ArrayList<>();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgMSPID() {
        return orgMSPID;
    }

    public void setOrgMSPID(String orgMSPID) {
        this.orgMSPID = orgMSPID;
    }

    public String getOrgDomainName() {
        return orgDomainName;
    }

    public void setOrgDomainName(String orgDomainName) {
        this.orgDomainName = orgDomainName;
    }

    /** ������������� */
    public void addPeer(String peerName, String peerEventHubName, String peerLocation, String peerEventHubLocation, String caLocation) {
        peers.add(new Peer(peerName, peerEventHubName, peerLocation, peerEventHubLocation, caLocation));
    }

    /** ��ȡ������������� */
    public List<Peer> get() {
        return peers;
    }

    /**
     * �ڵ����������
     * 
     * @author aberic
     *
     * @date 2017��11��11�� - ����6:56:14
     * @email abericyang@gmail.com
     */
    public class Peer {

        /** ��ǰָ������֯�ڵ����� */
        private String peerName; // peer0.org1.example.com
        /** ��ǰָ������֯�ڵ��¼����� */
        private String peerEventHubName; // peer0.org1.example.com
        /** ��ǰָ������֯�ڵ���ʵ�ַ */
        private String peerLocation; // grpc://110.131.116.21:7051
        /** ��ǰָ������֯�ڵ��¼��������ʵ�ַ */
        private String peerEventHubLocation; // grpc://110.131.116.21:7053
        /** ��ǰָ������֯�ڵ�ca���ʵ�ַ */
        private String caLocation; // http://110.131.116.21:7054
        /** ��ǰpeer�Ƿ�����Event�¼����� */
        private boolean addEventHub = false;

        public Peer(String peerName, String peerEventHubName, String peerLocation, String peerEventHubLocation, String caLocation) {
            this.peerName = peerName;
            this.peerEventHubName = peerEventHubName;
            this.peerLocation = peerLocation;
            this.peerEventHubLocation = peerEventHubLocation;
            this.caLocation = caLocation;
        }

        public String getPeerName() {
            return peerName;
        }

        public void setPeerName(String peerName) {
            this.peerName = peerName;
        }

        public String getPeerEventHubName() {
            return peerEventHubName;
        }

        public void setPeerEventHubName(String peerEventHubName) {
            this.peerEventHubName = peerEventHubName;
        }

        public String getPeerLocation() {
            return peerLocation;
        }

        public void setPeerLocation(String peerLocation) {
            this.peerLocation = peerLocation;
        }

        public String getPeerEventHubLocation() {
            return peerEventHubLocation;
        }

        public void setPeerEventHubLocation(String eventHubLocation) {
            this.peerEventHubLocation = eventHubLocation;
        }

        public String getCaLocation() {
            return caLocation;
        }

        public void setCaLocation(String caLocation) {
            this.caLocation = caLocation;
        }

        public boolean isAddEventHub() {
            return addEventHub;
        }

        public void addEventHub(boolean addEventHub) {
            this.addEventHub = addEventHub;
        }

    }

}
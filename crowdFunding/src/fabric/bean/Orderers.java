package fabric.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Fabric������orderer��Ϣ�����ǵ����ͼ�Ⱥ���ַ���
 * 
 * @author aberic
 *
 * @date 2017��10��18�� - ����1:56:48
 * @email abericyang@gmail.com
 */
public class Orderers {

    /** orderer ������������ڸ����� */
    private String ordererDomainName; // anti-moth.com
    /** orderer ������������� */
    private List<Orderer> orderers;

    public Orderers() {
        orderers = new ArrayList<>();
    }

    public String getOrdererDomainName() {
        return ordererDomainName;
    }

    public void setOrdererDomainName(String ordererDomainName) {
        this.ordererDomainName = ordererDomainName;
    }

    /** ������������� */
    public void addOrderer(String name, String location) {
        orderers.add(new Orderer(name, location));
    }

    /** ��ȡ������������� */
    public List<Orderer> get() {
        return orderers;
    }

    /**
     * �������������
     * 
     * @author aberic
     *
     * @date 2017��10��18�� - ����2:06:22
     * @email abericyang@gmail.com
     */
    public class Orderer {

        /** orderer ��������������� */
        private String ordererName;
        /** orderer ����������ķ��ʵ�ַ */
        private String ordererLocation;

        public Orderer(String ordererName, String ordererLocation) {
            super();
            this.ordererName = ordererName;
            this.ordererLocation = ordererLocation;
        }

        public String getOrdererName() {
            return ordererName;
        }

        public void setOrdererName(String ordererName) {
            this.ordererName = ordererName;
        }

        public String getOrdererLocation() {
            return ordererLocation;
        }

        public void setOrdererLocation(String ordererLocation) {
            this.ordererLocation = ordererLocation;
        }

    }

}
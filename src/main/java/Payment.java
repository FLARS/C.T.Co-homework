
public class Payment {
    String friendName;
    String serviceName;
    int serviceCost;

    public Payment(String friendName, String serviceName, int serviceCost) {
        this.friendName = friendName;
        this.serviceName = serviceName;
        this.serviceCost = serviceCost;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(int serviceCost) {
        this.serviceCost = serviceCost;
    }
}

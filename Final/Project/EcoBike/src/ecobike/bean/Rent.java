package ecobike.bean;

public class Rent {

    private int RID;
    private int AID;
    private int BID;
    private String startTime;
    private String endTime;

    public Rent() {

    }

    public Rent(int RID, int AID, int BID, String startTime, String endTime) {
        this.RID = RID;
        this.AID = AID;
        this.BID = BID;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return RID;
    }

    public void setId(int RID) {
        this.RID = RID;
    }

    public int getAccountId() {
        return AID;
    }

    public void setAccountId(int AID) {
        this.AID = AID;
    }

    public int getBikeId() {
        return BID;
    }

    public void setBikeId(int BID) {
        this.BID = BID;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

}

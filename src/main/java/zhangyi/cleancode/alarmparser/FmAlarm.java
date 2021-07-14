package zhangyi.cleancode.alarmparser;

public class FmAlarm {

    public static final int ALARM_UNKNOW = -1;
    public static final int ALARM_NEW = 0;
    public static final int ALARM_CLEARED = 1;
    public static final int ACK_STATE_CHANGED = 2;

    public static final int ALARM_NOTIFICATION = 10;
    public static final int ALARM_SYNCHRONISATION = 20;

    private int alarmType = 0;
    private int nodeType;

    private String systemDN;
    private String eventTime;
    private String specificProblem;
    private String alarmText;
    private String perceivedSeverity;
    private String eventType;
    private String alarmId;

    private String notificationId;
    private String probableCause;
    private String additionalText1;
    private String additionalText2;
    private String additionalText3;
    private String additionalText4;

    private int seq;
    private String agentId;
    private int agentIdType;
    private String cancelUser;
    private String nofificationId;
    private int notificationType;

    private String ackStatus;
    private String ackUser;

    private boolean uploadable;

    public String getAdditionalText1() {
        return additionalText1;
    }

    public void setAdditionalText1(String additionalText1) {
        this.additionalText1 = additionalText1;
    }

    public String getAdditionalText2() {
        return additionalText2;
    }

    public void setAdditionalText2(String additionalText2) {
        this.additionalText2 = additionalText2;
    }

    public String getAdditionalText3() {
        return additionalText3;
    }

    public void setAdditionalText3(String additionalText3) {
        this.additionalText3 = additionalText3;
    }

    public String getAdditionalText4() {
        return additionalText4;
    }

    public void setAdditionalText4(String additionalText4) {
        this.additionalText4 = additionalText4;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getProbableCause() {
        return probableCause;
    }

    public void setProbableCause(String probableCause) {
        this.probableCause = probableCause;
    }

    public String getSystemDN() {
        return systemDN;
    }

    public void setSystemDN(String systemDN) {
        this.systemDN = systemDN;
    }

    public String getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId;
    }

    public String getAlarmText() {
        return alarmText;
    }

    public void setAlarmText(String alarmText) {
        this.alarmText = alarmText;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getPerceivedSeverity() {
        return perceivedSeverity;
    }

    public void setPerceivedSeverity(String perceivedSeverity) {
        this.perceivedSeverity = perceivedSeverity;
    }

    public String getSpecificProblem() {
        return specificProblem;
    }

    public void setSpecificProblem(String specificProblem) {
        this.specificProblem = specificProblem;
    }

    public String toString() {
        return "Alarm: " + " alarmType = " + getAlarmType() + " specificProblem = " + getSpecificProblem() +
                " alarmText = " + getAlarmText() + " perceivedSeverity = " + getPerceivedSeverity() + " eventType = " +
                getEventType() + " alarmId = " + getAlarmId() + " additionalText1 = " + getAdditionalText1() +
                " additionalText2 = " + getAdditionalText2() + " additionalText3 = " + getAdditionalText3() +
                " additionalText4 = " + getAdditionalText4();
    }

    /**
     * @return Returns the alarmType.
     */
    public int getAlarmType() {
        return alarmType;
    }

    /**
     * @param alarmType The alarmType to set.
     */
    public void setAlarmType(int alarmType) {
        this.alarmType = alarmType;
    }

    /**
     * @return Returns the nodeType.
     */
    public int getNodeType() {
        return nodeType;
    }

    /**
     * @param nodeType The nodeType to set.
     */
    public void setNodeType(int nodeType) {
        this.nodeType = nodeType;
    }

    public String getAckStatus() {
        return ackStatus;
    }

    public void setAckStatus(String ackStatus) {
        this.ackStatus = ackStatus;
    }

    public String getAckUser() {
        return ackUser;
    }

    public void setAckUser(String ackUser) {
        this.ackUser = ackUser;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public int getAgentIdType() {
        return agentIdType;
    }

    public void setAgentIdType(int agentIdType) {
        this.agentIdType = agentIdType;
    }

    public String getCancelUser() {
        return cancelUser;
    }

    public void setCancelUser(String cancelUser) {
        this.cancelUser = cancelUser;
    }

    public String getNofificationId() {
        return nofificationId;
    }

    public void setNofificationId(String nofificationId) {
        this.nofificationId = nofificationId;
    }

    public int getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(int notificationType) {
        this.notificationType = notificationType;
    }

    public boolean isUploadable() {
        return uploadable;
    }

    public void setUploadable(boolean uploadable) {
        this.uploadable = uploadable;
    }

}

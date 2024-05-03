package zhangyi.refactoring.messagehandler;


public class MessageWriter {
    private MessageType messageType;

    public MessageWriter() {
    }

    public MessageType getMessageType() {
        return this.messageType;
    }

    public void selectBlock(String messageHeader) {
    }

    public void setFieldValue(Profile profile, Object value) {
    }

    public boolean selectBlockIfExists(String individual) {
        return false;
    }
}

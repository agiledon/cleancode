package zhangyi.refactoring.messagehandler;/*                                                                      *\
**                                                                      **
**      __  __ _________ _____          ©Mort BI                        **
**     |  \/  / () | () |_   _|         (c) 2015                        **
**     |_|\/|_\____|_|\_\ |_|           http://www.bigeyedata.com       **
**                                                                      **
\*                                                                      */


public class MessageWriter {
    private MessageType messageType;

    public MessageWriter() {
    }

    public MessageType getMessageType() {
        return this.messageType;
    }

    public void selectBlock(String messageHeader) {
    }

    public void setFieldValue(Profile profile, String identifier) {
    }

    public boolean selectBlockIfExists(String individual) {
        return false;
    }
}

package zhangyi.refactoring.messagehandler;

import java.util.Queue;

public abstract class MessageReceiver {

    public abstract MessageReader getMessageFor(MessageType messageType, String identifier, Queue queue);
}

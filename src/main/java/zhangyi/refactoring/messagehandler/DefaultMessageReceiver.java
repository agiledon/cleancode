package zhangyi.refactoring.messagehandler;/*                                                                      *\
**                                                                      **
**      __  __ _________ _____          ©Mort BI                        **
**     |  \/  / () | () |_   _|         (c) 2015                        **
**     |_|\/|_\____|_|\_\ |_|           http://www.bigeyedata.com       **
**                                                                      **
\*                                                                      */

import java.util.Queue;

public class DefaultMessageReceiver extends MessageReceiver {
    @Override
    public MessageReader getMessageFor(MessageType type, String identifier, Queue queue) {
        MessageReader reader = getCachedMessageFor(type, identifier, queue);
        while (reader == null) {
            reader = getMessageFromQueueFor(type, identifier, queue);
        }
        return reader;
    }

    private MessageReader getCachedMessageFor(MessageType type, String identifier, Queue queue) {
        return null;
    }

    private MessageReader getMessageFromQueueFor(MessageType type, String identifier, Queue queue) {
        return null;
    }
}

package zhangyi.refactoring.messagehandler;/*                                                                      *\
**                                                                      **
**      __  __ _________ _____          ©Mort BI                        **
**     |  \/  / () | () |_   _|         (c) 2015                        **
**     |_|\/|_\____|_|\_\ |_|           http://www.bigeyedata.com       **
**                                                                      **
\*                                                                      */

public class MessageCheckFactory {
    public MessageCheckFactory() {
    }

    public MessageChecker checkerFor(MessageType messageType, MessageReader messageReader, String messageText) {
        return new MessageChecker();
    }
}

package zhangyi.refactoring.messagehandler.checker;/*                                                                      *\
**                                                                      **
**      __  __ _________ _____          ©Mort BI                        **
**     |  \/  / () | () |_   _|         (c) 2015                        **
**     |_|\/|_\____|_|\_\ |_|           http://www.bigeyedata.com       **
**                                                                      **
\*                                                                      */

import zhangyi.refactoring.messagehandler.MessageReader;
import zhangyi.refactoring.messagehandler.MessageType;

public class MessageCheckFactory {
    public MessageCheckFactory() {
    }

    public MessageChecker checkerFor(MessageType messageType, MessageReader messageReader, String messageText) {
        switch (messageType) {
            case SO05:
                return new SO05MessageChecker(messageReader, messageText);
            case SO07:
                return new SO07MessageChecker(messageReader, messageText);
            case SO08:
                return new SO08MessageChecker(messageReader, messageText);
            default:
                return new DefaultMessageChecker(messageReader, messageText);
        }

    }
}

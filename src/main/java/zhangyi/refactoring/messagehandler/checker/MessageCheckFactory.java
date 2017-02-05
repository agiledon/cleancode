package zhangyi.refactoring.messagehandler.checker;/*                                                                      *\
**                                                                      **
**      __  __ _________ _____          ©Mort BI                        **
**     |  \/  / () | () |_   _|         (c) 2015                        **
**     |_|\/|_\____|_|\_\ |_|           http://www.bigeyedata.com       **
**                                                                      **
\*                                                                      */

import zhangyi.refactoring.messagehandler.MessageType;

public class MessageCheckFactory {
    public MessageChecker checkerFor(MessageType messageType, String expectedMessage, String actualMessage) {
        switch (messageType) {
            case SO05:
                return new SO05MessageChecker(expectedMessage, actualMessage);
            case SO07:
                return new SO07MessageChecker(expectedMessage, actualMessage);
            case SO08:
                return new SO08MessageChecker(expectedMessage, actualMessage);
            default:
                return new DefaultMessageChecker(expectedMessage, actualMessage);
        }

    }
}

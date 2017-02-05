package zhangyi.refactoring.messagehandler.checker;/*                                                                      *\
**                                                                      **
**      __  __ _________ _____          ©Mort BI                        **
**     |  \/  / () | () |_   _|         (c) 2015                        **
**     |_|\/|_\____|_|\_\ |_|           http://www.bigeyedata.com       **
**                                                                      **
\*                                                                      */

import zhangyi.refactoring.messagehandler.MessageReader;

public abstract class MessageChecker {
    protected final MessageReader expectedMessage;
    protected final String actualMessage;

    public MessageChecker(MessageReader expectedMessage, String actualMessage) {
        this.expectedMessage = expectedMessage;
        this.actualMessage = actualMessage;
    }

    public abstract void checkResponse();
}

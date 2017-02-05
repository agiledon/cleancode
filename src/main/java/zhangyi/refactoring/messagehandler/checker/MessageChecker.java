package zhangyi.refactoring.messagehandler.checker;/*                                                                      *\
**                                                                      **
**      __  __ _________ _____          ©Mort BI                        **
**     |  \/  / () | () |_   _|         (c) 2015                        **
**     |_|\/|_\____|_|\_\ |_|           http://www.bigeyedata.com       **
**                                                                      **
\*                                                                      */

public abstract class MessageChecker {
    protected final String expectedMessage;
    protected final String actualMessage;

    public MessageChecker(String expectedMessage, String actualMessage) {
        this.expectedMessage = expectedMessage;
        this.actualMessage = actualMessage;
    }

    public abstract void checkResponse();
}

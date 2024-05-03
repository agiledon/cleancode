package zhangyi.refactoring.messagehandler.checker;

import zhangyi.refactoring.messagehandler.MessageReader;

public class DefaultMessageChecker extends MessageChecker {
    public DefaultMessageChecker(MessageReader expectedMessage, String actualMessage) {
        super(expectedMessage, actualMessage);
    }

    @Override
    public void checkResponse() {

    }
}

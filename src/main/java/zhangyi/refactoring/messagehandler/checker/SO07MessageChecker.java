package zhangyi.refactoring.messagehandler.checker;

import zhangyi.refactoring.messagehandler.MessageReader;

public class SO07MessageChecker extends MessageChecker {
    public SO07MessageChecker(MessageReader expectedMessage, String actualMessage) {
        super(expectedMessage, actualMessage);
    }

    @Override
    public void checkResponse() {

    }
}

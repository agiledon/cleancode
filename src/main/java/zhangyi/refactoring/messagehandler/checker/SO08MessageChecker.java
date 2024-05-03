package zhangyi.refactoring.messagehandler.checker;

import zhangyi.refactoring.messagehandler.MessageReader;

public class SO08MessageChecker extends MessageChecker {
    public SO08MessageChecker(MessageReader expectedMessage, String actualMessage) {
        super(expectedMessage, actualMessage);
    }

    @Override
    public void checkResponse() {

    }
}

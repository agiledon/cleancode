package zhangyi.refactoring.messagehandler.checker;

import zhangyi.refactoring.messagehandler.MessageReader;

public class SO05MessageChecker extends MessageChecker {
    public SO05MessageChecker(MessageReader expectedMessage, String actualMessage) {
        super(expectedMessage, actualMessage);
    }

    @Override
    public void checkResponse() {

    }
}

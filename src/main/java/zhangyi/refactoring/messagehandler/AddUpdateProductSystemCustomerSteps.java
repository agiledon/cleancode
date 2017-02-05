package zhangyi.refactoring.messagehandler;/*                                                                      *\
**                                                                      **
**      __  __ _________ _____          ©Mort BI                        **
**     |  \/  / () | () |_   _|         (c) 2015                        **
**     |_|\/|_\____|_|\_\ |_|           http://www.bigeyedata.com       **
**                                                                      **
\*                                                                      */

import zhangyi.refactoring.messagehandler.checker.MessageCheckFactory;

import java.util.Queue;

public class AddUpdateProductSystemCustomerSteps extends AbstractCustomerExpectationSteps {
    private static final String SO05_MESSAGE_HEADER = "";
    private static final String SO05_PROFILE = "";
    private static final String SO05_INDIVIDUAL = "";
    private static final Profile INDIVIDUAL_CUSTOMER_TYPE;
    private MessageReceiver messageReceiver;
    private ScenarioContext scenarioContext;
    private StoryContext storyContext;
    private MessageCheckFactory messageCheckFactory;
    private TransformerFactory transformerFactory;
    private MessageFactory messageFactory;

    public AddUpdateProductSystemCustomerSteps() {
    }

    private void checkPropagationQueueByName(String name, Queue queue, MessageType messageType) {
        MessageReader reader = this.messageReceiver.getMessageFor(messageType, queue);
        String messageText = reader.toString();
        this.scenarioContext.setResponseText(messageText);
        this.scenarioContext.setResponseMessage(reader);
        if(messageType == MessageType.SO05) {
            this.messageCheckFactory.checkerFor(messageType, this.getExpectedSO05ResponseFor(name), messageText).checkResponse();
        }

        if(messageType == MessageType.SO07) {
            this.checkSO07Response(name, messageType, messageText);
        }

        if(messageType == MessageType.SO08) {
            this.messageCheckFactory.checkerFor(messageType, this.getExpectedSO08ResponseFor(name), messageText).checkResponse();
        }

    }

    protected MessageReader getExpectedSO05ResponseFor(String name) {
        MessageWriter writer;
        if(this.scenarioContext.hasExpectedMessage() && this.scenarioContext.getExpectedMessage().getMessageType() == MessageType.SO05) {
            writer = this.scenarioContext.getExpectedMessage();
        } else {
            writer = this.transformerFactory.transformerFor(this.scenarioContext.getRequestMessage(), MessageType.SO05).forCustomer(name).transform();
        }

        writer.selectBlock("");
        writer.selectBlock("");
        String identifier = this.storyContext.getCustomerIdentifier(name);
        writer.setFieldValue(Profile.PROFILE_ID, identifier);
        String customerVersion = this.scenarioContext.getCustomerVersion();
        writer.setFieldValue(Profile.USER_COUNT, customerVersion);
        if(writer.selectBlockIfExists("")) {
            writer.setFieldValue(INDIVIDUAL_CUSTOMER_TYPE, (String)null);
        }

        return this.messageFactory.readFor(MessageType.SO05, writer.toString());
    }

    private MessageReader getExpectedSO08ResponseFor(String name) {
        return null;
    }

    private void checkSO07Response(String name, MessageType messageType, String messageText) {
    }

    static {
        INDIVIDUAL_CUSTOMER_TYPE = Profile.INDIVIDUAL;
    }
}

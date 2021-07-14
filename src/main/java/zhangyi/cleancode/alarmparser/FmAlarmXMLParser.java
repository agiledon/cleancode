/**
 *
 */
package zhangyi.cleancode.alarmparser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 * class to parse alarm file
 *
 */
public class FmAlarmXMLParser
        implements FmAlarmXMLFeatures {

    private static Logger log = Logger.getLogger(FmAlarmXMLParser.class);

    public List<FmAlarm> parse(String filename) throws XMLParserException {
        List<FmAlarm> alarmList = null;
        DOMParser parser = new DOMParser();

        boolean namespaces = DEFAULT_NAMESPACES;
        boolean validation = DEFAULT_VALIDATION;
        boolean externalDTD = DEFAULT_LOAD_EXTERNAL_DTD;
        boolean schemaValidation = DEFAULT_SCHEMA_VALIDATION;
        boolean schemaFullChecking = DEFAULT_SCHEMA_FULL_CHECKING;
        boolean dynamicValidation = DEFAULT_DYNAMIC_VALIDATION;

        // set parser features
        try {
            parser.setFeature(NAMESPACES_FEATURE_ID, namespaces);
        } catch (SAXException e1) {

            log.warn("warning: Parser does not support feature (" + NAMESPACES_FEATURE_ID + ")");
        }
        try {
            parser.setFeature(VALIDATION_FEATURE_ID, validation);
        } catch (SAXException e1) {

            log.warn("warning: Parser does not support feature (" + VALIDATION_FEATURE_ID + ")");
        }
        try {

            parser.setFeature(LOAD_EXTERNAL_DTD_FEATURE_ID, externalDTD);
        } catch (SAXException e1) {

            log.warn("warning: Parser does not support feature (" + LOAD_EXTERNAL_DTD_FEATURE_ID + ")");
        }
        try {
            parser.setFeature(SCHEMA_VALIDATION_FEATURE_ID, schemaValidation);
        } catch (SAXException e1) {

            log.warn("warning: Parser does not support feature (" + SCHEMA_VALIDATION_FEATURE_ID + ")");
        }
        try {
            parser.setFeature(SCHEMA_FULL_CHECKING_FEATURE_ID, schemaFullChecking);
        } catch (SAXException e1) {

            log.warn("warning: Parser does not support feature (" + SCHEMA_FULL_CHECKING_FEATURE_ID + ")");
        }
        try {
            parser.setFeature(DYNAMIC_VALIDATION_FEATURE_ID, dynamicValidation);
        } catch (SAXException e1) {

            log.warn("warning: Parser does not support feature (" + DYNAMIC_VALIDATION_FEATURE_ID + ")");
        }
        try {
            parser.parse(filename);

            Document doc = parser.getDocument();

            NodeList children = doc.getChildNodes();
            if (children.getLength() != 1) {
                throw new XMLParserException("Error: XML file must have a single element (xml). This one has " +
                        children.getLength());
            }

            Node child = (Node) children.item(0);
            String nodeName = child.getLocalName();
            int nodeType = -1;
            if (nodeName.equalsIgnoreCase("notification")) {
                nodeType = FmAlarm.ALARM_NOTIFICATION;
            }
            if (nodeName.equalsIgnoreCase("alarmSynchronisation")) {
                nodeType = FmAlarm.ALARM_SYNCHRONISATION;
            }
            if (nodeType < 0) {
                throw new XMLParserException("Error: Node unknown: " + nodeName);
            }

            NodeList valueList = child.getChildNodes();
            alarmList = parseAlarm(valueList, nodeType);

        } catch (SAXException e) {
            throw new XMLParserException("Error 1 parsing alarm file: " + e.getMessage(), e);
        } catch (IOException e) {
            throw new XMLParserException("Error 2 reading alarm file: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new XMLParserException("Error 3 reading alarm file: " + e.getMessage(), e);
        }

        return alarmList;
    }

    /**
     * 1(basic) + 29(if) + 1(for) = 31 "throw" in "else" don't add 1 by CC
     *
     * @param node
     * @param nodeType
     * @return
     * @throws XMLParserException
     */
    protected List<FmAlarm> parseAlarm(NodeList node, int nodeType) throws XMLParserException {

        List<FmAlarm> alarmList = new ArrayList<FmAlarm>();

        int type = FmAlarm.ALARM_UNKNOW;
        NodeList valueList;

        int l = node.getLength();
        for (int i = 0; i < l; i++) {
            if (node.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            FmAlarm fmAlarm = new FmAlarm();
            Element alarm = (Element) node.item(i);

            String nodeName = alarm.getNodeName();
            if (nodeName.equalsIgnoreCase("alarmNew")) {
                type = FmAlarm.ALARM_NEW;
            }
            if (nodeName.equalsIgnoreCase("alarmCleared")) {
                type = FmAlarm.ALARM_CLEARED;
            }
            if (nodeName.equalsIgnoreCase("ackStateChanged")) {
                type = FmAlarm.ACK_STATE_CHANGED;
            }

            fmAlarm.setAlarmType(type);
            fmAlarm.setNodeType(nodeType);

            fmAlarm.setSystemDN(alarm.getAttribute("systemDN"));

            // Get eventTime
            valueList = alarm.getElementsByTagName("eventTime");
            if (valueList.getLength() == 1) {
                Element value = (Element) valueList.item(0);
                Text text = (Text) value.getFirstChild();
                fmAlarm.setEventTime(text.getNodeValue());
            } else {
                throw new XMLParserException("Error: alarm must have 1 eventTime tag. This one has " +
                        valueList.getLength());
            }

            // Get alarmId
            valueList = alarm.getElementsByTagName("alarmId");
            if (valueList.getLength() == 1) {
                Element value = (Element) valueList.item(0);
                Text text = (Text) value.getFirstChild();
                fmAlarm.setAlarmId(text.getNodeValue());
            } else {
                throw new XMLParserException("Error: alarm must have 1 alarmId tag. This one has " +
                        valueList.getLength());
            }

            // Get specificProblem
            valueList = alarm.getElementsByTagName("specificProblem");
            if (valueList.getLength() == 1) {
                Element value = (Element) valueList.item(0);
                Text text = (Text) value.getFirstChild();
                fmAlarm.setSpecificProblem(text.getNodeValue());
            } else {
                throw new XMLParserException("Error: alarm must have 1 specificProblem tag. This one has " +
                        valueList.getLength());
            }

            // get alarmText
            valueList = alarm.getElementsByTagName("alarmText");
            if (valueList.getLength() == 1) {
                Element value = (Element) valueList.item(0);
                Text text = (Text) value.getFirstChild();
                fmAlarm.setAlarmText(text.getNodeValue());
            } else if (type == FmAlarm.ALARM_NEW) {
                throw new XMLParserException("Error: alarmNew must have 1 alarmText tag. This one has " +
                        valueList.getLength());
            }

            // Get perceivedSeverity
            valueList = alarm.getElementsByTagName("perceivedSeverity");
            if (valueList.getLength() == 1) {
                Element value = (Element) valueList.item(0);
                Text text = (Text) value.getFirstChild();
                fmAlarm.setPerceivedSeverity(text.getNodeValue());
            } else if (type == FmAlarm.ALARM_NEW) {
                throw new XMLParserException("Error: alarmNew must have 1 perceivedSeverity tag. This one has " +
                        valueList.getLength());
            }

            // Get eventType
            valueList = alarm.getElementsByTagName("eventType");
            if (valueList.getLength() == 1) {
                Element value = (Element) valueList.item(0);
                Text text = (Text) value.getFirstChild();
                fmAlarm.setEventType(text.getNodeValue());
            } else if (type == FmAlarm.ALARM_NEW) {
                throw new XMLParserException("Error: alarmNew must have 1 eventType tag. This one has " +
                        valueList.getLength());
            }

            // Get notificationId
            valueList = alarm.getElementsByTagName("notificationId");
            if (valueList.getLength() > 0) {
                if (valueList.getLength() == 1) {
                    Element value = (Element) valueList.item(0);
                    Text text = (Text) value.getFirstChild();
                    fmAlarm.setNotificationId(text.getNodeValue());
                } else {
                    throw new XMLParserException("Error: alarm must have 0 or 1 notificationId tag. This one has " +
                            valueList.getLength());
                }
            }

            // Get probableCause
            valueList = alarm.getElementsByTagName("probableCause");
            if (valueList.getLength() > 0) {
                if (valueList.getLength() == 1) {
                    Element value = (Element) valueList.item(0);
                    Text text = (Text) value.getFirstChild();
                    fmAlarm.setProbableCause(text.getNodeValue());
                } else {
                    throw new XMLParserException("Error: alarmNew must have 0 or 1 probableCause tag. This one has " +
                            valueList.getLength());
                }
            }

            // Get additionalText1
            valueList = alarm.getElementsByTagName("additionalText1");
            if (valueList.getLength() > 0) {
                if (valueList.getLength() == 1) {
                    Element value = (Element) valueList.item(0);
                    Text text = (Text) value.getFirstChild();
                    fmAlarm.setAdditionalText1(text.getNodeValue());
                } else {
                    throw new XMLParserException(
                            "Error: alarmNew must have 0 or 1 additionalText1 tag. This one has " + valueList.getLength());
                }
            }

            // Get additionalText2
            valueList = alarm.getElementsByTagName("additionalText2");
            if (valueList.getLength() > 0) {
                if (valueList.getLength() == 1) {
                    Element value = (Element) valueList.item(0);
                    Text text = (Text) value.getFirstChild();
                    fmAlarm.setAdditionalText2(text.getNodeValue());
                } else {
                    throw new XMLParserException(
                            "Error: alarmNew must have 0 or 1 additionalText2 tag. This one has " + valueList.getLength());
                }
            }

            // get additionalText3
            valueList = alarm.getElementsByTagName("additionalText3");
            if (valueList.getLength() > 0) {
                if (valueList.getLength() == 1) {
                    Element value = (Element) valueList.item(0);
                    Text text = (Text) value.getFirstChild();
                    fmAlarm.setAdditionalText3(text.getNodeValue());
                } else {
                    throw new XMLParserException(
                            "Error: alarmNew must have 0 or 1 additionalText3 tag. This one has " + valueList.getLength());
                }
            }

            // Get additionalText4
            valueList = alarm.getElementsByTagName("additionalText4");
            if (valueList.getLength() > 0) {
                if (valueList.getLength() == 1) {
                    Element value = (Element) valueList.item(0);
                    Text text = (Text) value.getFirstChild();
                    fmAlarm.setAdditionalText4(text.getNodeValue());
                } else {
                    throw new XMLParserException(
                            "Error: alarmNew must have 0 or 1 additionalText4 tag. This one has " + valueList.getLength());
                }
            }

            // Get ackStatus
            valueList = alarm.getElementsByTagName("ackStatus");
            if (valueList.getLength() > 0) {
                if (valueList.getLength() == 1) {
                    Element value = (Element) valueList.item(0);
                    Text text = (Text) value.getFirstChild();
                    fmAlarm.setAckStatus(text.getNodeValue());
                } else {
                    throw new XMLParserException("Error: alarmNew must have 0 or 1 ackStatus tag. This one has " +
                            valueList.getLength());
                }
            }
            // Get ackUser
            valueList = alarm.getElementsByTagName("ackUser");
            if (valueList.getLength() > 0) {
                if (valueList.getLength() == 1) {
                    Element value = (Element) valueList.item(0);
                    Text text = (Text) value.getFirstChild();
                    fmAlarm.setAckUser(text.getNodeValue());
                } else {
                    throw new XMLParserException("Error: alarmNew must have 0 or 1 ackStatus tag. This one has " +
                            valueList.getLength());
                }
            }

            alarmList.add(fmAlarm);
        }
        return alarmList;
    }

    public static void main(String args[]) throws XMLParserException {

        String filename = "an_mainHost_10.102.243.39_XXX.xml";
        FmAlarmXMLParser f = new FmAlarmXMLParser();
        List<FmAlarm> a = f.parse(filename);
        log.info(a.toString());
    }

}

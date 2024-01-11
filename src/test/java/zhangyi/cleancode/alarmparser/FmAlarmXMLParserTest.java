package zhangyi.cleancode.alarmparser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FmAlarmXMLParserTest {
    private FmAlarmXMLParser fmAlarmXMLParser;
    private static final String FileName = "/an_mainHost_10.102.243.39_XXX.xml";

    @Before
    public void setUp() {
        fmAlarmXMLParser = new FmAlarmXMLParser();
    }

    @Test
    public void testParse() throws Exception {
        URL resource = this.getClass().getResource(FileName);


        String path = (new File(resource.getPath())).getAbsolutePath();
        List<FmAlarm> result = fmAlarmXMLParser.parse(path);
        assertEquals(
                result.toString(),
                "[Alarm:  alarmType = 0 specificProblem = 2147483647 alarmText = test perceivedSeverity = major eventType = communication alarmId = 2147483647 additionalText1 = test additionalText2 = null additionalText3 = null additionalText4 = null]");
    }

    @After
    public void tearDown() {
        fmAlarmXMLParser = null;
    }

}
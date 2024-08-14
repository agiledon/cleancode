package zhangyi.utpractice.json;

import org.junit.Before;
import org.junit.Test;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JsonObjectTest {

    private JsonObject rootJsonObj;

    @Before
    public void setUp() {
        rootJsonObj = Json.parse(provideJsonString());
    }

    @Test
    public void should_return_default_string_value_given_wrong_key_name() {
        String result = rootJsonObj.getString("wrong_key_name", "default");
        assertThat(result).isEqualTo("default");
    }

    @Test
    public void should_return_empty_given_nonexisted_key_name() {
        Optional<JsonObject> nonexisted = rootJsonObj.getJsonObject("nonexisted");
        assertThat(nonexisted.isPresent()).isFalse();
    }

    @Test
    public void should_return_child_JsonObject_given_existed_key_name() {
        Optional<JsonObject> caacetcObj = rootJsonObj.getJsonObject("etc");
        assertThat(caacetcObj.isPresent()).isTrue();
        assertThat(caacetcObj.get()).isNotNull();
    }

    @Test
    public void should_return_nested_string_field_of_JsonObject() {
        Optional<String> flidOpt = rootJsonObj.getJsonObject("etc")
                .flatMap(caacetc -> caacetc.getJsonObject("data"))
                .flatMap(data -> data.getJsonObject("MSG"))
                .flatMap(msg -> msg.getJsonObject("DFLT"))
                .map(dflt -> dflt.getString("FLID"));
        assertThat(flidOpt.isPresent()).isTrue();
        assertThat(flidOpt.get()).isEqualTo("3084916");
    }

    @Test
    public void should_return_empty_of_JsonObject_given_wrong_key_name() {
        Optional<String> flidOpt = rootJsonObj.getJsonObject("etc")
                .flatMap(caacetc -> caacetc.getJsonObject("data"))
                .flatMap(data -> data.getJsonObject("MSG"))
                .flatMap(msg -> msg.getJsonObject("WRONG_DFLT"))
                .map(dflt -> dflt.getString("FLID"));
        assertThat(flidOpt.isPresent()).isFalse();
    }

    @Test
    public void should_return_false_for_object_given_wrong_key_name() {
        assertThat(rootJsonObj.isObject("worng_key_name")).isFalse();
    }

    @Test
    public void should_return_true_for_object_given_correct_key_name() {
        assertThat(rootJsonObj.isObject("etc")).isTrue();
    }

    @Test
    public void should_return_true_for_array_given_correct_key_name() {
        Optional<Boolean> flidOpt = rootJsonObj.getJsonObject("etc")
                .flatMap(caacetc -> caacetc.getJsonObject("data"))
                .flatMap(data -> data.getJsonObject("MSG"))
                .flatMap(msg -> msg.getJsonObject("DFLT"))
                .map(dflt -> dflt.isArray("TEST_STRING_ARRAY"));
        assertThat(flidOpt.isPresent()).isTrue();
        assertThat(flidOpt.get()).isTrue();
    }

    @Test
    public void should_return_false_for_not_array_given_correct_key_name() {
        Optional<Boolean> flidOpt = rootJsonObj.getJsonObject("etc")
                .flatMap(caacetc -> caacetc.getJsonObject("data"))
                .flatMap(data -> data.getJsonObject("MSG"))
                .flatMap(msg -> msg.getJsonObject("DFLT"))
                .map(dflt -> dflt.isArray("FRLT"));
        assertThat(flidOpt.isPresent()).isTrue();
        assertThat(flidOpt.get()).isFalse();
    }

    @Test
    public void should_return_empty_Object_for_remove_key_name(){
        Optional<JsonObject> flidOpt = rootJsonObj.getJsonObject("etc")
                .map(caacetc->caacetc.remove("data"))
                .flatMap(caacetc->caacetc.getJsonObject("data"));
        assertThat(flidOpt.isPresent()).isFalse();
    }

    @Test
    public void should_return_true_Object_for_given_wrong_key_name(){
        Optional<String> fildOpt = rootJsonObj.getJsonObject("etc")
                .map(caacetc->caacetc.remove("SEQN"))
                .flatMap(caacetc->caacetc.getJsonObject("data"))
                .flatMap(data->data.getJsonObject("MSG"))
                .flatMap(msg->msg.getJsonObject("META"))
                .map(meta->meta.getString("SEQN"));
        assertThat(fildOpt.isPresent()).isTrue();
        assertThat(fildOpt.get()).isEqualTo("651");
    }

    @Test
    public void should_return_empty_Object_for_remove_list_key_name(){
        Optional<JsonObject> caacetcAfterRemove = rootJsonObj.getJsonObject("etc")
                .map(caacetc->caacetc.removeAll("data","originalTimestamp"));
        assertThat(caacetcAfterRemove.isPresent()).isTrue();

        Optional<String> messageId = caacetcAfterRemove.map(caacetc->caacetc.getString("messageId"));
        assertThat(messageId.isPresent()).isTrue();
        assertThat(messageId.get()).isEqualTo("123456");

        Optional<String> originalTimestamp = caacetcAfterRemove.map(caacetc->caacetc.getString("originalTimestamp"));
        assertThat(originalTimestamp.isPresent()).isFalse();

        Optional<JsonObject> data = caacetcAfterRemove.flatMap(caacetc->caacetc.getJsonObject("data"));
        assertThat(data.isPresent()).isFalse();
    }

    @Test
    public void should_return_Object_for_put_in_key_and_value(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("test",rootJsonObj.getJSONObject());
        jsonObject.put("first","1");

        Optional<String> fildOpt = jsonObject.getJsonObject("test")
                .flatMap(test->test.getJsonObject("etc"))
                .flatMap(caacetc->caacetc.getJsonObject("data"))
                .flatMap(data->data.getJsonObject("MSG"))
                .flatMap(msg->msg.getJsonObject("META"))
                .map(meta->meta.getString("SNDR"));
        assertThat(fildOpt.isPresent()).isTrue();
        assertThat(fildOpt.get()).isEqualTo("FIMS");

        String first = jsonObject.getString("first");
        assertThat(first).isNotEmpty();
        assertThat(first).isEqualTo("1");
    }

    @Test
    public void should_cover_value_in_same_key() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("first", "2");
        jsonObject.put("first", "1");

        assertThat(jsonObject.getString("first")).isEqualTo("1");

        System.out.println(provideJsonString());
    }

    private String provideJsonString() {
        return "{\n" +
                "  \"etc\": {\n" +
                "    \"messageId\": \"123456\",\n" +
                "    \"originalTimestamp\": \"201807180954\",\n" +
                "    \"data\": {\n" +
                "      \"MSG\": {\n" +
                "        \"META\": {\n" +
                "          \"SNDR\": \"FIMS\",\n" +
                "          \"SEQN\": \"651\",\n" +
                "          \"DDTM\": \"20130810225122\",\n" +
                "          \"TYPE\": \"DFME\",\n" +
                "          \"STYP\": \"NXTE\"\n" +
                "        },\n" +
                "        \"DFLT\": {\n" +
                "          \"FLID\": \"3084916\",\n" +
                "          \"FFID\": \"CZ-6115-20100103081030-D\",\n" +
                "          \"FLTK\": \"W/Z\",\n" +
                "          \"FATT\": \"2403\",\n" +
                "          \"FRLT\": \"20130810224900\",\n" +
                "          \"TEST_STRING_ARRAY\": [\"A\", \"B\", \"C\"]\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";
    }
}
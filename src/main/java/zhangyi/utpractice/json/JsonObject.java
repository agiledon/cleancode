package zhangyi.utpractice.json;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

public class JsonObject extends Json implements Serializable {

    private static final long serialVersionUID = 1L;
    private JSONObject currentJsonObj;

    public JsonObject() {
        this.currentJsonObj = new JSONObject();
    }

    public JsonObject(JSONObject currentJsonObj) {
        this.currentJsonObj = currentJsonObj;
    }

    public JSONObject getJSONObject() {
        return (JSONObject) currentJsonObj.clone();
    }

    public String getSimpleString(String key) {
        return getString(key, "");
    }

    public String getString(String key) {
        return getString(key, null);
    }

    public String getString(String key, String defaultValue) {
        String result = currentJsonObj.getString(key);
        if (result == null) {
            return defaultValue;
        }
        return result;
    }

    public Integer getSimpleInt(String key) {
        return getInteger(key, 0);
    }

    public Integer getInteger(String key) {
        return getInteger(key, null);
    }

    public Integer getInteger(String key, Integer defaultValue) {
        Integer result = currentJsonObj.getInteger(key);
        if (result == null) {
            return defaultValue;
        }
        return result;
    }

    public Float getSimpleFloat(String key) {
        return getFloat(key, 0F);
    }

    public Float getFloat(String key) {
        return getFloat(key, null);
    }

    public Float getFloat(String key, Float defaultValue) {
        Float result = currentJsonObj.getFloat(key);
        if (result == null) {
            return defaultValue;
        }
        return result;
    }

    public Double getSimpleDouble(String key) {
        return getDouble(key, 0.0D);
    }

    public Double getDouble(String key) {
        return getDouble(key, null);
    }

    public Double getDouble(String key, Double defaultValue) {
        Double result = currentJsonObj.getDouble(key);
        if (result == null) {
            return defaultValue;
        }
        return result;
    }

    public BigDecimal getSimpleDecimal(String key) {
        return getDecimal(key, new BigDecimal(0));
    }

    public BigDecimal getDecimal(String key) {
        return getDecimal(key, null);
    }

    public BigDecimal getDecimal(String key, BigDecimal defaultValue) {
        String value = getString(key);
        BigDecimal bigDecimal;
        try {
            bigDecimal = new BigDecimal(value);
        } catch (Exception e) {
            bigDecimal = defaultValue;
        }
        return bigDecimal;
    }

    public Set<String> keySet() {
        return currentJsonObj.keySet();
    }

    public Optional<JsonObject> getJsonObject(String key) {
        try {
            JSONObject jsonObject = currentJsonObj.getJSONObject(key);
            if (jsonObject == null) {
                return Optional.empty();
            }
            return Optional.of(new JsonObject(jsonObject));
        } catch (ClassCastException ex) {
            return Optional.empty();
        }
    }

    public <T> JsonArray<T> getJsonArray(String key) {
        try {
            if (isObject(key)) {
                return getJsonArrayIfIsJSONObject(key);
            }
            if (isArray(key)) {
                return getJsonArrayIfIsJSONArray(key);
            }
            return getJsonArrayIfIsObject(key);
        } catch (ClassCastException ex) {
            return new JsonArray<>();
        }
    }

    private <T> JsonArray<T> getJsonArrayIfIsObject(String key) {
        Object obj = currentJsonObj.get(key);
        if (obj == null) {
            return new JsonArray<>();
        }
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(0, obj);
        return new JsonArray<>(jsonArray);
    }

    private <T> JsonArray<T> getJsonArrayIfIsJSONArray(String key) {
        JSONArray jsonArray = currentJsonObj.getJSONArray(key);
        if (jsonArray == null || jsonArray.isEmpty()) {
            return new JsonArray<>();
        }
        return new JsonArray<>(jsonArray);
    }

    private <T> JsonArray<T> getJsonArrayIfIsJSONObject(String key) {
        JSONObject jsonObject = currentJsonObj.getJSONObject(key);
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(0, jsonObject);
        return new JsonArray<>(jsonArray);
    }

    public JsonObject remove(String key) {
        currentJsonObj.remove(key);
        return new JsonObject(currentJsonObj);
    }

    public JsonObject removeAll(String... key) {
        for (String s : key) {
            currentJsonObj.remove(s);
        }
        return new JsonObject(currentJsonObj);
    }

    public JsonObject put(String key, Object value) {
        return new JsonObject(currentJsonObj.fluentPut(key, value));
    }

    public boolean isArray(String key) {
        return byFlag(key, "[");
    }

    public boolean isObject(String key) {
        return byFlag(key, "{");
    }

    private boolean byFlag(String key, String flag) {
        return getString(key, "").trim().startsWith(flag);
    }

    @Override
    public String toString() {
        return currentJsonObj.toString();
    }
}

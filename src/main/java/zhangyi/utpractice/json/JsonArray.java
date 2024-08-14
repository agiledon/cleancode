package zhangyi.utpractice.json;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class JsonArray<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private JSONArray jsonArray;

    public JsonArray() {
        this.jsonArray = new JSONArray();
    }

    public JsonArray(List<JsonObject> list) {
        this.jsonArray = new JSONArray(list);
    }

    public static <T> JsonArray<T> fromJSONArray(JSONArray array) {
        JsonArray<T> objectJsonArray = new JsonArray<>();
        objectJsonArray.jsonArray = array;
        return objectJsonArray;
    }

    public <U> JsonArray<U> map(Function<T, U> mapper) {
        Objects.requireNonNull(mapper);
        JSONArray array = new JSONArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            array.add(i, mapper.apply(get(i)));
        }
        return fromJSONArray(array);

    }

    public <U> JsonArray<U> flatMap(Function<T, JsonArray<U>> mapper) {
        Objects.requireNonNull(mapper);
        JSONArray array = new JSONArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonArray<U> nestedArray = mapper.apply(get(i));
            for (int j = 0; j < nestedArray.size(); j++) {
                array.add(array.size(), nestedArray.get(j));
            }
        }
        return new JsonArray<>(array);
    }

    public JsonArray<T> filter(Predicate<T> predicate) {
        Objects.requireNonNull(predicate);
        JSONArray array = new JSONArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            if (predicate.test(get(i))) {
                array.add(array.size(), get(i));
            }
        }
        return new JsonArray<>(array);
    }

    public void forEach(Consumer<T> action) {
        for (int i = 0; i < jsonArray.size(); i++) {
            action.accept(get(i));
        }
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        Object o = jsonArray.get(index);

        if (o instanceof JSONObject) {
            JsonObject r = new JsonObject((JSONObject) o);
            return (T) r;
        }
        if (o instanceof JSONArray) {
            JsonArray r = JsonArray.fromJSONArray((JSONArray) o);
            return (T) r;
        }
        return (T) o;
    }

    public int size() {
        return jsonArray.size();
    }

    public boolean isEmpty() {
        return this.filter(Objects::nonNull).jsonArray.isEmpty();
    }

    public List<T> toList() {
        List<T> result = new ArrayList<>(jsonArray.size());
        for (int i = 0; i < jsonArray.size(); i++) {
            result.add(i, get(i));
        }
        return result;
    }

    public Set<T> toSet() {
        HashSet<T> result = new HashSet<>(jsonArray.size());
        for (int i = 0; i < jsonArray.size(); i++) {
            result.add(get(i));
        }
        return result;
    }

    public Boolean add(T element) {
        return this.jsonArray.add(element);
    }

    public Boolean addAll(JsonArray<T> newArray) {
        return this.jsonArray.addAll(newArray.toList());
    }

    @Override
    public String toString() {
        return jsonArray.toString();
    }
}

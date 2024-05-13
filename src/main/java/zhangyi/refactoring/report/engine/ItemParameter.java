package zhangyi.refactoring.report.engine;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ItemParameter implements Parameter {
    @Override
    public void fill(HttpServletRequest request) {
        ItemParameter itemPara = this;
        for (Item item : itemPara.getItems()) {
            String[] values = request.getParameterValues(item.getName());
            item.setValues(values);
        }
    }

    @Override
    public String getName() {
        return null;
    }

    public void setValue(String[] value) {

    }

    public List<Item> getItems() {
        return null;
    }
}

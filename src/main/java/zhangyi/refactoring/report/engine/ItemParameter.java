package zhangyi.refactoring.report.engine;

import zhangyi.refactoring.report.servlet.ServletHttpRequest;

import java.util.List;

public class ItemParameter implements Parameter {
    @Override
    public String getName() {
        return null;
    }

    public void setValue(String[] value) {

    }

    public List<Item> getItems() {
        return null;
    }

    public void fill(ServletHttpRequest request) {
        ItemParameter itemPara = this;
        for (Item item : itemPara.getItems()) {
            String[] values = request.getParameterValues(item.getName());
            item.setValues(values);
        }
    }
}

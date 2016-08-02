package thoughtworks.refactoring.report.web;

import thoughtworks.refactoring.report.engine.*;
import thoughtworks.refactoring.report.servlet.ServletHttpRequest;

public class ParameterCollector {
    private void fillParameters(ServletHttpRequest request, ParameterGraph parameterGraph) {
        for (Parameter para : parameterGraph.getParmaeters()) {
            if (para instanceof SimpleParameter) {
                SimpleParameter simplePara = (SimpleParameter) para;
                String[] values = request.getParameterValues(para.getName());
                simplePara.setValue(values);
            } else {
                if (para instanceof ItemParameter) {
                    ItemParameter itemPara = (ItemParameter) para;
                    for (Item item : itemPara.getItems()) {
                        String[] values = request.getParameterValues(item.getName());
                        item.setValues(values);
                    }
                } else {
                    TableParameter tablePara = (TableParameter) para;
                    String[] rows =
                            request.getParameterValues(tablePara.getRowName());
                    String[] columns =
                            request.getParameterValues(tablePara.getColumnName());
                    String[] dataCells =
                            request.getParameterValues(tablePara.getDataCellName());

                    int columnSize = columns.length;
                    for (int i = 0; i < rows.length; i++) {
                        for (int j = 0; j < columns.length; j++) {
                            TableParameterElement element = new TableParameterElement();
                            element.setRow(rows[i]);
                            element.setColumn(columns[j]);
                            element.setDataCell(dataCells[columnSize * i + j]);
                            tablePara.addElement(element);
                        }
                    }
                }
            }
        }
    }
}

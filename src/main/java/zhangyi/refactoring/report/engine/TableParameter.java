package zhangyi.refactoring.report.engine;

public class TableParameter implements Parameter {
    private String rowName;
    private String columnName;
    private String dataCellName;

    @Override
    public void fill(ParameterRequest request) {
        TableParameter tablePara = this;
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

    @Override
    public String getName() {
        return null;
    }

    public String getRowName() {
        return rowName;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getDataCellName() {
        return dataCellName;
    }

    public void addElement(TableParameterElement element) {


    }
}

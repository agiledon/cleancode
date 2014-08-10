package thoughtworks.refactoring.report.engine;

public class TableParameter implements Parameter {
    private String rowName;
    private String columnName;
    private String dataCellName;

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

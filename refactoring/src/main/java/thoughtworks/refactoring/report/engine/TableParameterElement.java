package thoughtworks.refactoring.report.engine;

public class TableParameterElement {
    private String row;
    private String column;
    private String dataCell;

    public void setRow(String row) {
        this.row = row;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public void setDataCell(String dataCell) {
        this.dataCell = dataCell;
    }
}

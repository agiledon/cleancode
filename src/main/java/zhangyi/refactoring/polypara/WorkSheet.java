package zhangyi.refactoring.polypara;

public class WorkSheet{
    public void fillHeader() {
        Header header = createHeader();
        for (String title:header.getTitles()) {
            fillCell(title);
        }
    }

    public void fillBody() {
        CellGroup cellGroup = createCellGroup();
        for (Cell cell:cellGroup.getCells()) {
            fillCell(cell.getText());
        }
    }

    private Header createHeader() {
        return new Header();
    }

    private CellGroup createCellGroup() {
        return new CellGroup();
    }

    private void fillCell(String text) {
        // ignore for demonstration
    }
}

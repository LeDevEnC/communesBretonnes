package controller;

import javafx.event.EventHandler;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import model.TableauModel;

public class TableRowFactory implements Callback<TableView<TableauModel>, TableRow<TableauModel>> {
    private final ControllerDataSee controller;

    public TableRowFactory(ControllerDataSee controller) {
        this.controller = controller;
    }

    @Override
    public TableRow<TableauModel> call(TableView<TableauModel> tableView) {
        TableRow<TableauModel> row = new TableRow<>();
        row.setOnMouseClicked(new TableRowClickHandler(row));
        return row;
    }

    public class TableRowClickHandler implements EventHandler<MouseEvent> {
        private final TableRow<TableauModel> row;

        public TableRowClickHandler(TableRow<TableauModel> row) {
            this.row = row;
        }

        @Override
        public void handle(MouseEvent event) {
            if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                TableauModel clickedRow = row.getItem();
                controller.lineClicked(clickedRow.getLaCommune());
            }
        }
    }
}
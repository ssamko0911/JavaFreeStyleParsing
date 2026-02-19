package entities;

import entities.libraryItems.LibraryItem;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class BookTableModel extends AbstractTableModel {
    private static final String[] COLUMN_NAMES = {"Item type", "Title", "OCLC", "Genre", "ISBN"};
    private List<LibraryItem> records = new ArrayList<>();

    @Override
    public int getRowCount() {
        return this.records.size();
    }

    @Override
    public int getColumnCount() {
        return BookTableModel.COLUMN_NAMES.length;
    }

    @Override
    public String getColumnName(int column) {
        return BookTableModel.COLUMN_NAMES[column];
    }

    @Override
    public Class<?> getColumnClass(int column) {
        return String.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        LibraryItem record = this.records.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> record.getRecordTypeAsString();
            case 1 -> record.getTitleAsString();
            case 2 -> record.getOclcNumber();
            case 3 -> record.getGenre();
            case 4 -> record.getIsbnAsString();
            default -> null;
        };
    }

    public void setRecords(List<? extends LibraryItem> records) {
        this.records = new ArrayList<>(records);
        fireTableDataChanged();
    }

    public LibraryItem getRecordAt(int row) {
        return this.records.get(row);
    }

    public void clearRecords() {
        this.records = new  ArrayList<>();
        fireTableDataChanged();
    }
}

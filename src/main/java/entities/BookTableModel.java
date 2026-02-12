package entities;

import entities.libraryItems.LibraryBookRecord;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class BookTableModel extends AbstractTableModel {
    private static final String[] COLUMN_NAMES = {"Title", "Author(s)", "Publication Year", "Genre"};
    private List<LibraryBookRecord> records = new ArrayList<>();

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
        // Hardcoded publicationYear;
        if (column == 2) {
            return Integer.class;
        }

        return String.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        LibraryBookRecord record = this.records.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> record.getTitleAsString();
            case 1 -> record.getAuthorsAsString();
            case 2 -> record.getPublicationYear();
            case 3 -> record.getGenre();
            default -> null;
        };
    }

    public void setRecords(List<LibraryBookRecord> records) {
        this.records = records;
        fireTableDataChanged();
    }

    public LibraryBookRecord getRecordAt(int row) {
        return this.records.get(row);
    }

    public void clearRecords() {
        this.records = new  ArrayList<>();
        fireTableDataChanged();
    }
}

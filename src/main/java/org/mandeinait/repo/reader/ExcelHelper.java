package org.mandeinait.repo.reader;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

public class ExcelHelper {

	public static void printRow(Row row) {
		System.out.println("------------RowNum:" + row.getRowNum() + "------------------");
		for (Cell cell : row) {

			System.out.print("<" + cell.getRowIndex() + "-" + cell.getColumnIndex() + ",val: " + readCellValue(cell)
					+ ", col: " + cell.getCellStyle().getFillForegroundColor() + ">");
		}
		System.out.println();
		System.out.println("------------------------------");
	}

	public static Object readCellValue(Cell cell) {
		CellType cellType = cell.getCellType();
		switch (cellType) {
		case NUMERIC:
			return cell.getNumericCellValue();
		case STRING:
			return cell.getStringCellValue();
		case FORMULA:
			return cell.getCellFormula();
		case BLANK:
			return "Blank";
		case BOOLEAN:
			return cell.getBooleanCellValue();
		case ERROR:
			return cell.getErrorCellValue();
		case _NONE:
		default:
			return "NONE";
		}
	}
}

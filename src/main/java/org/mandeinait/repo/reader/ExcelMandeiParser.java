package org.mandeinait.repo.reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.mandeinait.repo.model.Result;

public class ExcelMandeiParser {

	public ParsedFile parseFile(String filePath) throws IOException {
		List<CSVRecord> records = new ArrayList<>();
		Set<String> columns = new HashSet<>();
		Workbook wb = WorkbookFactory.create(new File(filePath));

		for (Sheet sheet : wb) {
			String sheetName = sheet.getSheetName();
			System.out.println("Parsing :" + sheetName);
			if (sheetName.contains("Punti")) {

			} else if (sheetName.contains("Marcatori")) {

			} else if (sheetName.contains("Risultati")) {
				ResultsParser.parse(sheet);
			} else {
				throw new IllegalArgumentException("Sheet name: " + sheetName + " not recognized!");
			}
//			for (Row row : sheet) {
//				System.out.println(
//						"\tFirst cell num " + row.getFirstCellNum() + " Last cell num " + row.getLastCellNum());
//				for (Cell cell : row) {
//					System.out.print("\t\tCell val " + readCellValue(cell) + " col "
//							+ cell.getCellStyle().getFillForegroundColor());
//				}
//			}
		}

		return new ParsedFile(filePath, records, columns);
	}

	private static Object readCellValue(Cell cell) {
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

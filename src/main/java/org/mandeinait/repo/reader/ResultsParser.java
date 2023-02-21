package org.mandeinait.repo.reader;

import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.mandeinait.repo.model.Team;

public class ResultsParser {

	public static void parse(Sheet sheet) {
		Date seasonStartDate = null;
		Date seasonStartEnd = null;
		// Check Header
		Row headerRow = sheet.getRow(0);
		String dataStrVal = headerRow.getCell(1).getStringCellValue();
		String resultStrVal = headerRow.getCell(2).getStringCellValue();
		if ("data".equalsIgnoreCase(dataStrVal) && "risultato".equalsIgnoreCase(resultStrVal)) {
			System.out.println("Header find! Row:");
		} else {
			throw new IllegalArgumentException("Not found Header for sheet " + sheet.getSheetName());
		}

		for (int i = 1; sheet.getLastRowNum() < i; i++) {
			Row row = sheet.getRow(i);
			Date date = row.getCell(1).getDateCellValue();

			if (seasonStartDate == null) {
				seasonStartDate = date;
			}

			double team1Goals = row.getCell(2).getNumericCellValue();
			short team1Color = row.getCell(2).getCellStyle().getFillForegroundColor();
			Team t1 = new Team((int) team1Goals);
			double team2Goals = row.getCell(3).getNumericCellValue();
			short team2Color = row.getCell(3).getCellStyle().getFillForegroundColor();
			Team t2 = new Team((int) team2Goals);

			boolean completed = false;
			for (int j = 4; row.getLastCellNum() < j && !completed; j++) {
				Cell currentCell = row.getCell(j);
				if (currentCell.getCellType() == CellType.BLANK) {
					completed = true;
				} else {
					String playerName = currentCell.getStringCellValue();
					
					if(currentCell.getCellStyle().getFillBackgroundColor() == team1Color) {
						t1.addPlayer(null);
					}
				}
			}

			System.out.println("\tFirst cell num " + row.getFirstCellNum() + " Last cell num " + row.getLastCellNum());
		}
	}
}

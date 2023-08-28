package org.mandeinait.repo.reader;

import java.io.File;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.mandeinait.repo.model.PlayerRepository;
import org.mandeinait.repo.model.Season;

public class ExcelMandeiParser {

	public void parseFile(File inputFile, PlayerRepository playerRepo) throws IOException {
		Workbook wb = WorkbookFactory.create(inputFile);
		String seasoname = inputFile.getName();
		for (Sheet sheet : wb) {
			String sheetName = sheet.getSheetName();
			System.out.println("***** Starting Parsing season:" + seasoname + " sheet " + sheetName + "*****");
			Season season = new Season(seasoname);

			if (sheetName.contains("Punti")) {

			} else if (sheetName.contains("Marcatori")) {

			} else if (sheetName.contains("Risultati")) {
				try {
					ResultsParser.parse(season, playerRepo, sheet);
				} catch (Exception e) {
					System.out.println("Error during parsing sheet Risultati of season " + seasoname + ". Err Msg:"
							+ e.getMessage());
					e.printStackTrace();
				}
			} else {
				System.out.println("Sheet name: " + sheetName + " not recognized!");
			}
		}
		System.out.println("***** Complete Parsing season:" + seasoname + "*****");
	}
}

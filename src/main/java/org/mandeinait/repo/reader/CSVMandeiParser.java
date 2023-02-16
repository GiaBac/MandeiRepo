package org.mandeinait.repo.reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVMandeiParser {

	/* Parse the 3 files: Standing, TopScorer and Result */
	public void parseFiles(InputParameters inputParams) throws IOException {

		ParsedFile parsedStandingCSV = parseCsvFile(inputParams.getStandingPath());
		validateStanding(parsedStandingCSV);

		ParsedFile parsedTopScorerCSV = parseCsvFile(inputParams.getTopScorerPath());
		validateTopScorer(parsedTopScorerCSV);

		ParsedFile parsedresultCSV = parseCsvFile(inputParams.getResultPath());
		validateResult(parsedresultCSV);
	}

	private void validateResult(ParsedFile parsedResultCSV) {
		System.out.println("Result loaded correctly numCol:" + parsedResultCSV.getColumns() + " numRecord:"
				+ parsedResultCSV.getRecords());
	}

	private void validateTopScorer(ParsedFile parsedTopScorerCSV) {
		System.out.println("TopScorer loaded correctly numCol:" + parsedTopScorerCSV.getColumns() + " numRecord:"
				+ parsedTopScorerCSV.getRecords());
	}

	private void validateStanding(ParsedFile parsedStandingCSV) {
		System.out.println("Standig loaded correctly numCol:" + parsedStandingCSV.getColumns() + " numRecord:"
				+ parsedStandingCSV.getRecords());
	}

	private ParsedFile parseCsvFile(String filePath) throws IOException {

		final Reader inputReader;
		try {
			inputReader = new FileReader(filePath);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open file: " + filePath + " Error reported:  " + e);
			return null;
		}

		final List<CSVRecord> records;
		final Set<String> columns;
		try {
			CSVParser parser = CSVFormat.EXCEL.withFirstRecordAsHeader().withDelimiter(';').parse(inputReader);
			records = parser.getRecords();
			columns = parser.getHeaderMap().keySet();
		} catch (IOException e) {
			System.out.println("Cannot parse file: " + filePath + " Error reported:  " + e);
			throw e;
		}

		return new ParsedFile(filePath, records, columns);
	}

}

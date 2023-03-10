package org.mandeinait.repo.main;

import java.io.IOException;

import org.mandeinait.repo.reader.CSVMandeiParser;
import org.mandeinait.repo.reader.ExcelMandeiParser;
import org.mandeinait.repo.reader.InputParameters;
import org.mandeinait.repo.reader.ParserException;

public class Main {

	public static void main(String[] args) {
		System.out.println("Starting!\nVersion 1.0");

		InputParameters inputParams;

//		try {
//			inputParams = InputParameters.parseInputParam(args);
//		} catch (ParserException e1) {
//			stopProgram(1);
//			return;
//		}
//
//		System.out.println("Standing File: " + inputParams.getStandingPath());
//		System.out.println("Top Scorer File: " + inputParams.getTopScorerPath());
//		System.out.println("Result File: " + inputParams.getResultPath());

//		CSVMandeiParser mandeiParser = new CSVMandeiParser();
		ExcelMandeiParser mandeiParser = new ExcelMandeiParser();

		try {
			mandeiParser.parseFile("C:\\Users\\giacomobachi\\MyTemp\\Stagione Estiva 2015.xls");
		} catch (IOException e) {
			System.out.print("Error parsing the file: " + e.getMessage());
			e.printStackTrace();
			stopProgram(1);
			return;
		}

	}

	private static void stopProgram(int status) {
		System.out.println("Terminated!");
		System.exit(0);
	}
}

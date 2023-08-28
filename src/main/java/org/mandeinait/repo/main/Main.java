package org.mandeinait.repo.main;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;

import org.mandeinait.repo.model.Player;
import org.mandeinait.repo.model.PlayerRepository;
import org.mandeinait.repo.model.Result;
import org.mandeinait.repo.model.Season;
import org.mandeinait.repo.reader.ExcelMandeiParser;

public class Main {

	public static void main(String[] args) {
		System.out.println("Starting!\nVersion 1.0");

		ExcelMandeiParser mandeiParser = new ExcelMandeiParser();
//		String fileName = "D:\\GIACOMO\\DEV\\mandeiFile\\03_Stagione Invernale 2017.xls";

		File folderFiles = new File("D:\\GIACOMO\\DEV\\mandeiFile\\mandeiXls");
//		File folderFiles = new File("D:\\GIACOMO\\DEV\\mandeiFile\\test");
		File[] files = folderFiles.listFiles();

		PlayerRepository pr = new PlayerRepository();

		for (File file : files) {
			try {
				mandeiParser.parseFile(file, pr);
			} catch (IOException e) {
				System.out.print("Error parsing the file " + file.getName() + ": " + e.getMessage());
				e.printStackTrace();
			}
		}

		System.out.print("Dump player list:" + pr.toString());

		calcAficionados(pr);

		stopProgram(0);
	}

	private static void stopProgram(int status) {
		if (status != 0) {
			System.out.println("Terminated with error!");
		} else {
			System.out.println("Terminated correctly!");
		}

		System.exit(status);
	}

	private static void calcAficionados(PlayerRepository pr) {
		System.out.println("Aficionados:");

		for (Player p : pr.getAll()) {
			int numMatch = 0;
			int numSeason = 0;

			for (Entry<Season, List<Result>> ms : p.getMatches().entrySet()) {
				numSeason++;
				numMatch += ms.getValue().size();
			}

			System.out.println(p.toString() + ",NumMatch, " + numMatch + ",Num Season, " + numSeason);
		}

	}

}

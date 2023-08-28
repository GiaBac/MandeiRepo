package org.mandeinait.repo.reader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.math3.util.Pair;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.mandeinait.repo.model.Player;
import org.mandeinait.repo.model.PlayerRepository;
import org.mandeinait.repo.model.Result;
import org.mandeinait.repo.model.Season;
import org.mandeinait.repo.model.Team;

public class ResultsParser {

	public static void parse(Season season, PlayerRepository playerRepo, Sheet sheet) {

		System.out.println("***** Parsing Risultati:" + season + " sheet " + sheet.getSheetName() + "*****");

		Date seasonStartDate = null;
		Date seasonEndDate = null;
		// Check Header
		int headerRowIdx = -1;
		for (Row row : sheet) {
			Cell cellDate = row.getCell(1);
			Cell cellResult = row.getCell(2);
			if (cellDate != null || cellResult != null) {
				String dataStrVal = cellDate.getStringCellValue();
				String resultStrVal = cellResult.getStringCellValue();
				if ("data".equalsIgnoreCase(dataStrVal) && "risultato".equalsIgnoreCase(resultStrVal)) {
					System.out.println("Header find! Row:" + row.getRowNum());
					headerRowIdx = row.getRowNum();
					break;
				}
			}
		}

		if (headerRowIdx == -1) {
			throw new IllegalArgumentException(
					"Not found Risultati Header for season " + season + ", sheet " + sheet.getSheetName());
		}

		List<Result> matches = new ArrayList<>();

		for (int rowIdx = headerRowIdx + 1; rowIdx < sheet.getLastRowNum(); rowIdx++) {
			Row currRow = sheet.getRow(rowIdx);

			ExcelHelper.printRow(currRow);

			Cell dateCell = currRow.getCell(1);

			if (dateCell == null) {
				System.out.println("Unexpected Date in Risultati for sheet " + sheet.getSheetName() + " in season "
						+ season + ". Row number: " + rowIdx);
				continue;
			}

			if (dateCell.getCellType() == CellType.STRING && "Gol totali:".equals(dateCell.getStringCellValue())) {
				System.out.println("Complete parsing sheet Risultati for season " + season);
				return;
			}

			Date currentDate = dateCell.getDateCellValue();

			if (seasonStartDate == null) {
				seasonStartDate = currentDate;
			}

			Optional<InitTeam> team1Init = initTeam1(currRow);
			Pair<Integer, Optional<InitTeam>> team2Init = initTeam2(currRow);

			if (team1Init.isEmpty() && team2Init.getSecond().isEmpty()) {
				// No goal found, maybe rest day?
				System.out.println(
						"***** Complete parsing row " + currRow.getRowNum() + ": No goal found, maybe REST DAY");
				continue;
			}

			if (team1Init.isEmpty() || team2Init.getSecond().isEmpty()) {
				System.out
						.println("ERROR parsing row " + currRow.getRowNum() + ": one team size is empty. T1.isPresent:"
								+ team1Init.isPresent() + "T2.isPresent:" + team2Init.getSecond().isEmpty());
				continue;
			}

			short team1Color = team1Init.get().getTeamColor();
			Team t1 = team1Init.get().getTeam();

			final int idxStartPlayer = team2Init.getFirst();
			final short team2Color = team2Init.getSecond().get().getTeamColor();
			final Team t2 = team2Init.getSecond().get().getTeam();

			System.out.println("Team1 Color: " + team1Color + ",  Team2 Color: " + team2Color);

			Result result = new Result(currentDate, t1, t2, -1);

			for (int j = idxStartPlayer; j < currRow.getLastCellNum(); j++) {

				Cell currentCell = currRow.getCell(j);
				if (currentCell == null || currentCell.getCellType() == CellType.BLANK) {
					continue;
				}

				// TODO: parse goal scored
				String playerName = currentCell.getStringCellValue();

				if (currentCell.getCellStyle().getFillForegroundColor() == team1Color) {
					Player player = getOrCreatePlayer(season.getName(), playerRepo, playerName);
					t1.addPlayer(player);
				} else if (currentCell.getCellStyle().getFillForegroundColor() == team2Color) {
					Player player = getOrCreatePlayer(season.getName(), playerRepo, playerName);
					t2.addPlayer(player);
				}
			}

			System.out.println("***** Complete parsing row " + currRow.getRowNum() + ":" + result + " *****");
			boolean isValid = result.validate();
			if (isValid) {
				matches.add(result);
				addMatchToPlayer(season, result);
			}
		}

		seasonEndDate = matches.get(matches.size() - 1).getDate();

		System.out.println("***** Complete parsing Risultati for season " + season + ". StartDate:" + seasonStartDate
				+ ", EndDate:" + seasonEndDate + " *****");

		season.setStartDate(seasonStartDate);
		season.setEndDate(seasonEndDate);
		season.setMatches(matches);
		season.elaborateResults();
	}

	private static void addMatchToPlayer(Season season, Result match) {
		for (Player p : match.getT1().getPlayers()) {
			p.addMatch(season, match);
		}

		for (Player p : match.getT2().getPlayers()) {
			p.addMatch(season, match);
		}
	}

	private static Player getOrCreatePlayer(String seasonName, PlayerRepository playerRepo, String playerName) {
		Player player = playerRepo.get(playerName);
		if (player == null) {
			player = new Player(playerName, seasonName);
			playerRepo.add(player);
		}

		return player;
	}

	private static Optional<InitTeam> initTeam1(Row currRow) {
		Cell cell2 = currRow.getCell(2);
		if (cell2 == null || cell2.getCellType() != CellType.NUMERIC) {
			return Optional.empty();
		}

		double team1Goals = cell2.getNumericCellValue();
		short team1Color = cell2.getCellStyle().getFillForegroundColor();
		Team t1 = new Team((int) team1Goals);

		return Optional.of(new InitTeam(team1Color, t1));
	}

	private static Pair<Integer, Optional<InitTeam>> initTeam2(Row currRow) {
		Cell cell3 = currRow.getCell(3);
		final int idxStartPlayer;
		final short team2Color;
		final Team t2;
		if (cell3 == null || cell3.getCellType() != CellType.NUMERIC) {
			Cell cell4 = currRow.getCell(4);
			if (cell4 == null || cell4.getCellType() != CellType.NUMERIC) {
				return Pair.create(-1, Optional.empty());
			}
			double team2Goals = cell4.getNumericCellValue();
			team2Color = cell4.getCellStyle().getFillForegroundColor();
			t2 = new Team((int) team2Goals);
			idxStartPlayer = 5;
		} else {
			double team2Goals = cell3.getNumericCellValue();
			team2Color = cell3.getCellStyle().getFillForegroundColor();
			t2 = new Team((int) team2Goals);
			idxStartPlayer = 4;
		}

		return Pair.create(idxStartPlayer, Optional.of(new InitTeam(team2Color, t2)));
	}
}

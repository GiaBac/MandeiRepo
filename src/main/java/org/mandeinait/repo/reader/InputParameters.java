package org.mandeinait.repo.reader;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class InputParameters {

	private final String standingPath;
	private final String topScorerPath;
	private final String resultPath;

	public InputParameters(String standingPath, String topScorerPath, String resultPath) {
		this.standingPath = standingPath;
		this.topScorerPath = topScorerPath;
		this.resultPath = resultPath;
	}

	public String getStandingPath() {
		return standingPath;
	}

	public String getTopScorerPath() {
		return topScorerPath;
	}

	public String getResultPath() {
		return resultPath;
	}

	public static InputParameters parseInputParam(String[] args) throws ParserException {

		Options options = new Options();

		try {

			CommandLineParser parser = new DefaultParser();
			CommandLine cmd = parser.parse(options, args);

			if (cmd.getArgs().length != 3) {
				System.out.println("Wrong number of input arguments! You must insert 3 arguments. You insert "
						+ args.length + " instead.");
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("java -jar mandeiRepo.jar <standingFile> <topScorerFile> <resultFile>", options);
				System.out.println(
						"Sample of usage: java -jar mandeiRepo.jar \"Invernale 2020-2021 - ClassificaPunti_Invernale2019.csv\" \"Invernale 2022-2023 - ClassificaMarcatori_2022-2023.csv\" \"Invernale 2022-2023 - Risultati_2022-2023.csv\"");

				throw new ParserException();
			}

			final String standingPath = cmd.getArgs()[0];
			final String topScorerPath = cmd.getArgs()[1];
			final String resultPath = cmd.getArgs()[1];

			return new InputParameters(standingPath, topScorerPath, resultPath);
		} catch (ParseException e) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("java -jar mandeiRepo.jar <standingFile> <topScorerFile> <resultFile>", options);

			throw new ParserException();
		}
	}
}
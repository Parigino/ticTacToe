package dev.aielloparigino.tris.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.aielloparigino.tris.db.GameRepository;
import dev.aielloparigino.tris.db.GameSteps;
import dev.aielloparigino.tris.db.GameStepsRepository;
import dev.aielloparigino.tris.db.Games;

@Service("service")
public class DBRepository {

	@Autowired
	private GameRepository gameRepository;
	@Autowired
	private GameStepsRepository gameStepsRepository;

	public Games insertNewGame(String numPlayer, String grid) {

		Games newGame = new Games();
		// FIXME: inutile salvare con 'x'
		newGame.setGridConf(grid + "x" + grid);
		newGame.setPlayerNum(Integer.valueOf(numPlayer));

		newGame.setIdgames(Integer.valueOf("999"));
		return newGame = gameRepository.save(newGame);
	}

	public GameSteps insertStep(String gameID, String step, String playerTurn) {

		GameSteps gameStep = new GameSteps();
		gameStep.setIdgame(Integer.valueOf(gameID));
		// gameStep.setIdGameStep(Integer.valueOf(gameID));
		gameStep.setStep(step.toString());
		gameStep.setPlayerTurn(Integer.valueOf(playerTurn));
		// TODO: vericare salvataggio transazione/riga su db?

		return gameStepsRepository.save(gameStep);
	}

	public String loadStep(String gameID) {

		return gameStepsRepository.findLastStep(gameID);
	}
}

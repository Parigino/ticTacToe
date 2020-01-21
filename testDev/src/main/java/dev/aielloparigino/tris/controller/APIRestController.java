package dev.aielloparigino.tris.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.aielloparigino.tris.model.DBRepository;
import dev.aielloparigino.tris.model.WinChecker;

@RestController
public class APIRestController {

	@Autowired
	private DBRepository dbRepository;

	@GetMapping("/startGame")
	public String startGame(@RequestParam(value = "numPlayer", required = true) String numPlayer,
			@RequestParam(value = "grid", required = true) String grid) {

		return String.valueOf(dbRepository.insertNewGame(numPlayer, grid).getIdgames());
	}

	@GetMapping("/checkWin")
	public Integer checkWin(@RequestParam(value = "gameID") String gameID,
			@RequestParam(value = "gridArray") String gridArray,
			@RequestParam(value = "playerTurn") String playerTurn) {
		String[] step = gridArray.split(",");

		// save state on DB
		dbRepository.insertStep(gameID, gridArray, playerTurn);

		return WinChecker.checkWin(step);

	}

}

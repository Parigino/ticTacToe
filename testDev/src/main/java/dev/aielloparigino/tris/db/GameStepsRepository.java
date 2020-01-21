package dev.aielloparigino.tris.db;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GameStepsRepository extends CrudRepository<GameSteps, Integer> {

	// Get lastStep + numPlayer + gridConf
	@Query(value = "SELECT games.gridConf,games.playerNum,gamesteps.playerTurn,gamesteps.step\r\n"
			+ "FROM tictactoe.games INNER JOIN tictactoe.gamesteps\r\n" + "ON games.idgames = gamesteps.idgame\r\n"
			+ "where idgame=?1\r\n" + "order by idgamesteps desc\r\n" + "LIMIT 1", nativeQuery = true)
	String findLastStep(@Param("idGame") String idGame);
}

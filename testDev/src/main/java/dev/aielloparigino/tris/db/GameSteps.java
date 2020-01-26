package dev.aielloparigino.tris.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gamesteps")
public class GameSteps {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idGameSteps;

	// @ManyToOne
	// @JoinColumn(name = "idgames", referencedColumnName = "idgames")
	// FIXME: external key?
	private Integer idgame;

	private String step;

	private Integer playerTurn;

	public Integer getIdGameStep() {
		return idGameSteps;
	}

	public void setIdGameStep(Integer idGameStep) {
		this.idGameSteps = idGameStep;
	}

	public Integer getIdgame() {
		return idgame;
	}

	public void setIdgame(Integer idgame) {
		this.idgame = idgame;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public Integer getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(Integer playerTurn) {
		this.playerTurn = playerTurn;
	}
}

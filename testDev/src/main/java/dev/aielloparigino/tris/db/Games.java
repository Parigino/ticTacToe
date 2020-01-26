package dev.aielloparigino.tris.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "games")
public class Games {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idgames;

	private String gridConf;

	private Integer playerNum;

	// @OneToMany(mappedBy = "idgames")
	// private Set<GameSteps> gameSteps;

	public Integer getIdgames() {
		return idgames;
	}

	public void setIdgames(Integer id) {
		this.idgames = id;
	}

	public String getGridConf() {
		return gridConf;
	}

	public void setGridConf(String gridConf) {
		this.gridConf = gridConf;
	}

	public Integer getPlayerNum() {
		return playerNum;
	}

	public void setPlayerNum(Integer playerNum) {
		this.playerNum = playerNum;
	}
}

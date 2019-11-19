package edu.dipa.lunatech.ImdbApplication.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the title_episode database table.
 * 
 */
@Entity
@Table(name="title_episode")
@NamedQuery(name="TitleEpisode.findAll", query="SELECT t FROM TitleEpisode t")
public class TitleEpisode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer tconst;

	@Column(name="episode_number")
	private Integer episodeNumber;

	@Column(name="parent_tconst")
	private Integer parentTconst;

	@Column(name="season_number")
	private Integer seasonNumber;

	public TitleEpisode() {
	}

	public Integer getEpisodeNumber() {
		return this.episodeNumber;
	}

	public void setEpisodeNumber(Integer episodeNumber) {
		this.episodeNumber = episodeNumber;
	}

	public Integer getParentTconst() {
		return this.parentTconst;
	}

	public void setParentTconst(Integer parentTconst) {
		this.parentTconst = parentTconst;
	}

	public Integer getSeasonNumber() {
		return this.seasonNumber;
	}

	public void setSeasonNumber(Integer seasonNumber) {
		this.seasonNumber = seasonNumber;
	}

	public Integer getTconst() {
		return this.tconst;
	}

	public void setTconst(Integer tconst) {
		this.tconst = tconst;
	}

}
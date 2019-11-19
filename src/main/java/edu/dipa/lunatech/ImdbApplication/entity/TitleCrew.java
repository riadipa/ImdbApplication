package edu.dipa.lunatech.ImdbApplication.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the title_crew database table.
 * 
 */
@Entity
@Table(name="title_crew")
@NamedQuery(name="TitleCrew.findAll", query="SELECT t FROM TitleCrew t")
public class TitleCrew implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer tconst;

	private String directors;

	private String writers;

	public TitleCrew() {
	}

	public String getDirectors() {
		return this.directors;
	}

	public void setDirectors(String directors) {
		this.directors = directors;
	}

	public Integer getTconst() {
		return this.tconst;
	}

	public void setTconst(Integer tconst) {
		this.tconst = tconst;
	}

	public String getWriters() {
		return this.writers;
	}

	public void setWriters(String writers) {
		this.writers = writers;
	}

}
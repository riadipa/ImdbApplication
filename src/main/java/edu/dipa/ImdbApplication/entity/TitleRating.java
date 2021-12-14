package edu.dipa.ImdbApplication.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the title_ratings database table.
 * 
 */
@Entity
@Table(name="title_ratings")
@NamedQuery(name="TitleRating.findAll", query="SELECT t FROM TitleRating t")
public class TitleRating implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer tconst;

	@Column(name="average_rating")
	private double averageRating;

	@Column(name="num_votes")
	private Integer numVotes;

	public TitleRating() {
	}

	public double getAverageRating() {
		return this.averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public Integer getNumVotes() {
		return this.numVotes;
	}

	public void setNumVotes(Integer numVotes) {
		this.numVotes = numVotes;
	}

	public Integer getTconst() {
		return this.tconst;
	}

	public void setTconst(Integer tconst) {
		this.tconst = tconst;
	}

}
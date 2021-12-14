package edu.dipa.ImdbApplication.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the title_principals database table.
 * 
 */
@Entity
@Table(name="title_principals")
@NamedQuery(name="TitlePrincipal.findAll", query="SELECT t FROM TitlePrincipal t")
public class TitlePrincipal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer nconst;

	private String category;

	private String characters;

	private String job;

	private Integer ordering;

	private Integer tconst;

	public TitlePrincipal() {
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCharacters() {
		return this.characters;
	}

	public void setCharacters(String characters) {
		this.characters = characters;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Integer getNconst() {
		return this.nconst;
	}

	public void setNconst(Integer nconst) {
		this.nconst = nconst;
	}

	public Integer getOrdering() {
		return this.ordering;
	}

	public void setOrdering(Integer ordering) {
		this.ordering = ordering;
	}

	public Integer getTconst() {
		return this.tconst;
	}

	public void setTconst(Integer tconst) {
		this.tconst = tconst;
	}

}
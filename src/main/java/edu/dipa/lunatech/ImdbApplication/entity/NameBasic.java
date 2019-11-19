package edu.dipa.lunatech.ImdbApplication.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the name_basics database table.
 * 
 */
@Entity
@Table(name="name_basics")
@NamedQuery(name="NameBasic.findAll", query="SELECT n FROM NameBasic n")
public class NameBasic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer nconst;

	@Column(name="birth_year")
	private Integer birthYear;

	@Column(name="death_year")
	private Integer deathYear;

	@Column(name="known_for_titles")
	private String knownForTitles;


	@Column(name="ns_soundex")
	private String nsSoundex;

	@Column(name="primary_name", unique = true)
	private String primaryName;

	@Column(name="primary_profession")
	private String primaryProfession;

	@Column(name="s_soundex")
	private String sSoundex;

	@Column(name="sn_soundex")
	private String snSoundex;

	public NameBasic() {
	}

	public Integer getBirthYear() {
		return this.birthYear;
	}

	public void setBirthYear(Integer birthYear) {
		this.birthYear = birthYear;
	}

	public Integer getDeathYear() {
		return this.deathYear;
	}

	public void setDeathYear(Integer deathYear) {
		this.deathYear = deathYear;
	}

	public String getKnownForTitles() {
		return this.knownForTitles;
	}

	public void setKnownForTitles(String knownForTitles) {
		this.knownForTitles = knownForTitles;
	}

	public Integer getNconst() {
		return this.nconst;
	}

	public void setNconst(Integer nconst) {
		this.nconst = nconst;
	}

	public String getNsSoundex() {
		return this.nsSoundex;
	}

	public void setNsSoundex(String nsSoundex) {
		this.nsSoundex = nsSoundex;
	}

	public String getPrimaryName() {
		return this.primaryName;
	}

	public void setPrimaryName(String primaryName) {
		this.primaryName = primaryName;
	}

	public String getPrimaryProfession() {
		return this.primaryProfession;
	}

	public void setPrimaryProfession(String primaryProfession) {
		this.primaryProfession = primaryProfession;
	}

	public String getSSoundex() {
		return this.sSoundex;
	}

	public void setSSoundex(String sSoundex) {
		this.sSoundex = sSoundex;
	}

	public String getSnSoundex() {
		return this.snSoundex;
	}

	public void setSnSoundex(String snSoundex) {
		this.snSoundex = snSoundex;
	}

}
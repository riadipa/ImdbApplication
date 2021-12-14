package edu.dipa.ImdbApplication.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the title_basics database table.
 * 
 */
@Entity
@Table(name="title_basics")
@NamedQuery(name="TitleBasic.findAll", query="SELECT t FROM TitleBasic t")
public class TitleBasic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer tconst;

	@Column(name="end_year")
	private Integer endYear;

	@Column(name="genres")
	private String genres;

	@Column(name="is_adult")
	private Boolean isAdult;

	@Column(name="original_title")
	private String originalTitle;

	@Column(name="primary_title")
	private String primaryTitle;

	@Column(name="runtime_minutes")
	private Integer runtimeMinutes;

	@Column(name="start_year")
	private Integer startYear;

	@Column(name="t_soundex")
	private String tSoundex;

	@Column(name="title_type")
	private String titleType;

	public TitleBasic() {
	}

	public Integer getEndYear() {
		return this.endYear;
	}

	public void setEndYear(Integer endYear) {
		this.endYear = endYear;
	}

	public String getGenres() {
		return this.genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public Boolean getIsAdult() {
		return this.isAdult;
	}

	public void setIsAdult(Boolean isAdult) {
		this.isAdult = isAdult;
	}

	public String getOriginalTitle() {
		return this.originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public String getPrimaryTitle() {
		return this.primaryTitle;
	}

	public void setPrimaryTitle(String primaryTitle) {
		this.primaryTitle = primaryTitle;
	}

	public Integer getRuntimeMinutes() {
		return this.runtimeMinutes;
	}

	public void setRuntimeMinutes(Integer runtimeMinutes) {
		this.runtimeMinutes = runtimeMinutes;
	}

	public Integer getStartYear() {
		return this.startYear;
	}

	public void setStartYear(Integer startYear) {
		this.startYear = startYear;
	}

	public String getTSoundex() {
		return this.tSoundex;
	}

	public void setTSoundex(String tSoundex) {
		this.tSoundex = tSoundex;
	}

	public Integer getTconst() {
		return this.tconst;
	}

	public void setTconst(Integer tconst) {
		this.tconst = tconst;
	}

	public String getTitleType() {
		return this.titleType;
	}

	public void setTitleType(String titleType) {
		this.titleType = titleType;
	}

}
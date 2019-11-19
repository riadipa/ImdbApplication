package edu.dipa.lunatech.ImdbApplication.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the title_akas database table.
 * 
 */
@Entity
@Table(name="title_akas")
@NamedQuery(name="TitleAka.findAll", query="SELECT t FROM TitleAka t")
public class TitleAka implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer titleId;

	@Column(name="is_original_title")
	private Boolean isOriginalTitle;

	private String attributes;

	private String language;

	private Integer ordering;

	private String region;

	@Column(name="t_soundex")
	private String tSoundex;

	private String title;

	private String types;

	public TitleAka() {
	}

	public String getAttributes() {
		return this.attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public Boolean getIsOriginalTitle() {
		return this.isOriginalTitle;
	}

	public void setIsOriginalTitle(Boolean isOriginalTitle) {
		this.isOriginalTitle = isOriginalTitle;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Integer getOrdering() {
		return this.ordering;
	}

	public void setOrdering(Integer ordering) {
		this.ordering = ordering;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getTSoundex() {
		return this.tSoundex;
	}

	public void setTSoundex(String tSoundex) {
		this.tSoundex = tSoundex;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getTitleId() {
		return this.titleId;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}

	public String getTypes() {
		return this.types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

}
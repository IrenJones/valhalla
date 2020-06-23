package com.valhallaproject.runes.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "formulas")
@Data
public class Formula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToMany
	@JoinTable(
			name = "formula_rune",
			joinColumns = @JoinColumn(table = "formulas", name = "formula_id"),
			inverseJoinColumns = @JoinColumn(table = "runes", name = "rune_id"))
	private List<Rune> runes = new LinkedList<>();

	private String description;

	private FormulaCategory category;
}

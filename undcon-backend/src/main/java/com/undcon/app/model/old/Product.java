package com.undcon.app.model.old;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="produtos")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "descricao")
	private String name;
	
	@Column(name = "unidade")
	private String unit;
	
	@Column(name = "precoCompra")
	private double purchasePrice;
	
	@Column(name = "precoVenda")
	private double salePrice;

	@Column(name = "estoque")
	private long stock;
	
	@Column(name = "estoqueMinimo")
	private long stockMin;
}

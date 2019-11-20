package com.undcon.app.model.old;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.undcon.app.model.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="vendas")
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idVendas", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "dataVenda", nullable = false)
	private Timestamp date;
	
	@Column(name = "valorTotal", nullable = false)
	private String valorTotal;
	
	@Column(name = "desconto", nullable = false)
	private String desconto;
	
	@Column(name = "faturado", nullable = false)
	private int faturado;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Person fornecedor;
	
	@ManyToOne
	@JoinColumn(name = "usuarios_id", nullable = false)
	private UserEntity usuario;
	
	@ManyToOne
	@JoinColumn(name = "lancamentos_id", nullable = false)
	private Lancamento lancamento;
}
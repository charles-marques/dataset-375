package br.com.levymoreira.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Pojo representando a tabela local de pagamento do banco de dados, 
 * configurado com annotation para usar o hibernate.
 * 
 * @author levy
 * @since 23/12/2011
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "LOCAL_PAGAMENTO")
public class LocalPagamento implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(name="descricao", nullable=false, length=50)
    private String descricao;
    
    //Constructor default
    
    public LocalPagamento(){}
    
    //Constructor
    
    public LocalPagamento(String descricao){
    	this.descricao = descricao;
    }
    
    //Getters and Setters
    
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
    
	
}

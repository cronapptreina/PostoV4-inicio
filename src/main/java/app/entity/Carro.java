package app.entity;

import java.io.*;
import javax.persistence.*;
import java.util.*;
import javax.xml.bind.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFilter;
import cronapi.rest.security.CronappSecurity;


/**
 * Classe que representa a tabela CARRO
 * @generated
 */
@Entity
@Table(name = "\"CARRO\"")
@XmlRootElement
@CronappSecurity
@JsonFilter("app.entity.Carro")
public class Carro implements Serializable {

  /**
   * UID da classe, necessário na serialização
   * @generated
   */
  private static final long serialVersionUID = 1L;

  /**
   * @generated
   */
  @Id
  @Column(name = "id", nullable = false, insertable=true, updatable=true)
  private java.lang.String id = UUID.randomUUID().toString().toUpperCase();

  /**
  * @generated
  */
  @Column(name = "placa", nullable = false, unique = false, insertable=true, updatable=true)
  
  private java.lang.String placa;

  /**
  * @generated
  */
  @Column(name = "ano", nullable = false, unique = false, insertable=true, updatable=true)
  
  private java.lang.Integer ano;

  /**
  * @generated
  */
  @ManyToOne
  @JoinColumn(name="fk_user", nullable = false, referencedColumnName = "id", insertable=true, updatable=true)
  
  private User user;

  /**
  * @generated
  */
  @ManyToOne
  @JoinColumn(name="fk_marcaFIPE", nullable = false, referencedColumnName = "id", insertable=true, updatable=true)
  
  private MarcaFIPE marcaFIPE;

  /**
  * @generated
  */
  @ManyToOne
  @JoinColumn(name="fk_modeloFIPE", nullable = false, referencedColumnName = "id", insertable=true, updatable=true)
  
  private ModeloFIPE modeloFIPE;

  /**
   * Construtor
   * @generated
   */
  public Carro(){
  }


  /**
   * Obtém id
   * return id
   * @generated
   */
  
  public java.lang.String getId(){
    return this.id;
  }

  /**
   * Define id
   * @param id id
   * @generated
   */
  public Carro setId(java.lang.String id){
    this.id = id;
    return this;
  }

  /**
   * Obtém placa
   * return placa
   * @generated
   */
  
  public java.lang.String getPlaca(){
    return this.placa;
  }

  /**
   * Define placa
   * @param placa placa
   * @generated
   */
  public Carro setPlaca(java.lang.String placa){
    this.placa = placa;
    return this;
  }

  /**
   * Obtém ano
   * return ano
   * @generated
   */
  
  public java.lang.Integer getAno(){
    return this.ano;
  }

  /**
   * Define ano
   * @param ano ano
   * @generated
   */
  public Carro setAno(java.lang.Integer ano){
    this.ano = ano;
    return this;
  }

  /**
   * Obtém user
   * return user
   * @generated
   */
  
  public User getUser(){
    return this.user;
  }

  /**
   * Define user
   * @param user user
   * @generated
   */
  public Carro setUser(User user){
    this.user = user;
    return this;
  }

  /**
   * Obtém marcaFIPE
   * return marcaFIPE
   * @generated
   */
  
  public MarcaFIPE getMarcaFIPE(){
    return this.marcaFIPE;
  }

  /**
   * Define marcaFIPE
   * @param marcaFIPE marcaFIPE
   * @generated
   */
  public Carro setMarcaFIPE(MarcaFIPE marcaFIPE){
    this.marcaFIPE = marcaFIPE;
    return this;
  }

  /**
   * Obtém modeloFIPE
   * return modeloFIPE
   * @generated
   */
  
  public ModeloFIPE getModeloFIPE(){
    return this.modeloFIPE;
  }

  /**
   * Define modeloFIPE
   * @param modeloFIPE modeloFIPE
   * @generated
   */
  public Carro setModeloFIPE(ModeloFIPE modeloFIPE){
    this.modeloFIPE = modeloFIPE;
    return this;
  }

  /**
   * @generated
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Carro object = (Carro)obj;
    if (id != null ? !id.equals(object.id) : object.id != null) return false;
    return true;
  }

  /**
   * @generated
   */
  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

}

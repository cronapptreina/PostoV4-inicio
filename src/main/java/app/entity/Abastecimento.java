package app.entity;

import java.io.*;
import javax.persistence.*;
import java.util.*;
import javax.xml.bind.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFilter;
import cronapi.rest.security.CronappSecurity;


/**
 * Classe que representa a tabela ABASTECIMENTO
 * @generated
 */
@Entity
@Table(name = "\"ABASTECIMENTO\"")
@XmlRootElement
@CronappSecurity
@JsonFilter("app.entity.Abastecimento")
public class Abastecimento implements Serializable {

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
  @Temporal(TemporalType.DATE)
  @Column(name = "data", nullable = false, unique = false, insertable=true, updatable=true)
  
  private java.util.Date data = new Date();

  /**
  * @generated
  */
  @Column(name = "km", nullable = false, unique = false, precision=6, scale=2, insertable=true, updatable=true)
  
  private java.lang.Double km;

  /**
  * @generated
  */
  @Column(name = "valor", nullable = false, unique = false, precision=6, scale=2, insertable=true, updatable=true)
  
  private java.lang.Double valor;

  /**
  * @generated
  */
  @Column(name = "precoLitro", nullable = false, unique = false, precision=4, scale=2, insertable=true, updatable=true)
  
  private java.lang.Double precoLitro;

  /**
  * @generated
  */
  @ManyToOne
  @JoinColumn(name="fk_carro", nullable = false, referencedColumnName = "id", insertable=true, updatable=true)
  
  private Carro carro;

  /**
  * @generated
  */
  @ManyToOne
  @JoinColumn(name="fk_posto", nullable = false, referencedColumnName = "id", insertable=true, updatable=true)
  
  private Posto posto;

  /**
   * Construtor
   * @generated
   */
  public Abastecimento(){
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
  public Abastecimento setId(java.lang.String id){
    this.id = id;
    return this;
  }

  /**
   * Obtém data
   * return data
   * @generated
   */
  
  public java.util.Date getData(){
    return this.data;
  }

  /**
   * Define data
   * @param data data
   * @generated
   */
  public Abastecimento setData(java.util.Date data){
    this.data = data;
    return this;
  }

  /**
   * Obtém km
   * return km
   * @generated
   */
  
  public java.lang.Double getKm(){
    return this.km;
  }

  /**
   * Define km
   * @param km km
   * @generated
   */
  public Abastecimento setKm(java.lang.Double km){
    this.km = km;
    return this;
  }

  /**
   * Obtém valor
   * return valor
   * @generated
   */
  
  public java.lang.Double getValor(){
    return this.valor;
  }

  /**
   * Define valor
   * @param valor valor
   * @generated
   */
  public Abastecimento setValor(java.lang.Double valor){
    this.valor = valor;
    return this;
  }

  /**
   * Obtém precoLitro
   * return precoLitro
   * @generated
   */
  
  public java.lang.Double getPrecoLitro(){
    return this.precoLitro;
  }

  /**
   * Define precoLitro
   * @param precoLitro precoLitro
   * @generated
   */
  public Abastecimento setPrecoLitro(java.lang.Double precoLitro){
    this.precoLitro = precoLitro;
    return this;
  }

  /**
   * Obtém carro
   * return carro
   * @generated
   */
  
  public Carro getCarro(){
    return this.carro;
  }

  /**
   * Define carro
   * @param carro carro
   * @generated
   */
  public Abastecimento setCarro(Carro carro){
    this.carro = carro;
    return this;
  }

  /**
   * Obtém posto
   * return posto
   * @generated
   */
  
  public Posto getPosto(){
    return this.posto;
  }

  /**
   * Define posto
   * @param posto posto
   * @generated
   */
  public Abastecimento setPosto(Posto posto){
    this.posto = posto;
    return this;
  }

  /**
   * @generated
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Abastecimento object = (Abastecimento)obj;
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

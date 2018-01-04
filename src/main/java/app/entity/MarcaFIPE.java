package app.entity;

import java.io.*;
import javax.persistence.*;
import java.util.*;
import javax.xml.bind.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFilter;
import cronapi.rest.security.CronappSecurity;


/**
 * Classe que representa a tabela MARCAFIPE
 * @generated
 */
@Entity
@Table(name = "\"MARCAFIPE\"")
@XmlRootElement
@CronappSecurity
@JsonFilter("app.entity.MarcaFIPE")
public class MarcaFIPE implements Serializable {

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
  private java.lang.Integer id;

  /**
  * @generated
  */
  @Column(name = "fipe_name", nullable = false, unique = false, insertable=true, updatable=true)
  
  private java.lang.String fipe_name;

  /**
   * Construtor
   * @generated
   */
  public MarcaFIPE(){
  }


  /**
   * Obtém id
   * return id
   * @generated
   */
  
  public java.lang.Integer getId(){
    return this.id;
  }

  /**
   * Define id
   * @param id id
   * @generated
   */
  public MarcaFIPE setId(java.lang.Integer id){
    this.id = id;
    return this;
  }

  /**
   * Obtém fipe_name
   * return fipe_name
   * @generated
   */
  
  public java.lang.String getFipe_name(){
    return this.fipe_name;
  }

  /**
   * Define fipe_name
   * @param fipe_name fipe_name
   * @generated
   */
  public MarcaFIPE setFipe_name(java.lang.String fipe_name){
    this.fipe_name = fipe_name;
    return this;
  }

  /**
   * @generated
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    MarcaFIPE object = (MarcaFIPE)obj;
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

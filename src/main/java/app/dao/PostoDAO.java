package app.dao;

import app.entity.*;
import java.util.*;
import org.springframework.stereotype.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.*;
import org.springframework.transaction.annotation.*; 

/**
 * Realiza operação de Create, Read, Update e Delete no banco de dados.
 * Os métodos de create, edit, delete e outros estão abstraídos no JpaRepository
 * 
 * @see org.springframework.data.jpa.repository.JpaRepository
 * 
 * @generated
 */
@Repository("PostoDAO")
@Transactional(transactionManager="app-TransactionManager")
public interface PostoDAO extends JpaRepository<Posto, java.lang.String> {

  /**
   * Obtém a instância de Posto utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Instância relacionada com o filtro indicado
   * @generated
   */    
  @Query("SELECT entity FROM Posto entity WHERE entity.id = :id")
  public Posto findOne(@Param(value="id") java.lang.String id);

  /**
   * Remove a instância de Posto utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Quantidade de modificações efetuadas
   * @generated
   */    
  @Modifying
  @Query("DELETE FROM Posto entity WHERE entity.id = :id")
  public void delete(@Param(value="id") java.lang.String id);


      
  /**
   * OneToMany Relation - Searchable fields - General search (Only strings fields)
   * @generated
   */
  @Query("SELECT entity FROM Abastecimento entity WHERE entity.posto.id = :id AND (:search = :search)")
  public Page<Abastecimento> findAbastecimentoGeneralSearch(@Param(value="search") java.lang.String search, @Param(value="id") java.lang.String id, Pageable pageable);

  /** 
   * OneToMany Relation - Searchable fields - Specific search
   * @generated
   */
  @Query("SELECT entity FROM Abastecimento entity WHERE entity.posto.id = :id AND (:data is null OR entity.data = :data) AND (:km is null OR entity.km = :km) AND (:valor is null OR entity.valor = :valor) AND (:precoLitro is null OR entity.precoLitro = :precoLitro)")
  public Page<Abastecimento> findAbastecimentoSpecificSearch(@Param(value="id") java.lang.String id, @Param(value="data") java.util.Date data, @Param(value="km") java.lang.Double km, @Param(value="valor") java.lang.Double valor, @Param(value="precoLitro") java.lang.Double precoLitro, Pageable pageable);

  /**
   * OneToMany Relation
   * @generated
   */
  @Query("SELECT entity FROM Abastecimento entity WHERE entity.posto.id = :id")
  public Page<Abastecimento> findAbastecimento(@Param(value="id") java.lang.String id, Pageable pageable);
  /**
   * ManyToOne Relation
   * @generated
   */
  @Query("SELECT entity.carro FROM Abastecimento entity WHERE entity.posto.id = :id")
  public Page<Carro> listCarro(@Param(value="id") java.lang.String id, Pageable pageable);

  /**
   * ManyToOne Relation Delete
   * @generated
   */
  @Modifying
  @Query("DELETE FROM Abastecimento entity WHERE entity.posto.id = :instanceId AND entity.carro.id = :relationId")
  public int deleteCarro(@Param(value="instanceId") java.lang.String instanceId, @Param(value="relationId") java.lang.String relationId);

}

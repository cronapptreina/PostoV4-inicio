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
@Repository("AbastecimentoDAO")
@Transactional(transactionManager="app-TransactionManager")
public interface AbastecimentoDAO extends JpaRepository<Abastecimento, java.lang.String> {

  /**
   * Obtém a instância de Abastecimento utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Instância relacionada com o filtro indicado
   * @generated
   */    
  @Query("SELECT entity FROM Abastecimento entity WHERE entity.id = :id")
  public Abastecimento findOne(@Param(value="id") java.lang.String id);

  /**
   * Remove a instância de Abastecimento utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Quantidade de modificações efetuadas
   * @generated
   */    
  @Modifying
  @Query("DELETE FROM Abastecimento entity WHERE entity.id = :id")
  public void delete(@Param(value="id") java.lang.String id);



    
  /**
   * Searchable fields - General search (Only strings fields)
   * @generated
   */
  @Query("SELECT entity FROM Abastecimento entity WHERE :search = :search")
  public Page<Abastecimento> generalSearch(@Param(value="search") java.lang.String search, Pageable pageable);

  /**
   * Searchable fields - Specific search
   * @generated
   */
  @Query("SELECT entity FROM Abastecimento entity WHERE (:data is null OR entity.data = :data) AND (:km is null OR entity.km = :km) AND (:valor is null OR entity.valor = :valor) AND (:precoLitro is null OR entity.precoLitro = :precoLitro)")
  public Page<Abastecimento> specificSearch(@Param(value="data") java.util.Date data, @Param(value="km") java.lang.Double km, @Param(value="valor") java.lang.Double valor, @Param(value="precoLitro") java.lang.Double precoLitro, Pageable pageable);
  
  /**
   * Foreign Key carro
   * @generated
   */
  @Query("SELECT entity FROM Abastecimento entity WHERE entity.carro.id = :id")
  public Page<Abastecimento> findAbastecimentosByCarro(@Param(value="id") java.lang.String id, Pageable pageable);

  /**
   * Foreign Key posto
   * @generated
   */
  @Query("SELECT entity FROM Abastecimento entity WHERE entity.posto.id = :id")
  public Page<Abastecimento> findAbastecimentosByPosto(@Param(value="id") java.lang.String id, Pageable pageable);

}

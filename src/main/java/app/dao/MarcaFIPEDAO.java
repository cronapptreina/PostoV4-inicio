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
@Repository("MarcaFIPEDAO")
@Transactional(transactionManager="app-TransactionManager")
public interface MarcaFIPEDAO extends JpaRepository<MarcaFIPE, java.lang.Integer> {

  /**
   * Obtém a instância de MarcaFIPE utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Instância relacionada com o filtro indicado
   * @generated
   */    
  @Query("SELECT entity FROM MarcaFIPE entity WHERE entity.id = :id")
  public MarcaFIPE findOne(@Param(value="id") java.lang.Integer id);

  /**
   * Remove a instância de MarcaFIPE utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Quantidade de modificações efetuadas
   * @generated
   */    
  @Modifying
  @Query("DELETE FROM MarcaFIPE entity WHERE entity.id = :id")
  public void delete(@Param(value="id") java.lang.Integer id);



  /**
   * OneToMany Relation
   * @generated
   */
  @Query("SELECT entity FROM Carro entity WHERE entity.marcaFIPE.id = :id")
  public Page<Carro> findCarro(@Param(value="id") java.lang.Integer id, Pageable pageable);

}

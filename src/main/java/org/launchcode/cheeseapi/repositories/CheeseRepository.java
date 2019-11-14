package org.launchcode.cheeseapi.repositories;

import org.launchcode.cheeseapi.models.Cheese;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface CheeseRepository extends JpaRepository<Cheese, Long> {
  @Query(
      value = "SELECT * from cheeses AS c JOIN menu_cheeses AS mc ON mc.cheese_id = c.id " +
          "WHERE mc.menu_id = :menuId",
      nativeQuery = true)
  /**
   * using native SQL query √ (single join)
   *
   * Hibernate:
   *     select
   *         *
   *     from
   *         cheeses
   *     join
   *         menu_cheeses
   *             on menu_cheeses.cheese_id = cheeses.id
   *     where
   *         menu_cheeses.menu_id = ?
   * Hibernate: (eager fetch)
   *     select
   *         category0_.id as id1_0_0_,
   *         category0_.name as name2_0_0_
   *     from
   *         categories category0_
   *     where
   *         category0_.id=?
   */

  //  @Query(
  //      "SELECT c FROM Cheese c JOIN c.menus cm WHERE cm.id = :menuId")

  /**
   * using JPQL ✗ (multiple inner joins)
   * <p>
   * Hibernate:
   * select (*)
   * cheese0_.id as id1_2_,
   * cheese0_.category_id as category4_2_,
   * cheese0_.description as descript2_2_,
   * cheese0_.name as name3_2_
   * from
   * cheeses cheese0_
   * inner join
   * menu_cheeses menus1_
   * on cheese0_.id=menus1_.cheese_id
   * inner join
   * menus menu2_
   * on menus1_.menu_id=menu2_.id
   * where
   * menu2_.id=?
   * <p>
   * Hibernate: (eager fetch)
   * select
   * category0_.id as id1_0_0_,
   * category0_.name as name2_0_0_
   * from
   * categories category0_
   * where
   * category0_.id=?
   */
  List<Cheese> findByMenuId(@Param("menuId") long menuId);
}

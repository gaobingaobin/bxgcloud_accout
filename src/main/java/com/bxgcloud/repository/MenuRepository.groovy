package com.bxgcloud.repository

import com.bxgcloud.model.Menu
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query

/**
 * Created by gaobin on 2016/8/15.
 */
interface MenuRepository extends JpaRepository<Menu, Integer> , JpaSpecificationExecutor {
  @Query("select a from Menu a where a.parentMenu.id is null order by a.sort")
  List<Menu> findByParentMenuIsNull();
  @Query("select a from Menu a where a.parentMenu.id = ?1 order by a.sort")
  List<Menu> findByParentMenu(Integer menu);
}

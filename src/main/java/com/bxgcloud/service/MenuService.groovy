package com.bxgcloud.service

import com.bxgcloud.model.Menu
import com.bxgcloud.repository.MenuRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import javax.servlet.http.HttpServletRequest
import java.text.ParseException

/**
 * Created by gaobin on 2016/8/15.
 */
@Service
class MenuService {
    @Autowired
    private MenuRepository menuRepository;

/**
 * 页面加载所有菜单
 *
 * @param request
 * @return
 * @throws java.text.ParseException
 */
    @Transactional
    public List<Menu> findAllMenu() throws ParseException {
        List<Menu> parentMenus = menuRepository.findByParentMenuIsNull();
        for (int i = 0; i < parentMenus.size(); i++) {
            Menu menu = parentMenus.get(i);
            List<Menu> subMenus = menuRepository.findByParentMenu(menu.getId());
            if (subMenus.size() > 0) {
                menu.setChildrenMenus(subMenus);
            }

        }
        return parentMenus;
    }
}

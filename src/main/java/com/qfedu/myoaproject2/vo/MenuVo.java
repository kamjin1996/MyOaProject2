package com.qfedu.myoaproject2.vo;

import com.qfedu.myoaproject2.pojo.Resource;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.*;

/**
 * 准备左侧菜单资源，MenuVo类表示一级菜单和多个子菜单
 */
@Data
@Accessors(chain = true)
public class MenuVo {

    private Resource parent;
    private List<Resource> childrens;

    private MenuVo(Resource parent) {
        this.parent = parent;
    }

    MenuVo() {
    }

    /**
     * 遍历resources，当parentid是-1，将此resouce赋值给parent，
     * 并记录当前resouc的id，并拿这个id和后边的resocu的parentid比较，
     * 如果符合则说明是此菜单的二级菜单，将此菜单添加到childs中
     * 组装了一个menuvo对象完成，在将此对象添加到提前准备好的menuvo类型的list中。
     *
     * @param resources 父级与子级菜单集合
     * @return 返回list泛型Menuvo
     */
    public static List<MenuVo> createMenuList(List<Resource> resources) {
        List<MenuVo> menuVos = Collections.emptyList();
        resources.stream()
                .filter(resource -> resource.getParentid() == -1 && resource.getType() != 2)
                .forEach(resource -> {
                    MenuVo menuVo = new MenuVo(resource);
                    List<Resource> childList = Collections.emptyList();
                    Integer parentId = resource.getId();
                    resources.forEach(resource1 -> {
                        if (Objects.equals(parentId, resource1.getParentid())) {
                            childList.add(resource1);
                        }
                        menuVo.setChildrens(childList);
                    });
                    menuVos.add(menuVo);
                });
        return menuVos;
    }
}

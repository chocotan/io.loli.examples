package io.loli.example.ibatis.controller;

import io.loli.example.ibatis.model.Clazz;
import io.loli.example.ibatis.service.IClazzService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@RequestMapping(value = "clazz")
@Controller
public class ClazzController {
    @Autowired
    private IClazzService clazzService;

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "list")
    public String list(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int limit,
            Model model) {

        PageBounds p = new PageBounds(page, limit);

        List list = clazzService.findAll(p);
        // 获得结果集条总数
        PageList pageList = (PageList) list;

        model.addAttribute("page", pageList);
        return "clazzList";

    }

    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(@ModelAttribute Clazz clazz, Model model) {
        if (clazz != null) {
            if (clazz.getId() != null) {
                clazz = clazzService.findById(clazz.getId());

                model.addAttribute("clazz", clazz);
            }
        }

        model.addAttribute("clazz", clazz);
        return "clazzForm";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute Clazz clazz,
            RedirectAttributes attributes) {
        if (clazz != null) {
            if (clazz.getId() == null) {
                // 新增
                clazzService.insert(clazz);
                attributes.addFlashAttribute("message", "添加[" + clazz.getName()
                        + "]成功");
            } else {
                // 修改
                clazzService.update(clazz);
                attributes.addFlashAttribute("message", "修改[" + clazz.getName()
                        + "]成功");
            }

        }

        return "redirect:list";
    }

    @RequestMapping(value = "delete")
    public String delete(@RequestParam(value = "id", required = true) Long id,
            RedirectAttributes attributes) {

        clazzService.deleteById(id);
        attributes.addFlashAttribute("message", "删除成功");

        return "redirect:list";
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "search")
    public String search(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int limit,
            @RequestParam(value = "q", defaultValue = "") String q, Model model) {

        PageBounds p = new PageBounds(page, limit);

        List list = clazzService.findByName("%" + q + "%", p);
        // 获得结果集条总数
        PageList pageList = (PageList) list;
        if (q != null) {
            model.addAttribute("q", q);
        }

        model.addAttribute("page", pageList);
        return "clazzList";

    }

}

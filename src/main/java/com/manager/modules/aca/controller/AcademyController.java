package com.manager.modules.aca.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manager.modules.aca.entity.AcademyEntity;
import com.manager.modules.aca.service.AcademyService;
import com.manager.common.utils.PageUtils;
import com.manager.common.utils.R;



/**
 * 学院
 *
 * @author ""
 * @email ""
 * @date 2023-03-30 10:14:35
 */
@RestController
@RequestMapping("aca/academy")
public class AcademyController {
    @Autowired
    private AcademyService academyService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = academyService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 字典列表
     */
    @RequestMapping("/select")
    public R select(){
        List<AcademyEntity> list = academyService.list();
        return R.ok().put("list", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{value}")
    public R info(@PathVariable("value") Long value){
		AcademyEntity academy = academyService.getById(value);

        return R.ok().put("academy", academy);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AcademyEntity academy){
		academyService.save(academy);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AcademyEntity academy){
		academyService.updateById(academy);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] values){
		academyService.removeByIds(Arrays.asList(values));

        return R.ok();
    }

}

package com.manager.modules.club.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manager.modules.club.entity.ClubBudgetEntity;
import com.manager.modules.club.service.ClubBudgetService;
import com.manager.common.utils.PageUtils;
import com.manager.common.utils.R;



/**
 * 社团经费
 *
 * @author ""
 * @email ""
 * @date 2023-03-29 22:00:19
 */
@RestController
@RequestMapping("club/clubbudget")
public class ClubBudgetController {
    @Autowired
    private ClubBudgetService clubBudgetService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = clubBudgetService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/select")
    public R select(@RequestParam Map<String, Object> params){
        PageUtils page = clubBudgetService.select(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		ClubBudgetEntity clubBudget = clubBudgetService.getById(id);

        return R.ok().put("clubBudget", clubBudget);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ClubBudgetEntity clubBudget){
        clubBudget.setCreateTime(new Date());
		clubBudgetService.save(clubBudget);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ClubBudgetEntity clubBudget){
		clubBudgetService.updateWithNum(clubBudget);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		clubBudgetService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

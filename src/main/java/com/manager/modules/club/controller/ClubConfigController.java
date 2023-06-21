package com.manager.modules.club.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manager.modules.club.entity.ClubConfigEntity;
import com.manager.modules.club.service.ClubConfigService;
import com.manager.common.utils.PageUtils;
import com.manager.common.utils.R;



/**
 * 社团注册/审核控制
 *
 * @author ""
 * @email ""
 * @date 2023-03-30 10:11:06
 */
@RestController
@RequestMapping("club/clubconfig")
public class ClubConfigController {
    @Autowired
    private ClubConfigService clubConfigService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = clubConfigService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		ClubConfigEntity clubConfig = clubConfigService.getById(id);

        return R.ok().put("clubConfig", clubConfig);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ClubConfigEntity clubConfig){
		clubConfigService.save(clubConfig);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ClubConfigEntity clubConfig){
		clubConfigService.updateById(clubConfig);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		clubConfigService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

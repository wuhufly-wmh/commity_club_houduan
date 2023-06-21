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

import com.manager.modules.club.entity.ClubUserEntity;
import com.manager.modules.club.service.ClubUserService;
import com.manager.common.utils.PageUtils;
import com.manager.common.utils.R;



/**
 * 社团成员
 *
 * @author ""
 * @email ""
 * @date 2023-03-30 10:11:06
 */
@RestController
@RequestMapping("club/clubuser")
public class ClubUserController {
    @Autowired
    private ClubUserService clubUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = clubUserService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/select")
    public R select(@RequestParam Map<String, Object> params){
        PageUtils page = clubUserService.select(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		ClubUserEntity clubUser = clubUserService.getById(id);

        return R.ok().put("clubUser", clubUser);
    }

    /**
     * 同意入会
     */
    @RequestMapping("/success/{id}")
    public R success(@PathVariable("id") Long id){
        ClubUserEntity clubUserEntity = new ClubUserEntity();
        clubUserEntity.setId(id);
        clubUserEntity.setStatus(1);
        clubUserService.updateById(clubUserEntity);
        return R.ok();
    }


    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ClubUserEntity clubUser){
		clubUserService.saveInfo(clubUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ClubUserEntity clubUser){
		clubUserService.updateById(clubUser);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		clubUserService.removeWithNum(Arrays.asList(ids));
        return R.ok();
    }

}

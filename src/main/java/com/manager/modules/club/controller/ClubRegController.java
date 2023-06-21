package com.manager.modules.club.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.manager.modules.club.entity.ClubRegEntity;
import com.manager.modules.club.service.ClubRegService;
import com.manager.common.utils.PageUtils;
import com.manager.common.utils.R;



/**
 * 注册申请
 *
 * @author ""
 * @email ""
 * @date 2023-03-30 17:12:27
 */
@RestController
@RequestMapping("club/clubreg")
public class ClubRegController {
    @Autowired
    private ClubRegService clubRegService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = clubRegService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		ClubRegEntity clubReg = clubRegService.getById(id);

        return R.ok().put("clubReg", clubReg);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ClubRegEntity clubReg){
		clubRegService.saveInfo(clubReg);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ClubRegEntity clubReg){
		clubRegService.updateById(clubReg);

        return R.ok();
    }
    /**
     * 同意
     */
    @GetMapping("/success/{id}")
    public R success(@PathVariable("id") Long id){
        clubRegService.success(id);

        return R.ok();
    }

    /**
     * 拒绝
     */
    @GetMapping("/reject/{id}")
    public R reject(@PathVariable("id") Long id){
        clubRegService.reject(id);

        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		clubRegService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

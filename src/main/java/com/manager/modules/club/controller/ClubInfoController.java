package com.manager.modules.club.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.manager.common.utils.ShiroUtils;
import com.manager.modules.club.entity.PieVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.manager.modules.club.entity.ClubInfoEntity;
import com.manager.modules.club.service.ClubInfoService;
import com.manager.common.utils.PageUtils;
import com.manager.common.utils.R;



/**
 * 社团信息
 *
 * @author ""
 * @email ""
 * @date 2023-03-29 20:59:18
 */
@RestController
@RequestMapping("club/clubinfo")
public class ClubInfoController {
    @Autowired
    private ClubInfoService clubInfoService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = clubInfoService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/mylist")
    public R mylist(@RequestParam Map<String, Object> params){
        params.put("acId", ShiroUtils.getUserEntity().getAcId());
        PageUtils page = clubInfoService.queryPage(params);

        return R.ok().put("page", page);
    }



    /**
     * 学院审核列表
     */
    @RequestMapping("/listAc")
    public R listAc(@RequestParam Map<String, Object> params){
        PageUtils page = clubInfoService.queryPageAc(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		ClubInfoEntity clubInfo = clubInfoService.getById(id);

        return R.ok().put("clubInfo", clubInfo);
    }
    /**
     * 社团信息
     */
    @RequestMapping("/getByUser")
    public R getByUser(){
        ClubInfoEntity clubInfo = clubInfoService.getByUser();

        return R.ok().put("clubInfo", clubInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ClubInfoEntity clubInfo){

		clubInfoService.saveInfo(clubInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ClubInfoEntity clubInfo){
		clubInfoService.updateById(clubInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		clubInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
    /**
     * 饼图数据
     */
    @GetMapping("/pie")
    public R pie(){
        List<PieVo> pie = clubInfoService.pie();
        return R.ok().put("pie",pie);
    }

}

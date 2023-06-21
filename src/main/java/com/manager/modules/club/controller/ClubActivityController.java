package com.manager.modules.club.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manager.modules.club.entity.ClubActivityEntity;
import com.manager.modules.club.service.ClubActivityService;
import com.manager.common.utils.PageUtils;
import com.manager.common.utils.R;



/**
 * 社团活动
 *
 * @author ""
 * @email ""
 * @date 2023-03-29 22:00:19
 */
@RestController
@RequestMapping("club/clubactivity")
public class ClubActivityController {
    @Autowired
    private ClubActivityService clubActivityService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = clubActivityService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 校级查看所有
     */
    @RequestMapping("/listAll")
    public R listAll(@RequestParam Map<String, Object> params){
        PageUtils page = clubActivityService.queryPageAll(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/select")
    public R select(@RequestParam Map<String, Object> params){
        PageUtils page = clubActivityService.select(params);

        return R.ok().put("page", page);
    }

    /**
     * 经费申请
     */
    @RequestMapping("/selectAll")
    public R selectAll(){
        List<ClubActivityEntity> list = clubActivityService.selectAll();

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		ClubActivityEntity clubActivity = clubActivityService.getById(id);

        return R.ok().put("clubActivity", clubActivity);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ClubActivityEntity clubActivity){
        clubActivity.setCreateTime(new Date());
		clubActivityService.save(clubActivity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ClubActivityEntity clubActivity){
		clubActivityService.updateWithNum(clubActivity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		clubActivityService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

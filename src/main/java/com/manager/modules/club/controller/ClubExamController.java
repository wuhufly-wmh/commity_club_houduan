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

import com.manager.modules.club.entity.ClubExamEntity;
import com.manager.modules.club.service.ClubExamService;
import com.manager.common.utils.PageUtils;
import com.manager.common.utils.R;



/**
 * 考核申请
 *
 * @author ""
 * @email ""
 * @date 2023-03-30 17:12:27
 */
@RestController
@RequestMapping("club/clubexam")
public class ClubExamController {
    @Autowired
    private ClubExamService clubExamService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = clubExamService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/select")
    public R select(@RequestParam Map<String, Object> params){
        PageUtils page = clubExamService.select(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		ClubExamEntity clubExam = clubExamService.getById(id);

        return R.ok().put("clubExam", clubExam);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ClubExamEntity clubExam){
		clubExamService.saveInfo(clubExam);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ClubExamEntity clubExam){
		clubExamService.updateById(clubExam);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		clubExamService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

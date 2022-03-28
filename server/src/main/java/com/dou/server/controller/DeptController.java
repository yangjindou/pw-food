package com.dou.server.controller;

import com.dou.server.model.Dept;
import com.dou.server.model.ResultEntity;
import com.dou.server.service.DeptService;
import com.dou.server.tag.BaseController;
import com.dou.server.tag.PassToken;
import com.dou.server.tag.ResultEnums;
import com.dou.server.utils.CommonUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author yangjd
 */
@RequestMapping("/dept")
@RestController
public class DeptController implements BaseController {

    @Resource
    private DeptService deptService;

    @PassToken
    @GetMapping("")
    public ResultEntity get(@RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer limit, Dept dept) {
        return setPage(deptService.get(page,limit,dept));
    }

    @PostMapping("")
    public ResultEntity add(Dept dept) throws Exception {
        if (CommonUtils.varIsBlank(dept.getName(),dept.getPassword())) {
            return error(ResultEnums.E_PARAM_MISS);
        }
        return setResult(deptService.add(dept));
    }

    @PutMapping("")
    public ResultEntity update(@RequestBody Dept dept) throws Exception {
        if (CommonUtils.varIsBlank(dept.getId())) {
            return error(ResultEnums.E_PARAM_MISS);
        }
        return setResult(deptService.update(dept));
    }

    @DeleteMapping("")
    @SuppressWarnings("unchecked")
    public ResultEntity delete(@RequestBody Map<String, Object> params) {
        List<Integer> ids = (List<Integer>) params.get("ids");
        if (CommonUtils.varIsBlank(ids)) {
            return error(ResultEnums.E_PARAM_MISS);
        }
        return setResult(deptService.delete(ids));
    }
}

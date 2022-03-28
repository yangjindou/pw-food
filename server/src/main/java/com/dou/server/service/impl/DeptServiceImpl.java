package com.dou.server.service.impl;

import com.dou.server.mapper.DeptMapper;
import com.dou.server.mapper.UserMapper;
import com.dou.server.model.Dept;
import com.dou.server.model.User;
import com.dou.server.service.DeptService;
import com.dou.server.service.UserService;
import com.dou.server.tag.ResultEnums;
import com.dou.server.utils.CommonUtils;
import com.dou.server.utils.RSAUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author yangjd
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Resource
    private DeptMapper deptMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserService userService;

    @Override
    public PageInfo<Dept> get(Integer page, Integer limit, Dept temp) {
        PageHelper.startPage(page, limit);
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if (!CommonUtils.varIsBlank(temp.getName())) {
            criteria.andLike("name",String.format("%%%s%%",temp.getName()));
        }
//        List<Dept> list = deptMapper.selectByExample(example);
        UserMapper mapper = userService.getMapper();
        System.out.println(mapper == userMapper);
        return new PageInfo<>();
    }

    @Override
    public ResultEnums add(Dept temp) throws Exception {
        Map<String, String> map = RSAUtils.encryptPass(temp.getPassword());
        temp.setPassword(map.get("password"));
        temp.setPublicKey(map.get("publicKey"));
        return deptMapper.insert(temp) > 0 ? ResultEnums.SUCCESS : ResultEnums.ERROR;
    }

    @Override
    public ResultEnums update(Dept temp) throws Exception {
        if (!CommonUtils.varIsBlank(temp.getPassword())) {
            Map<String, String> map = RSAUtils.encryptPass(temp.getPassword());
            temp.setPassword(map.get("password"));
            temp.setPublicKey(map.get("publicKey"));
        }
        return deptMapper.updateByPrimaryKeySelective(temp) > 0 ? ResultEnums.SUCCESS : ResultEnums.ERROR;
    }

    @Override
    public ResultEnums delete(List<Integer> ids) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id",ids);
        return deptMapper.deleteByExample(example) > 0 ? ResultEnums.SUCCESS : ResultEnums.ERROR;
    }
}

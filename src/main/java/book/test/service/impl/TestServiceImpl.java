package book.test.service.impl;

import book.test.mapper.TestMapper;
import book.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public String test() {
        System.err.println("数据库查询的结果为："+testMapper.test());
        return testMapper.test();
    }
}

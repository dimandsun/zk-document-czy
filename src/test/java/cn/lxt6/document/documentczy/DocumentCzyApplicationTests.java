package cn.lxt6.document.documentczy;

import cn.lxt6.document.www.service.IDocService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class DocumentCzyApplicationTests {
    @Autowired
    private IDocService docService;
    @Test
    void contextLoads() {
//        docService.md2Doc("E:\\work\\wd");
    }

}

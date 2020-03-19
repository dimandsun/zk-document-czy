package cn.lxt6.document.www.controll;

import cn.lxt6.document.www.service.IDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenzy
 * @description
 * @since 2020-03-19
 */
@RestController
public class DocController {
    @Autowired
    private IDocService docService;

    /**
     * 把在html文件里的接口写入数据库
     * @param filePath
     * @return
     */
    @GetMapping("/md2Doc")
    public String md2Doc(String filePath){

        return docService.md2Doc("E:\\work\\wd");
    }

}

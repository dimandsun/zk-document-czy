package cn.lxt6.document.www.controll;

import cn.lxt6.document.www.service.IDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
     * 把从md文档转换的html文件里的接口写入数据库
     * @return
     */
    @PostMapping("/md2Doc")
    public String md2Doc(@RequestPart("files") MultipartFile[] files){

        return docService.md2Doc(files);
    }

}

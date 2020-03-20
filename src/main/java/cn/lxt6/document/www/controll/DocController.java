package cn.lxt6.document.www.controll;

import cn.lxt6.document.www.controll.pojo.vo.ResultVO;
import cn.lxt6.document.www.dao.pojo.po.Doc;
import cn.lxt6.document.www.service.IDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author chenzy
 * @description
 * @since 2020-03-19
 */
@RequestMapping("/docs")
@RestController
public class DocController {
    @Autowired
    private IDocService docService;

    /**
     * 把从md文档转换的html文件里的接口写入数据库
     * @return
     */
    @PostMapping
    public ResultVO insertList(@RequestPart("files") MultipartFile[] files){
        return docService.md2Doc(files);
    }

    @GetMapping
    public ResultVO getList(Doc doc){
        return docService.getList(doc);
    }


}

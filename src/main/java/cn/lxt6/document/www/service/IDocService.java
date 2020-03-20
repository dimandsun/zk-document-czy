package cn.lxt6.document.www.service;

import cn.lxt6.document.www.controll.pojo.vo.ResultVO;
import cn.lxt6.document.www.dao.pojo.po.Doc;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author chenzy
 * @description
 * @since 2020-03-19
 */
public interface IDocService {
    ResultVO md2Doc(MultipartFile[] files);

    ResultVO getList(Doc queryDoc);
}

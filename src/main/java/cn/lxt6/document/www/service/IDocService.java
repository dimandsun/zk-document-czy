package cn.lxt6.document.www.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author chenzy
 * @description
 * @since 2020-03-19
 */
public interface IDocService {
    String md2Doc(MultipartFile[] files);
}

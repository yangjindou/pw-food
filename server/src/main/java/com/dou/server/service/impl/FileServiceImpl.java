package com.dou.server.service.impl;

import com.dou.server.mapper.DictMapper;
import com.dou.server.model.SysFile;
import com.dou.server.service.DictDataService;
import com.dou.server.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author yangjd
 */
@Service
@RequiredArgsConstructor
public class FileServiceImpl extends BaseServiceImpl<SysFile> implements FileService {

    private final DictMapper dictMapper;
    private final DictDataService dictDataService;

}
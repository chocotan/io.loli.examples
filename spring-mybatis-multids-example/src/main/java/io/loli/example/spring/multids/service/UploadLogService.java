package io.loli.example.spring.multids.service;

import io.loli.example.spring.multids.db.DataSource;
import io.loli.example.spring.multids.mapper.UploadLogMapper;
import io.loli.example.spring.multids.model.UploadLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UploadLogService {
    @Autowired
    private UploadLogMapper mapper;

    @DataSource("ztoDS")
    @Transactional
    public void insert(UploadLog log) {
        mapper.insertUploadLog(log);
    }
}

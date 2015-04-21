package io.loli.example.spring.multids.mapper;

import io.loli.example.spring.multids.model.UploadLog;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadLogMapper {
    @Insert("insert into BASE_UPLOAD_LOG(ID,USER_NAME,IP_ADDRESS) values (#{id}, #{userName}, #{ipAddress})")
    public void insertUploadLog(UploadLog log);
}

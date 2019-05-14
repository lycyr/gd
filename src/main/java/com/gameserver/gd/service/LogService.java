package com.gameserver.gd.service;

import com.gameserver.gd.entity.Log;
import com.gameserver.gd.entity.LogExample;
import com.gameserver.gd.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private LogMapper logMapper;

    @Autowired
    public LogService(LogMapper logMapper){
        this.logMapper = logMapper;
    }

    public boolean addLog(String message){
        Log log = new Log();
        log.setMessage(message);
        return logMapper.insertSelective(log) >0 ;
    }
}

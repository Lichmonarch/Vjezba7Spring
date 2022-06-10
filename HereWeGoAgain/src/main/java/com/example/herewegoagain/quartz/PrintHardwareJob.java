package com.example.herewegoagain.quartz;

import com.example.herewegoagain.hardware.Hardware;
import com.example.herewegoagain.hardware.Repository.HardwareJDBCRepository;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class PrintHardwareJob extends QuartzJobBean {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HardwareJDBCRepository hardwareRepository;


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        List<Hardware> hardwareList = new ArrayList<>();
        hardwareList.addAll(hardwareRepository.findAll());

        logger.info("hardware in stock:");
        for(int i=0;i<hardwareList.size();i++){
            if(hardwareList.get(i).getInStock()>0)
                logger.info("{} - in stock: {}", hardwareList.get(i).getName(), hardwareList.get(i).getInStock());
              //  System.out.println(hardwareList.get(i).getName() +  " - " + hardwareList.get(i).getInStock());
        }

    }

}
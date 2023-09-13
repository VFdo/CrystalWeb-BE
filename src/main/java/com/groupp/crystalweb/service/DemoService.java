package com.groupp.crystalweb.service;

import com.groupp.crystalweb.dto.request.DemoRequest;
import com.groupp.crystalweb.entity.Demo;
import com.groupp.crystalweb.repository.DemoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemoService {
    private final DemoRepository demoRepository;

    public DemoService(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    public Demo saveDemo(DemoRequest demoRequest){
        Demo demo = new Demo(
                demoRequest.refId(),
                demoRequest.demoString(),
                demoRequest.demoInt(),
                demoRequest.demoBool()
//                demoRequest.demoList()
        );
        return demoRepository.save(demo);
    }

    public List<Demo> getAllDemos() {
        return (List<Demo>) demoRepository.findAll();
    }

    public Demo getDemo(String id) {
        Optional<Demo> demo = demoRepository.findByRefId(id);
        if(demo.isPresent()){
            return demo.get();
        }
//        TODO: handle response
        return null;
    }

    public Demo update(String id, DemoRequest demoRequest) {
        Optional<Demo> demo = demoRepository.findByRefId(id);
        if(demo.isPresent()){
            Demo existingDemo = demo.get();
            existingDemo.setDemoString(demoRequest.demoString());
            existingDemo.setDemoInt(demoRequest.demoInt());
            existingDemo.setDemoBool(demoRequest.demoBool());
//            existingDemo.setDemoList(demoRequest.demoList());
            return demoRepository.save(existingDemo);
        }
        return null;
    }

    public long deleteDemo(String id) {
        return demoRepository.deleteByRefId(id);
    }
}

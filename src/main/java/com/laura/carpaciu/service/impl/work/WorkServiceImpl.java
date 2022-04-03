package com.laura.carpaciu.service.impl.work;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.laura.carpaciu.dao.interfaces.WorkRepository;
import com.laura.carpaciu.entity.work.Work;
import com.laura.carpaciu.services.WorkService;


public class WorkServiceImpl implements WorkService{
	private final WorkRepository workDao ;


    public WorkServiceImpl(WorkRepository workDao) {
		super();
		this.workDao = workDao;
	}


	@Override
    @Transactional
    public void createWork(Work work)

    		{ workDao.createWork(work);
    }


    @Override
    @Transactional
    public List<Work> findAllWorks(){

       return workDao.findAllWorks();
    }


    @Override
    @Transactional
    public List<Work> findWorkByName(String workDescription){

        return workDao.findWorkByName(workDescription);
    }

    @Override
    @Transactional(readOnly = true)
    public Work findWorkById(int id){


       return workDao.findWorkById(id)
                      .orElseThrow(() -> new RuntimeException("Labor id not found"));

    }
    @Override
    @Transactional
    public int updateWorkTimeAndDescription(double timedWork, String workDescription, int id){

        return workDao.updateWorkTimeAndDescription(timedWork, workDescription, id);

    }

    @Override
    @Transactional
    public int deleteWork(int id){

    	java.util.Optional<Work> work = workDao.findWorkById(id);

        if(work.isPresent()){
            return workDao.deleteWork(id);
        }

        throw new RuntimeException("Work not found");
    }


}

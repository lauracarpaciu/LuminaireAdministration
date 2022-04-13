package com.laura.carpaciu.convertor;

import com.laura.carpaciu.entity.order.ServiceOrder;
import com.laura.carpaciu.entity.order.WorkOrder;
import com.laura.carpaciu.entity.work.Work;

public class WorkConvertor {
	private WorkConvertor() {

	}

	public static WorkOrder convert(Work work, double workPrice, ServiceOrder order) {
		// TODO Auto-generated method stub
		return new WorkOrder(null, work.getWorkDescription(), work.getTimedWork(), workPrice, order, work.getCategory());
	}

}

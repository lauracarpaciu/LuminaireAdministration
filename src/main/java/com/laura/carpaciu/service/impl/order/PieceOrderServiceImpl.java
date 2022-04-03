package com.laura.carpaciu.service.impl.order;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.laura.carpaciu.convertor.PieceConvertor;
import com.laura.carpaciu.dao.interfaces.OrderRepository;
import com.laura.carpaciu.dao.interfaces.PieceOrderRepository;
import com.laura.carpaciu.dao.interfaces.PieceRepository;
import com.laura.carpaciu.entity.luminaire.Piece;
import com.laura.carpaciu.entity.order.PieceOrder;
import com.laura.carpaciu.entity.order.ServiceOrder;
import com.laura.carpaciu.errors.order.NotEnoughPartsException;
import com.laura.carpaciu.errors.order.PartOrderException;
import com.laura.carpaciu.services.PieceOrderService;
import com.laura.carpaciu.utility.OrderStatus;



public class PieceOrderServiceImpl implements PieceOrderService {
	private final PieceOrderRepository partOrderDao;
	private final PieceRepository partDao;
	private final OrderRepository orderDao;


	public PieceOrderServiceImpl(PieceOrderRepository partOrderDao, PieceRepository partDao, OrderRepository orderDao) {
		super();
		this.partOrderDao = partOrderDao;
		this.partDao = partDao;
		this.orderDao = orderDao;
	}

	@Override
	@Transactional
	public void addPartToServiceOrder(Piece part, ServiceOrder serviceOrder, int count) {

		if ((serviceOrder.getOrderStatus().toString().equals("CLOSE"))) {
			throw new PartOrderException("Order is CLOSED, can't add any more parts to it!");
		}

		Optional<PieceOrder> optPartOrder = partOrderDao.findPartOrderByPartName(part.getPartNumber(), serviceOrder);
		PieceOrder partServiceOrder = PieceConvertor.convert(part, serviceOrder, count);

		if ((count <= part.getCount())) {

			if (optPartOrder.empty() != null) {

				partOrderDao.createPieceOrder(partServiceOrder);

			} else {

				partOrderDao.updatePartOrderCount(optPartOrder.get().getId(), count);
			}

			int id = serviceOrder.getId();

			orderDao.updateOrderStatus(OrderStatus.READY, id);
			partDao.decreasePieceCount(count, part.getPartNumber());
			return;

		}

		throw new NotEnoughPartsException("Not enough parts in Warehouse!!! ");

	}

	@Override
	@Transactional
	public int deletePartFromServiceOrder(String partNumber, int count, ServiceOrder order) {

		if (order.getOrderStatus().toString().equals("CLOSE")) {
			throw new PartOrderException("Order is CLOSED can't remove parts from it!");
		}

		partDao.updatePieceCount(count, partNumber);
		return partOrderDao.deletePartFromServiceOrder(partNumber);

	}

}
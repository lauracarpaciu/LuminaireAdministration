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
	private final PieceOrderRepository pieceOrderRepository;
	private final PieceRepository pieceRepository;
	private final OrderRepository orderRepository;

	public PieceOrderServiceImpl(PieceOrderRepository pieceOrderRepository, PieceRepository pieceRepository,
			OrderRepository orderRepository) {
		super();
		this.pieceOrderRepository = pieceOrderRepository;
		this.pieceRepository = pieceRepository;
		this.orderRepository = orderRepository;
	}

	@Override
	@Transactional
	public void addPartToServiceOrder(Piece part, ServiceOrder serviceOrder, int count) {

		if ((serviceOrder.getOrderStatus().toString().equals("CLOSE"))) {
			throw new PartOrderException("Order is CLOSED, can't add any more parts to it!");
		}

		Optional<PieceOrder> optPartOrder = pieceOrderRepository.findByPartNumb(part.getPartNumber(), serviceOrder);
		PieceOrder partServiceOrder = PieceConvertor.convert(part, serviceOrder, count);

		if ((count <= part.getCount())) {

			if (optPartOrder.isPresent()) {

				pieceOrderRepository.create(partServiceOrder);

			} else {

				pieceOrderRepository.updatePartOrderCount(count, count);
			}

			Long id = serviceOrder.getId();

			orderRepository.update(OrderStatus.READY, id);
			pieceRepository.decreasePieceCount(count, part.getPartNumber());
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

		pieceRepository.updatePieceCount(count, partNumber);
		return pieceOrderRepository.delete(partNumber);

	}

}
package com.laura.carpaciu.service.impl.order;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laura.carpaciu.dao.interfaces.OrderRepository;
import com.laura.carpaciu.dao.interfaces.PieceRepository;
import com.laura.carpaciu.dto.ServiceOrderIdAndStatusDto;
import com.laura.carpaciu.entity.order.PieceOrder;
import com.laura.carpaciu.entity.order.ServiceOrder;
import com.laura.carpaciu.entity.order.WorkOrder;
import com.laura.carpaciu.errors.order.ClientNotSelectedException;
import com.laura.carpaciu.errors.order.LuminaireNotSelectedException;
import com.laura.carpaciu.errors.order.OrderIsClosedException;
import com.laura.carpaciu.invoice.PdfService;
import com.laura.carpaciu.services.InvoiceService;
import com.laura.carpaciu.services.OrderService;
import com.laura.carpaciu.util.TwoDigitsDouble;
import com.laura.carpaciu.utility.OrderStatus;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	private final PieceRepository pieceRepository;

	private final PdfService pdfService;

	private final InvoiceService invoiceService;

	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository, PieceRepository pieceRepository, PdfService pdfService,
			InvoiceService invoiceService) {
		super();
		this.orderRepository = orderRepository;
		this.pieceRepository = pieceRepository;
		this.pdfService = pdfService;
		this.invoiceService = invoiceService;
	}

	@Override
	@Transactional
	public List<ServiceOrderIdAndStatusDto> allServiceOrderIdAndStatus() {
		return orderRepository.allServiceOrderIdAndStatus();
	}

	@Override
	@Transactional
	public void createServiceOrder(ServiceOrder serviceOrder) {

		double luminaireId = serviceOrder.getLuminaire().getId();
		double clientId = serviceOrder.getClient().getId();

		if (luminaireId == 0) {
			throw new LuminaireNotSelectedException("Luminaire not selected at order creation");
		}

		if (clientId == 0) {
			throw new ClientNotSelectedException("Client not selected at order creation!");
		}

		orderRepository.createServiceOrder(serviceOrder);
	}

	@Override
	@Transactional
	public Set<ServiceOrder> findAllServiceOrder() {
		return orderRepository.findAllServiceOrders();
	}

	@Override
	@Transactional
	public ServiceOrder updateServiceOrder(ServiceOrder serviceOrder, int decrement, String partNumber) {

		pieceRepository.decreasePieceCount(decrement, partNumber);
		return orderRepository.updateServiceOrder(serviceOrder);
	}

	@Override
	@Transactional
	public ServiceOrder findServiceOrderParts(Long id) {
		return orderRepository.findServiceOrderParts(id);
	}

	@Override
	@Transactional
	public int closeOrder(ServiceOrder serviceOrder) throws OrderIsClosedException {

		OrderStatus orderStatus = serviceOrder.getOrderStatus();
		Long orderId = serviceOrder.getId();

		if (orderStatus.equals(OrderStatus.CLOSE)) {
			throw new OrderIsClosedException();

		}

		pdfService.createPDFInvoice(serviceOrder);
		invoiceService.saveInvoiceToDatabase(serviceOrder);

		return orderRepository.updateOrderStatus(orderStatus.READY, orderId);
	}

	@Override
	@Transactional
	public ServiceOrder findCompleteServiceOrderById(Long id) {

		ServiceOrder serviceOrder = orderRepository.findCompleteServiceOrderById(id);

		double partsTotalPrice = this.getPartsTotalPrice(serviceOrder);
		double partsTotalPriceVAT = TwoDigitsDouble.formatPrice(partsTotalPrice * 1.19);

		double workPrice = this.getLaborTotalPrice(serviceOrder);
		double workPriceVAT = TwoDigitsDouble.formatPrice(workPrice * 1.19);

		double totalPrice = TwoDigitsDouble.formatPrice(partsTotalPrice + workPrice);
		double totalPriceVAT = TwoDigitsDouble.formatPrice(totalPrice * 1.19);

		serviceOrder.setPartsTotalPrice(partsTotalPrice);
		serviceOrder.setPartsTotalPriceVAT(partsTotalPriceVAT);

		serviceOrder.setWorkTotalPrice(workPrice);
		serviceOrder.setWorkTotalPriceVAT(workPriceVAT);

		serviceOrder.setTotalPrice(totalPrice);
		serviceOrder.setTotalPriceVAT(totalPriceVAT);

		return serviceOrder;
	}

	private double getPartsTotalPrice(ServiceOrder serviceOrder) {
		return serviceOrder.getParts().stream().mapToDouble(this::partPrice)
				.map(price -> TwoDigitsDouble.formatPrice(price)).sum();
	}

	private double partPrice(PieceOrder part) {
		return part.getCount() * part.getPrice();
	}

	private double getLaborTotalPrice(ServiceOrder serviceOrder) {

		return serviceOrder.getWorks().stream().mapToDouble(l -> l.getWorkPrice())
				.map(price -> TwoDigitsDouble.formatPrice(price)).sum();

	}

	@Override
	@Transactional
	public List<PieceOrder> getPartsFormServiceOrder(Long id) {
		// TODO Auto-generated method stub
		return orderRepository.getPartsFormServiceOrder(id);
	}

	@Override
	public List<WorkOrder> findAllWorksInOrder(Long id) {
		// TODO Auto-generated method stub

		return orderRepository.findAllWorksInOrder(id);
	}

}

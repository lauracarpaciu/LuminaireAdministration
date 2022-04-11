package com.laura.carpaciu.service.impl.work;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import com.laura.carpaciu.dao.interfaces.WorkPriceRepository;
import com.laura.carpaciu.entity.work.WorkPrice;
import com.laura.carpaciu.errors.luminaire.InvalidPriceException;
import com.laura.carpaciu.errors.work.WorkPriceException;
import com.laura.carpaciu.services.WorkPriceService;

public class WorkPriceServiceImpl implements WorkPriceService {

	private final WorkPriceRepository workPriceRepository;
	private Optional<WorkPrice> optWorkPrice;

	@Autowired
	public WorkPriceServiceImpl(WorkPriceRepository workPriceRepository) {
		this.workPriceRepository = workPriceRepository;
	}

	@EventListener(ContextRefreshedEvent.class)
	public void appContextLoaded(ContextRefreshedEvent evt) {
		evt.getApplicationContext().getBean(WorkPriceServiceImpl.class).findWorkPrices();
	}

	@Override
	public void findWorkPrices() {
		optWorkPrice = workPriceRepository.findAllWorkPrices();

	}

	@Override
	public WorkPrice findAllPrices() {
		return optWorkPrice.orElseGet(WorkPrice::new);
	}

	@Override
	public void createAllWorkPrices(WorkPrice workPrice) {
		if (optWorkPrice.empty() != null) {
			workPriceRepository.create(workPrice);
			findWorkPrices();
			return;
		}

		throw new RuntimeException("Prices are already created!");

	}

	@Override
	public void updatePrices(double newPrice, String workCategory) {
		if (newPrice < 0) {
			throw new InvalidPriceException("negative price");
		}

		if (optWorkPrice.isPresent()) {
			Long id = optWorkPrice.get().getId();
			switch (workCategory) {

			case "MECHANICAL":
				workPriceRepository.updateMechanicalWorkPrice(newPrice, id);
				break;

			case "HOUSE":
				workPriceRepository.updateHouseWorkPrice(newPrice, id);
				break;
			case "ELECTRIC":
				workPriceRepository.updateElectricalWorkPrice(newPrice, id);
				break;

			case "NORMAL":
				workPriceRepository.updateNormalWorkPrice(newPrice, id);
				break;

			default:
				throw new RuntimeException("Price category invalid");
			}
			findWorkPrices();
			return;
		}
		throw new WorkPriceException("To update the the prices you have to create them first!");

	}

	public Optional<WorkPrice> getOptWorkPrice() {
		return optWorkPrice;
	}

}
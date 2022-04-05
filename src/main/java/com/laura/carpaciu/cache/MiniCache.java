package com.laura.carpaciu.cache;

import java.util.List;
import java.util.Map;

import com.laura.carpaciu.entity.clients.Company;
import com.laura.carpaciu.entity.clients.Person;
import com.laura.carpaciu.entity.luminaire.Luminaire;
import com.laura.carpaciu.entity.luminaire.Piece;
import com.laura.carpaciu.entity.order.ServiceOrder;
import com.laura.carpaciu.entity.order.WorkOrder;
import com.laura.carpaciu.entity.work.Work;

public interface MiniCache {
	ServiceOrder loadCompleteServiceOrderById(int id);

	ServiceOrder getCompleteServiceOrder();

	void loadWorksOrder();

	List<WorkOrder> retrieveWorkFromOrder();

	void retrieveWorks(String workDescription);

	List<Work> retrieveWorks();

	Piece findPartByPartNumber(String partNumber);

	Luminaire findLuminaireBySerialNumber(String serialNumber);

	Luminaire retrieveLuminaire();

	Luminaire getEmptyLuminaire();

	Person findPersonByCnp(String cnp);

	Person loadEmptyPerson();

	Person retrievePerson();

	Company findCompanyByCui(String cui);

	Company retrieveCompany();

	Company loadEmptyCompany();

	Piece retrievePart();

	void resetCompanySearch();

	void resetPersonSearch();

	void resetCarSearch();

	Map<String, ServiceOrder> getOrder();

	Map<String, List<Work>> getWorks();

	Map<String, List<WorkOrder>> getOrderWorks();

	Map<String, Piece> getParts();

	Map<String, Luminaire> getLuminaire();

	Map<String, Person> getPerson();

	Map<String, Company> getCompany();
}
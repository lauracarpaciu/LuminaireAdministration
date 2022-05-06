package com.laura.carpaciu.invoice;

import com.laura.carpaciu.entity.order.ServiceOrder;

public interface PdfService {

	void createPDFInvoice(ServiceOrder serviceOrder);

}

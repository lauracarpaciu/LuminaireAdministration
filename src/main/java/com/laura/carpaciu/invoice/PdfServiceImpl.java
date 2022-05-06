package com.laura.carpaciu.invoice;

import com.laura.carpaciu.entity.clients.Client;
import com.laura.carpaciu.entity.clients.Company;
import com.laura.carpaciu.entity.clients.Person;
import com.laura.carpaciu.entity.order.ServiceOrder;
import com.lowagie.text.DocumentException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class PdfServiceImpl implements PdfService {

	private final TemplateEngine templateEngine;

	public PdfServiceImpl(TemplateEngine templateEngine) {
		super();
		this.templateEngine = templateEngine;
	}

	@Override
	public void createPDFInvoice(ServiceOrder serviceOrder) {

		String path = "./web/src/main/resources/invoices/invoice_" + serviceOrder.getId() + ".pdf";

		double totalPrice = serviceOrder.getTotalPrice();
		double totalPriceVAT = serviceOrder.getTotalPriceVAT();

		Context context = new Context();

		Client client = serviceOrder.getClient();

		if (client instanceof Person) {
			Person person = (Person) serviceOrder.getClient();
			context.setVariable("person", person);
		}

		if (client instanceof Company) {
			Company company = (Company) serviceOrder.getClient();
			context.setVariable("company", company);
		}

		context.setVariable("order", serviceOrder);
		context.setVariable("total", totalPrice);
		context.setVariable("totalVAT", totalPriceVAT);
		String processHTML = templateEngine.process("/invoice/invoice", context);

		try (FileOutputStream outputStream = new FileOutputStream(path)) {

			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(processHTML);
			renderer.layout();
			renderer.createPDF(outputStream, false);
			renderer.finishPDF();

		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}

	}

}

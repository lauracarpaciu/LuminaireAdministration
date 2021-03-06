package com.laura.carpaciu.service.impl.invoice;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MimeTypeUtils;

import com.laura.carpaciu.dao.interfaces.InvoiceRepository;
import com.laura.carpaciu.entity.invoice.Invoice;
import com.laura.carpaciu.entity.order.ServiceOrder;
import com.laura.carpaciu.errors.invoice.InvoiceException;
import com.laura.carpaciu.services.InvoiceService;

import lombok.AllArgsConstructor;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	private final InvoiceRepository invoiceRepository;

	@Autowired
	public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
		super();
		this.invoiceRepository = invoiceRepository;
	}

	@Override
	@Transactional
	public void saveInvoiceToDatabase(ServiceOrder serviceOrder) {

		Invoice invoice = new Invoice();

		String path = "./web/src/main/resources/invoices/invoice_" + serviceOrder.getId() + ".pdf";

		byte[] pdfToByte = this.convertPDFtoByteArray(path);

		String invoiceNumber = "invoice_" + serviceOrder.getId() + ".pdf";

		invoice.setInvoiceNumber(invoiceNumber);
		invoice.setInvoice(pdfToByte);
		invoice.setServiceOrder(serviceOrder);

		invoiceRepository.saveInvoiceToDatabase(invoice);

		this.deleteInvoiceFromApp(path);
	}

	private byte[] convertPDFtoByteArray(String path) {

		ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();

		try (java.io.InputStream inputStream = new FileInputStream(path)) {

			byte[] buffer = new byte[1024];
			int bytesRead;

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				byteOutputStream.write(buffer, 0, bytesRead);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return byteOutputStream.toByteArray();
	}

	private void deleteInvoiceFromApp(String path) {
		Path deletePath = Paths.get(path);
		try {
			Files.deleteIfExists(deletePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public void getInvoiceFromDataBase(ServiceOrder serviceOrder, HttpServletResponse response) {

		Invoice invoice = invoiceRepository.findInvoiceByServiceOrder(serviceOrder)
				.orElseThrow(() -> new InvoiceException("Invoice not found"));

		byte[] pdfBytes = invoice.getInvoice();

		response.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM.getType());
		response.setHeader("Content-Disposition", "attachment;filename=" + " invoice" + serviceOrder.getId() + ".pdf");

		try (OutputStream os = response.getOutputStream()) {

			os.write(pdfBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

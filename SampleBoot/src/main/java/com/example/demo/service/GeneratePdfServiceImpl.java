package com.example.demo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.contants.AppConstants;
import com.example.demo.exception.ErrorHandler;
import com.example.demo.model.DeptDetails;
import com.example.demo.model.FileDataTracker;
import com.example.demo.model.UserDetails;
import com.example.demo.repository.DeptDetailsDAO;
import com.example.demo.repository.FileDataTrackerDAO;
import com.example.demo.repository.UserDetailsDAO;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class GeneratePdfServiceImpl implements GeneratePdfService {

	private static final Logger logger = LoggerFactory.getLogger(GeneratePdfServiceImpl.class);

	@Autowired
	UserDetailsDAO userDetailsDAO;
	
	@Autowired
	DeptDetailsDAO deptDetailsDAO;
	
	@Autowired
	FileDataTrackerDAO fileDataTrackerDAO;

	@Override
	public JSONObject generatePdf() {
		JSONObject respJSON = new JSONObject();
		try {
			Document document = new Document(PageSize.A4);
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:/Users/Asus/Desktop/EmployeeData.pdf"));
			
//			PdfPTable tableImg = addImage();
			PdfPTable tableUserInfo = userDetails();
			PdfPTable tableDeptInfo = deptDetails();
			PdfPTable tableParaWithImage = createParaWithImage(writer);
			
			document.open();
//			document.add(tableImg);
			document.add(tableParaWithImage);
			document.add(tableUserInfo);
			document.add(tableDeptInfo);
			document.close();

			String finalFilePath 			= "C:/Users/Asus/Desktop/EmployeeData.pdf";
			File file 		= new File(finalFilePath);
	        Path path = Paths.get(finalFilePath);
			byte[] bytes = null;
			if(file.exists()) {
				bytes 	= Files.readAllBytes(path);
			}
			FileDataTracker fileDataTrackerInst = new FileDataTracker();
			fileDataTrackerInst.setFileName("EmployeeData.pdf");
			fileDataTrackerInst.setFileData(bytes);
			fileDataTrackerDAO.save(fileDataTrackerInst);
			
			respJSON.put(AppConstants.STATUS, AppConstants.SUCCESS);
			respJSON.put(AppConstants.MESSAGE, "Request Raised Successfully");
			
		} catch (Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object() {}.getClass(), e));
			respJSON.put(AppConstants.STATUS, AppConstants.FAILED);
			respJSON.put(AppConstants.MESSAGE, "Request Creation Failed");
		}
		return respJSON;
	}

	/*
	 * private void addLetterContent(Document document) throws Exception { Paragraph
	 * senderAddress = new Paragraph();
	 * 
	 * 
	 * Font font = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
	 * senderAddress.add(new Phrase("Sameer Shaikh\n", font)); senderAddress.add(new
	 * Phrase("Goregaon E\n", font)); senderAddress.add(new
	 * Phrase("Mumbai, India\n", font)); senderAddress.add(new
	 * Phrase("samshaikh@gmail.com", font)); senderAddress.add(new
	 * Phrase("Mumbai, India\n", font)); senderAddress.add(new
	 * Phrase("samshaikh@gmail.com", font)); //
	 * senderAddress.setAlignment(Element.ALIGN_LEFT); senderAddress.setFont(font);
	 * 
	 * String resourceImagePath = "C:/Users/Asus/Desktop/Image2.JPG"; Image image =
	 * Image.getInstance(resourceImagePath); image.scaleAbsolute(135f, 65f); //
	 * image.setAlignment(Image.RIGHT); senderAddress.add(image);
	 * document.add(senderAddress); }
	 */
	
	private PdfPTable createParaWithImage(PdfWriter writer) throws Exception {
	    PdfPTable table = new PdfPTable(6);
	    table.setWidthPercentage(100);
	    String resourceImagePath = "C:/Users/Asus/Desktop/Image2.JPG";
	    table.addCell(createTextCell("Sameer Shaikh \nGoregaon E \nMumbai, India \nsamshaikh@gmail.com \n \n \n"));
	    table.addCell(createImageCell(resourceImagePath));

	    return table;
	}
	
	private PdfPCell createImageCell(String path) throws Exception {
	    Image image = Image.getInstance(path);
		image.scaleAbsolute(100f, 45f);
		image.setAlignment(Image.RIGHT);
	    PdfPCell cell = new PdfPCell(image, true);
	    cell.setColspan(2);
	    cell.setBorder(Rectangle.NO_BORDER);
	    return cell;
	}
	
	private PdfPTable addImage() throws Exception {
		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100);
		PdfPCell cell = new PdfPCell(new Phrase(""));
		cell.setColspan(6);
		cell.setFixedHeight(120f);
		String resourceImagePath = "C:/Users/Asus/Desktop/Image2.JPG";
		Image image = Image.getInstance(resourceImagePath);
		image.scaleAbsolute(200f, 130f);
		image.setAlignment(Image.RIGHT);
		cell.addElement(image);
		table.addCell(cell);
		return table;
	}

	private PdfPTable userDetails() {
		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100);

		table.addCell(createBlankCell("USER INFORMATION"));
		table.addCell(createCell("USER ID"));
		table.addCell(createCell("USER NAME"));
		table.addCell(createCell("USER ROLE"));
		table.addCell(createCell("USER EMAIL"));
		table.addCell(createCell("USER DEPT"));
		table.addCell(createCell("MOBILE NO"));

		List<UserDetails> userDetailsList = userDetailsDAO.findAll();
		for (UserDetails userDetailsInst : userDetailsList) {
			table.addCell(createCell1(userDetailsInst.getEmpId().toString()));
			table.addCell(createCell1(userDetailsInst.getUserName().toString()));
			table.addCell(createCell1(userDetailsInst.getUserRole().toString()));
			table.addCell(createCell1(userDetailsInst.getUserEmail().toString()));
			table.addCell(createCell1(userDetailsInst.getDeptName().toString()));
			table.addCell(createCell1(userDetailsInst.getMobNo().toString()));
		}
		return table;
	}

	private PdfPTable deptDetails() {
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100);

		table.addCell(createBlankCell("DEPARTMENT INFORMATION"));
		table.addCell(createCell("USER ID"));
		table.addCell(createCell("DEPT ID"));
		table.addCell(createCell("DEPT NAME"));
		table.addCell(createCell("DEPT DESC"));
		table.addCell(createCell("CREATED DATE"));
		List<DeptDetails> deptDetailsList = deptDetailsDAO.findAll();
		int count = 0;
		for (DeptDetails deptDetailsInst : deptDetailsList) {
			table.addCell(createCell1(deptDetailsInst.getUserId().toString()));
			table.addCell(createCell1(deptDetailsInst.getDeptId().toString()));
			table.addCell(createCell1(deptDetailsInst.getDeptName().toString()));
			table.addCell(createCell1(deptDetailsInst.getDeptDesc().toString()));
			table.addCell(createCell1(deptDetailsInst.getCreatedDate().toString()));
			count++;
			if(count == 10) {
				break;
			}
		}
		return table;
	}

	private PdfPCell createBlankCell(String str) {
		Font font = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLDITALIC);
		PdfPCell blankCell = new PdfPCell(new Phrase(str, font));
		blankCell.setColspan(6);
		blankCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		blankCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		blankCell.setBackgroundColor(new BaseColor(151, 251, 247));
		return blankCell;
	}

	private PdfPCell createCell(String str) {
		Font font = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
		PdfPCell cell = new PdfPCell(new Phrase(str, font));
		cell.setBackgroundColor(new BaseColor(151, 251, 247));
		return cell;
	}

	private PdfPCell createCell1(String str) {
		Font font = new Font(Font.FontFamily.TIMES_ROMAN, 10);
		PdfPCell cell = new PdfPCell(new Phrase(str, font));
		return cell;
	}
	
	private PdfPCell createTextCell(String str) throws Exception {
	    PdfPCell cell = new PdfPCell();
	    cell.setColspan(4);
		Font font = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
	    Paragraph p = new Paragraph(str, font);
	    p.setAlignment(Element.ALIGN_LEFT);
	    cell.addElement(p);
	    cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
	    cell.setBorder(Rectangle.NO_BORDER);
	    return cell;
	}
	
	@Override
	public JSONObject downloadFile() {
		JSONObject respJSON = new JSONObject(); 
		try {
			FileDataTracker fileDataTrackerInst = fileDataTrackerDAO.findByFileName("EmployeeData.pdf");
			byte[] fileData = fileDataTrackerInst.getFileData();			
			respJSON.put(AppConstants.STATUS, AppConstants.SUCCESS);
			respJSON.put(AppConstants.MESSAGE, "PDF GENERATED");
			respJSON.put("fileName", "EmployeeData.pdf");
			respJSON.put("fileData", fileData);
		}
		catch (Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object() {}.getClass(), e));
			respJSON.put(AppConstants.STATUS, AppConstants.FAILED);
			respJSON.put(AppConstants.MESSAGE, "Request Creation Failed");
		}
		return respJSON;
	}
}
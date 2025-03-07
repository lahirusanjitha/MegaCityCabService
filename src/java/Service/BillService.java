/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.BillDAO;
import Model.Bill;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Lahiru Sanjitha
 */
public class BillService {
    private final BillDAO billDAO = new BillDAO();
    
     public boolean createBill(int bookingId, double totalFare, Bill bill){
        return billDAO.createBill(bookingId, totalFare, bill);
    }
    public Bill getBillByBookingId(int bookingId){
        return billDAO.getBillByBookingId(bookingId);
    }
    public double calculateTotalFare(Bill bill) {
        double fareForDistance = bill.getDistance() * bill.getRatePerKm();
        double fareForDuration = bill.getDuration() * bill.getRatePerMinute();
        double subTotal = bill.getBaseFare() + fareForDistance + fareForDuration + bill.getAdditionalCharges();
        double tax = subTotal * bill.getTaxRate() / 100;
        return subTotal + tax;
    }
    
    public String generateBillPdf(int bookingId) throws FileNotFoundException {
        Bill bill = billDAO.getBillByBookingId(bookingId);
        if (bill == null) {
            return null; 
        }

        String userHome = System.getProperty("user.home"); 
        String filePath = userHome + "/Downloads/" +"biil_booking_No " + bookingId + ".pdf"; 

        FileOutputStream fos = new FileOutputStream(filePath);

        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("Mega City Cab - Bill Receipt", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("\n"));

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);

            addTableRow(table, "Booking ID:", String.valueOf(bill.getBookingId()));
            addTableRow(table, "BillId ID:",String.valueOf(bill.getBillId()));
            addTableRow(table, "Base Fare:", String.valueOf(bill.getBaseFare()));
            addTableRow(table, "Distance:KM", String.valueOf(bill.getDistance()));
            addTableRow(table, "Rate Per:KM", String.format("%.2f", bill.getRatePerKm()));
            addTableRow(table, "Tax Rate:", String.format("%.2f", bill.getTaxRate()));
            addTableRow(table, "Total Fare:", String.format("%.2f", bill.getTotalFare()));

            document.add(table);
            document.add(new Paragraph("\nThank you for choosing Mega City Cab!", new Font(Font.FontFamily.HELVETICA, 12, Font.ITALIC)));

            document.close();
            return filePath;
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
        private void addTableRow(PdfPTable table, String label, String value) {
        PdfPCell cell1 = new PdfPCell(new Phrase(label));
        PdfPCell cell2 = new PdfPCell(new Phrase(value));
        table.addCell(cell1);
        table.addCell(cell2);
    }
    

}

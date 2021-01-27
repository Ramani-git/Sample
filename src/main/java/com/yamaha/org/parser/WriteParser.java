package com.yamaha.org.parser;


import com.yamaha.org.dao.Customer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WriteParser {

    public void writeDataIntoSheet(String outputFilePath, List<Customer> list, int index) throws Exception {
        File file = new File(outputFilePath);
        OutputStream fos = null;
        XSSFWorkbook workbook = file.exists() ? workbook = (XSSFWorkbook) WorkbookFactory.create(new FileInputStream(file)) : new XSSFWorkbook();
        try {
            Sheet sheet = workbook.createSheet("Sheet" + index);
            Row headerRow = sheet.createRow(0);
            setHeaderRow(headerRow);
            int rowIndex = 1;
            for (Customer customer : list) {
                Row row = sheet.createRow(rowIndex++);
                setRowInfo(row,customer);
            }
            fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            fos.close();
        }

    }

    private void setHeaderRow(Row headerRow){
        headerRow.createCell(0).setCellValue("Primary Mobile Number");
        headerRow.createCell(1).setCellValue("Customer Name");
        headerRow.createCell(2).setCellValue("Address");
        headerRow.createCell(3).setCellValue("Alternate Mobile Number");
    }

    private void setRowInfo(Row row,Customer customer){
        row.createCell(0).setCellValue(customer.getMobileNumber());
        row.createCell(1).setCellValue(customer.getCustomerName());
        row.createCell(2).setCellValue(customer.getAddress());
        row.createCell(3).setCellValue(customer.getAlternateMobileNum());
    }

    public List<List<Customer>>  divideLisSize(List<Customer> customers){
        List<List<Customer>> list = new ArrayList<>();
        int parttionSize = 5000;
        for(int i=0;i<customers.size();i+=parttionSize){

                List<Customer> sublist = customers.subList(i,Math.min(i+parttionSize,customers.size()));
                list.add(sublist);
        }
        return list;
    }
}

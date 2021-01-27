package com.yamaha.org.parser;

import com.yamaha.org.dao.Customer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ReadParser {

    public List<Customer> getCustomers(String FilePath) throws Exception {
        FileInputStream file = new FileInputStream(new File(FilePath));
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        List<Customer> customerList = readData(sheet);
        return customerList;
    }

    private List<Customer> readData(XSSFSheet sheet) {
        List<List<Customer>> partitionsList =new ArrayList<>();
        List<Customer> customerList = new ArrayList<>();
        Row headerRow = sheet.getRow(0);
        for (int i = 1; i < sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Customer customer = new Customer();
            for (int j = 0; j < row.getLastCellNum(); j++) {
                String headerName = headerRow.getCell(j).getStringCellValue();
                switch (headerName) {
                    case "mobile":
                        Cell cell = row.getCell(j);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String number = cell.getStringCellValue();
                        if (!number.isEmpty() || number.equalsIgnoreCase("na"))
                            customer.setMobileNumber("+91" + number);
                        break;
                    case "cname":
                        if (row.getCell(j) != null) {
                            row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                            customer.setCustomerName(row.getCell(j).getStringCellValue());
                        }
                        break;
                    case "address":
                        customer.setAddress(processAddressInfo(row.getCell(j)));
                        break;
                    case "altno":
                        if (row.getCell(j) != null) {
                            row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                            String alt = row.getCell(j).getStringCellValue();
                            if(alt != null && !alt.equalsIgnoreCase("na"))
                            customer.setAlternateMobileNum("+91"+alt);
                            else
                                customer.setAlternateMobileNum(alt);
                        }
                        break;

                }
            }
            customerList.add(customer);
           /* if(customerList.size()>=5000){
                partitionsList.add(customerList);
                customerList.clear();
            }*/
        }
        return customerList;
    }

    private String processAddressInfo(Cell cell) {
        String splitAddress = null;
        //1-17 BADINEBALUKOWTHALAM,ADONI, KURNOOL,AP, ,!!!!!
        //String address = cell.getStringCellValue();
        String address = cell.getStringCellValue();
                String[] add = null;
        if (address.contains("Add2")) {
            add = address.split("Add2");
            if (add != null && add.length > 1) {
                String[] custAddress = add[0].split("!");
                if (custAddress.length > 0)
                    splitAddress = custAddress[2];
            }
        } else {
            if (address.contains(",")) {
                add = address.split(",");
            }else{
            if (address.contains("!"))
                add = address.split("!");
            }
            if (add != null && add.length > 1) {
                splitAddress = add[1];
            } else {
                splitAddress = address;
            }

        }
        return splitAddress;
    }
}

package com.yamaha.org.service;

import com.yamaha.org.dao.Customer;
import com.yamaha.org.parser.ReadParser;
import com.yamaha.org.parser.WriteParser;

import java.util.List;

public class ParserHelper {

    public static void main(String[] args) {
        String inputFile = "D:\\Yamaha_Data\\Input\\kurnool\\Kurnool\\Kurnool 10-11L.xlsx";
        String outputFile = "D:\\Yamaha_Data\\output\\kurnool\\Kurnool\\KKurnool 10-11L.xlsx";
        ReadParser readParser = new ReadParser();
        WriteParser writeParser = new WriteParser();
        try {
            List<Customer> list = readParser.getCustomers(inputFile);
            List<List<Customer>> listOfCustomers = writeParser.divideLisSize(list);
            int index = 1;
            for(List<Customer> custList: listOfCustomers){
                writeParser.writeDataIntoSheet(outputFile,custList,index++);
                //custList.remove(custList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

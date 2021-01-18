package com.codeTest.analyze_crypto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import java.io.*;
import java.util.*;

public class CsvReader {
    private static final Logger logger = LoggerFactory.getLogger("CsvUtils.class");

    public static List<String[]> readCSV(String filePath, String[] headers){
        //Create CSVFormat
        CSVFormat format = CSVFormat.DEFAULT.withHeader(headers);
        List<String[]> resultList = new ArrayList<>();
        InputStreamReader inputStreamReader = null;
        CSVParser parser = null;

        try {
            ClassPathResource classPathResource = new ClassPathResource(filePath);
            //Get Input Stream
            InputStream fileStream = classPathResource.getInputStream();
            inputStreamReader = new InputStreamReader(fileStream,"UTF-8");

            parser = new CSVParser(inputStreamReader, format);
            int rowIndex = 0;
            for (CSVRecord record : parser) {
                if(rowIndex > 0){
                    String row[] = new String[headers.length];
                    for(int i=0; i!=headers.length; ++i)
                        row[i] = record.get(i);
                    resultList.add(row);
                }
                rowIndex++;
            }
            parser.close();
            inputStreamReader.close();
        } catch (Exception e) {
            logger.error("CsvUtils.readCSV,read csv file,message:{}", e.getMessage(), e);

        }finally {
            closeStream(inputStreamReader, parser);
        }
        return resultList;
    }

    private static void closeStream(InputStreamReader inputStreamReader, CSVParser parser) {
        if(inputStreamReader != null){
            try {
                inputStreamReader.close();
            } catch (IOException e) {
                logger.error("Cannot open the file",e.getMessage());
            }
        }
        if(parser != null){
            try {
                parser.close();
            } catch (IOException e) {
                logger.error("Cannot open the file",e.getMessage());
            }
        }
    }


}

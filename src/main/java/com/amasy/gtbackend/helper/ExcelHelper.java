package com.amasy.gtbackend.helper;

import com.amasy.gtbackend.entities.Batch;
import com.amasy.gtbackend.entities.Center;
import com.amasy.gtbackend.exceptions.ResourceNotFoundException;
import com.amasy.gtbackend.repositories.CenterRepo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    @Autowired
    private CenterRepo centerRepo;
    public static boolean checkExcelFormat(MultipartFile file){
        String contentType = file.getContentType();
        if(contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")){
            return true;
        } else {
            return false;
        }
    }
    public List<Batch> convertExcelToListOfProduct(InputStream is, Integer centerId){
        List<Batch> list = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheet("data");
            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();
            Batch batch = new Batch();

            while(iterator.hasNext()){
                Row row = iterator.next();
                if(rowNumber == 0){
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cells = row.iterator();
                int cid = 0;
                while(cells.hasNext()){
                    Cell cell = cells.next();
                    switch (cid){
                        case 0:
                            batch.setMprId((int)cell.getNumericCellValue());
                            break;
                        case 1:
                            batch.setCandidateName(cell.getStringCellValue());
                            break;
                        case 2:
                            batch.setFathersName(cell.getStringCellValue());
                            break;
                        case 3:
                            batch.setMothersName(cell.getStringCellValue());
                            break;
                        case 4:
                            batch.setDob(cell.getStringCellValue());
                            break;
                        case 5:
                            batch.setGender(cell.getStringCellValue());
                            break;
                        case 6:
                            batch.setReligion(cell.getStringCellValue());
                            break;
                        case 7:
                            batch.setCategory(cell.getStringCellValue());
                            break;
                        case 8:
                            batch.setNationality(cell.getStringCellValue());
                            break;
                        case 9:
                            batch.setGeneralQualification(cell.getStringCellValue());
                            break;
                        case 10:
                            batch.setAddress(cell.getStringCellValue());
                            break;
                        case 11:
                            batch.setState(cell.getStringCellValue());
                            break;
                        case 12:
                            batch.setDist(cell.getStringCellValue());
                            break;
                        case 13:
                            batch.setCity(cell.getStringCellValue());
                            break;
                        case 14:
                            batch.setPincode(cell.getStringCellValue());
                            break;
                        case 15:
                            batch.setMobile(cell.getStringCellValue());
                            break;
                        case 16:
                            batch.setEmail(cell.getStringCellValue());
                            break;
                        case 17:
                            batch.setSectorName(cell.getStringCellValue());
                            break;
                        case 18:
                            batch.setSectorCode(cell.getStringCellValue());
                            break;
                        case 19:
                            batch.setCourse(cell.getStringCellValue());
                            break;
                        case 20:
                            batch.setModule(cell.getStringCellValue());
                            break;
                        case 21:
                            batch.setUid(cell.getStringCellValue());
                            break;
                        case 22:
                            batch.setTrainingStartDate(cell.getStringCellValue());
                            break;
                        case 23:
                            batch.setTrainingEndDate(cell.getStringCellValue());
                            break;
                        case 24:
                            batch.setTrainingHours((int)cell.getNumericCellValue());
                            break;
                        case 25:
                            batch.setOjtHours((int)cell.getNumericCellValue());
                            break;
                        case 26:
                            batch.setTotalHours((int)cell.getNumericCellValue());
                            break;
                        case 27:
                            batch.setTotalDays((int)cell.getNumericCellValue());
                            break;
                        case 28:
                            batch.setPrAbnNo(cell.getStringCellValue());
                            break;
                        case 29:
                            Center center = this.centerRepo.findById(centerId).orElseThrow(() -> new ResourceNotFoundException("Center", "Id", centerId));
                            batch.setCenter(center);
                            break;
                        case 30:
                            batch.setStatus("Pending");
                            break;
                        case 31:
                            batch.setAddedDate(new Date());
                            break;
                        default:
                            break;
                    }
                    cid++;
                }
                list.add(batch);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
}

package com.spring.main.exceliservice;


import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.spring.main.entity.com.spring.main.entity.Person;
import com.spring.main.repo.PersonRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Transactional
public class ExcelUploadService {

    private final PersonRepository repository;

    private static final int DEFAULT_YEAR = LocalDate.now().getYear();

    private static final DateTimeFormatter[] FORMATTERS = {
            DateTimeFormatter.ofPattern("d-MMM", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("dd-MMM", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("d-MMMM", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("dd-MMMM", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("d-M-yyyy"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("d/MM/yyyy"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy")
    };

    public void uploadExcel(MultipartFile file) throws IOException {

        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {

            Sheet sheet = workbook.getSheetAt(0);

            // Skip Header Row
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);

                if (row == null) {
                    continue;
                }

                Person person = new Person();

                person.setName(getCellValue(row.getCell(0)));
                person.setDateOfBirth(parseDate(getCellValue(row.getCell(1))));
                person.setAniversaryDate(parseDate(getCellValue(row.getCell(2))));
                person.setRelation(getCellValue(row.getCell(3)));
                person.setLocation(getCellValue(row.getCell(4)));
                person.setCity(getCellValue(row.getCell(5)));
                person.setSubRelation(getCellValue(row.getCell(6)));
                repository.save(person); 
            } 
        }
    }
    /**
     * Check whether the entire row is empty.
     */
    private boolean isRowEmpty(Row row) {

        if (row == null) {
            return true;
        }

        for (int i = 0; i <= 6; i++) {

            Cell cell = row.getCell(i);
 
            if (cell != null) {

                String value = getCellValue(cell);

                if (!value.trim().isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }


    private String getCellValue(Cell cell) {

        if (cell == null) {
            return "";
        }
 
        switch (cell.getCellType()) {

            case STRING:
                return cell.getStringCellValue().trim();

            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getLocalDateTimeCellValue()
                            .toLocalDate()
                            .toString();
                }
                return String.valueOf((long) cell.getNumericCellValue());

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case FORMULA:
                return cell.getCellFormula();

            default:
                return "";
        }
    }

    private LocalDate parseDate(String value) {

        if (value == null || value.isBlank() || value.equalsIgnoreCase("NA")) {
            return null;
        }

        value = value.trim();

        // yyyy-MM-dd
        try {
            return LocalDate.parse(value);
        } catch (Exception ignored) {
        }

        // Other supported formats
        for (DateTimeFormatter formatter : FORMATTERS) {
            try {

                TemporalAccessor accessor = formatter.parse(value);

                if (accessor.isSupported(ChronoField.YEAR)) {
                    return LocalDate.from(accessor);
                }

                MonthDay monthDay = MonthDay.from(accessor);

                return monthDay.atYear(DEFAULT_YEAR);

            } catch (Exception ignored) {
            }
        }

        throw new IllegalArgumentException("Invalid date format: " + value);
    }
}
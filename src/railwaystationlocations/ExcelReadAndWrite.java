package railwaystationlocations;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelReadAndWrite {
	static Workbook wb = new HSSFWorkbook();
	static String filename = System.getProperty("user.home") + "/Desktop/" +"stations.xls";
	static Sheet sheet = wb.createSheet("second Sheet");

	public static void writtingToExcel(String stationName, String stationCode,String lat,
			String lon) {

		CreationHelper createHelper = wb.getCreationHelper();

		Row row = sheet.createRow(wb.getSheetAt(0).getLastRowNum() + 1);

		Cell c = null;

		c = row.createCell(0);
		c.setCellValue(createHelper.createRichTextString(stationName));
		
		c = row.createCell(1);
		c.setCellValue(createHelper.createRichTextString(stationCode));
		
		c = row.createCell(2);
		c.setCellValue(createHelper.createRichTextString(lat));

		c = row.createCell(3);
		c.setCellValue(createHelper.createRichTextString(lon));
		
		

		// Write the output to a file
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(filename);
			wb.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(stationName + " Added!");

	}
}

package es.consumo.gescom.modules.excel;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.consumo.gescom.modules.protocol.model.dto.ProtocolDTO;
import es.consumo.gescom.modules.questions.model.dto.QuestionsDTO;

public class ExcelUtils {

    private static final ExcelUtils instance = new ExcelUtils();
    private static final Logger log = LoggerFactory.getLogger(ExcelUtils.class);

    private ExcelUtils() {}

    public static ExcelUtils getInstance() {
        return instance;
    }

    public <E> byte[] createExportExcelTablas(ProtocolDTO protocolo) {
        log.debug("ExcelUtils.createExportExcelTablas.init()-----");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        // Creamos el libro de trabajo de Excel formato OOXML
        XSSFWorkbook workbook = new XSSFWorkbook();
        
        // XSSFSheet sheetPortada = workbook.createSheet("PORTADA");
        
        // La hoja donde pondremos los datos
        XSSFSheet sheet = workbook.createSheet("DATOS");
        
        // Ajustar el ancho de la columna A, B, C y D
        sheet.setColumnWidth(0, 25 * 1200); // Ancho en píxeles
        sheet.setColumnWidth(1, 25 * 120);
        sheet.setColumnWidth(2, 25 * 120);
        sheet.setColumnWidth(3, 25 * 120);

        // Creamos el estilo paga las celdas del encabezado - Titulo campaña
        XSSFCellStyle style = workbook.createCellStyle();

        // Establecer la fuente Arial 10 en negrita
        XSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeight(10);
        font.setBold(true);
        style.setFont(font);
        style.setWrapText(true);

        // Establecer el color de fondo
        style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
        // Alinear el texto al centro
        style.setAlignment(HorizontalAlignment.CENTER);

        // Centrar el contenido
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        // Estilo titulo protocolo
        XSSFCellStyle styleProtocolo = workbook.createCellStyle();
        styleProtocolo.cloneStyleFrom(style);
        styleProtocolo.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
        
        // Establecer el borde
        styleProtocolo.setBorderBottom(BorderStyle.THIN);
        styleProtocolo.setBorderLeft(BorderStyle.THIN);
        styleProtocolo.setBorderRight(BorderStyle.THIN);
        styleProtocolo.setBorderTop(BorderStyle.THIN);
        
        // Estilo titulo preguntas
        XSSFCellStyle styleTituloPreguntas = workbook.createCellStyle();
        styleTituloPreguntas.cloneStyleFrom(styleProtocolo);
        
        XSSFCellStyle stylePreguntas = workbook.createCellStyle();
        stylePreguntas.cloneStyleFrom(styleTituloPreguntas);
        
        // Establecer la fuente Arial 10 sin negrita
        XSSFFont fontPreguntas = workbook.createFont();
        fontPreguntas.setFontName("Arial");
        fontPreguntas.setFontHeight(10);
        fontPreguntas.setBold(false);
        stylePreguntas.setFont(fontPreguntas);;
        
        XSSFCellStyle styleRespuestas = workbook.createCellStyle();
        styleRespuestas.cloneStyleFrom(styleTituloPreguntas);
        styleRespuestas.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        
        log.debug("Creating excel");

        int rowNum = 0;
        int colHeader = 0;

        // Primera fila
        XSSFRow row = sheet.createRow(rowNum++);
        row.setHeightInPoints(50);
        XSSFCell cell = row.createCell(colHeader);
        cell.setCellValue(protocolo.getNameCampaign());
        cell.setCellStyle(style);
        sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowNum - 1, colHeader, colHeader + 3));

        // Segunda fila
        row = sheet.createRow(rowNum++);
        row.setHeightInPoints(25);
        cell = row.createCell(colHeader);
        cell.setCellValue(protocolo.getName());       
        cell.setCellStyle(styleProtocolo);
        sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowNum - 1, colHeader, colHeader + 3));
        
	        // Celda B
	        cell = row.createCell(colHeader + 1);
	        cell.setCellStyle(styleProtocolo);
	
	        // Celda C
	        cell = row.createCell(colHeader + 2);
	        cell.setCellStyle(styleProtocolo);
	        
	        // Celda D
	        cell = row.createCell(colHeader + 3);
	        cell.setCellStyle(styleProtocolo);
        
        // Tercera fila
        row = sheet.createRow(rowNum++);
        row.setHeightInPoints(25);
        cell = row.createCell(colHeader);
        cell.setCellStyle(styleProtocolo);
        sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowNum - 1, colHeader, colHeader + 3));
        
	        // Celda B
	        cell = row.createCell(colHeader + 1);
	        cell.setCellStyle(styleProtocolo);
	
	        // Celda C
	        cell = row.createCell(colHeader + 2);
	        cell.setCellStyle(styleProtocolo);
	        
	        // Celda D
	        cell = row.createCell(colHeader + 3);
	        cell.setCellStyle(styleProtocolo);
        
        // Cuarta fila
        row = sheet.createRow(rowNum++);
        row.setHeightInPoints(25);
        cell = row.createCell(colHeader);
        cell.setCellValue((String) "PREGUNTAS PROTOCOLO");
        cell.setCellStyle(styleProtocolo);
        sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowNum - 1, colHeader, colHeader + 3));
        
	        // Celda B
	        cell = row.createCell(colHeader + 1);
	        cell.setCellStyle(styleProtocolo);
	
	        // Celda C
	        cell = row.createCell(colHeader + 2);
	        cell.setCellStyle(styleProtocolo);
	        
	        // Celda D
	        cell = row.createCell(colHeader + 3);
	        cell.setCellStyle(styleProtocolo);
        
        // Quinta fila
        row = sheet.createRow(rowNum++);
        row.setHeightInPoints(25);
        cell = row.createCell(colHeader);
        cell.setCellStyle(styleProtocolo);
        sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowNum - 1, colHeader, colHeader + 3));
        
	        // Celda B
	        cell = row.createCell(colHeader + 1);
	        cell.setCellStyle(styleProtocolo);
	
	        // Celda C
	        cell = row.createCell(colHeader + 2);
	        cell.setCellStyle(styleProtocolo);
	        
	        // Celda D
	        cell = row.createCell(colHeader + 3);
	        cell.setCellStyle(styleProtocolo);
        
        // Sexta fila
        row = sheet.createRow(rowNum++);
        row.setHeightInPoints(5);
        cell = row.createCell(colHeader);
        sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowNum - 1, colHeader, colHeader + 3));

        // Septima fila
        row = sheet.createRow(rowNum++);
        cell = row.createCell(colHeader++);
        cell.setCellValue((String) "Pregunta");
        cell.setCellStyle(styleTituloPreguntas);
        
        cell = row.createCell(colHeader++);
        cell.setCellValue((String) "Si");
        cell.setCellStyle(styleTituloPreguntas);
        
        cell = row.createCell(colHeader++);
        cell.setCellValue((String) "No");
        cell.setCellStyle(styleTituloPreguntas);
        
        cell = row.createCell(colHeader++);
        cell.setCellValue((String) "No procede");
        cell.setCellStyle(styleTituloPreguntas);
        
        // Preguntas
        if (null != protocolo.getQuestion()) {
        	int numPregunta = 0;
        	
        	for (QuestionsDTO pregunta : protocolo.getQuestion()) {
        		colHeader = 0;
        		numPregunta++;
        		row = sheet.createRow(rowNum++);
        		cell = row.createCell(colHeader++);
        		cell.setCellValue(numPregunta + " - " + pregunta.getQuestion());
                cell.setCellStyle(stylePreguntas);
                
                cell = row.createCell(colHeader++);
                cell.setCellStyle(styleRespuestas);
                
                cell = row.createCell(colHeader++);
                cell.setCellStyle(styleRespuestas);
                
                cell = row.createCell(colHeader++);
                cell.setCellStyle(styleRespuestas);
        	}
        	
        }

        try {
            workbook.write(bos);
            workbook.close();
        } catch (FileNotFoundException e) {
            log.debug("Error_FileNotFoundException" + e.toString());
            return null;
        } catch (IOException e) {
            log.debug("IOException" + e.toString());
            return null;
        }

        log.debug("Excel Done!!");
        log.debug("ExcelUtils.createExportExcelTablas.end()-----");
        return bos.toByteArray();
    }

}

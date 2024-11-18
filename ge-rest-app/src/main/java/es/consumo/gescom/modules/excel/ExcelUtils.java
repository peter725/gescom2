package es.consumo.gescom.modules.excel;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import es.consumo.gescom.modules.campaign.model.dto.QuestionsResponseDTO;
import es.consumo.gescom.modules.ipr.model.dto.IprDTO;
import lombok.Getter;
import org.apache.poi.ss.usermodel.*;
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

    @Getter
    private static final ExcelUtils instance = new ExcelUtils();
    private static final Logger log = LoggerFactory.getLogger(ExcelUtils.class);

    private ExcelUtils() {}

    public <E> byte[] createExportExcelTablas(ProtocolDTO protocolo, boolean result) {
        log.debug("ExcelUtils.createExportExcelTablas.init()-----");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        // Creamos el libro de trabajo de Excel formato OOXML
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheetPortada = workbook.createSheet("PORTADA");
        int rowNumP = 0;
        int colHeaderP = 0;
        XSSFCellStyle styleP = workbook.createCellStyle();

        XSSFRow rowP = sheetPortada.createRow(rowNumP++);
        rowP.setHeightInPoints(50);
        XSSFCell cellP = rowP.createCell(colHeaderP);
        cellP.setCellValue(protocolo.getNameCampaign());
        cellP.setCellStyle(styleP);
        sheetPortada.addMergedRegion(new CellRangeAddress(rowNumP - 1, rowNumP - 1, colHeaderP, colHeaderP + 3));

        // La hoja donde pondremos los datos
        XSSFSheet sheet = workbook.createSheet("DATOS");

        // Ajustar el ancho de la columna A, B, C y D
        sheet.setColumnWidth(0, 25 * 1200); // Ancho en píxeles
        sheet.setColumnWidth(1, 25 * 120);
        sheet.setColumnWidth(2, 25 * 120);
        sheet.setColumnWidth(3, 25 * 120);

        // Creamos el estilo para las celdas del encabezado - Titulo campaña
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
        styleProtocolo.setAlignment(HorizontalAlignment.CENTER);
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
        stylePreguntas.setIndention((short) 5);
        stylePreguntas.setAlignment(HorizontalAlignment.LEFT);

        // Establecer la fuente Arial 10 sin negrita
        XSSFFont fontPreguntas = workbook.createFont();
        fontPreguntas.setFontName("Arial");
        fontPreguntas.setFontHeight(10);
        fontPreguntas.setBold(false);
        stylePreguntas.setFont(fontPreguntas);;

        XSSFCellStyle styleRespuestas = workbook.createCellStyle();
        styleRespuestas.cloneStyleFrom(styleTituloPreguntas);
        styleRespuestas.setAlignment(HorizontalAlignment.RIGHT);
        styleRespuestas.setFillForegroundColor(IndexedColors.YELLOW.getIndex());

        // Establecer el color de fondo Interlineado
        XSSFCellStyle styleInterlineado = workbook.createCellStyle();
        styleInterlineado.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        styleInterlineado.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
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
        if ( result ) {
            cell.setCellValue((String) "RESULTADOS A NIVEL ESTATAL");
        } else {
            cell.setCellValue((String) "PREGUNTAS PROTOCOLO");
        }
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

        if ( result ) {
            cell.setCellValue("PRODUCTO: " + protocolo.getResultsResponseDTO().getProductName());
            cell.setCellStyle(styleProtocolo);
        } else {
            cell.setCellStyle(styleProtocolo);
        }
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
        if (result){
            List<QuestionsResponseDTO> preguntasOrder = protocolo.getResultsResponseDTO().getQuestionsResponseDTOS().stream().sorted(Comparator.comparingInt(QuestionsResponseDTO::getOrderQuestion)).collect(Collectors.toList());
            if (null != protocolo.getResultsResponseDTO().getQuestionsResponseDTOS()) {
                Boolean question = true;
                for (QuestionsResponseDTO pregunta : preguntasOrder) {
                    colHeader = 0;

                    if (pregunta.getNumResponseSi() == 0 && pregunta.getNumResponseNo() == 0 && pregunta.getNumResponseNoProcede() == 0){
                        if (!question) {
                            row = sheet.createRow(rowNum++);
                        }


                        row = sheet.createRow(rowNum++);
                        cell = row.createCell(colHeader++);
                        cell.setCellValue(pregunta.getQuestion());
                        cell.setCellStyle(styleTituloPreguntas);

                        cell = row.createCell(colHeader++);
                        cell.setCellStyle(stylePreguntas);

                        cell = row.createCell(colHeader++);
                        cell.setCellStyle(stylePreguntas);

                        cell = row.createCell(colHeader++);
                        cell.setCellStyle(stylePreguntas);

                        question = false;
                    } else {
                        if (pregunta.getCodeQuestion() != null){
                            if (pregunta.getCodeQuestion().equals("DC1")){
                                row = sheet.createRow(rowNum++);

                                row = sheet.createRow(rowNum++);
                                cell = row.createCell(colHeader++);
                                cell.setCellStyle(styleTituloPreguntas);

                                cell = row.createCell(colHeader++);
                                cell.setCellStyle(stylePreguntas);

                                cell = row.createCell(colHeader++);
                                cell.setCellStyle(stylePreguntas);

                                cell = row.createCell(colHeader++);
                                cell.setCellStyle(stylePreguntas);
                                colHeader = 0;
                            }
                        }

                        row = sheet.createRow(rowNum++);
                        cell = row.createCell(colHeader++);
                        cell.setCellValue(pregunta.getCodeQuestion() + " - " + pregunta.getQuestion());
                        cell.setCellStyle(stylePreguntas);

                        cell = row.createCell(colHeader++);
                        cell.setCellStyle(styleRespuestas);
                        cell.setCellValue(pregunta.getNumResponseSi());

                        if (!pregunta.getCodeQuestion().startsWith("DC")) {
                            cell = row.createCell(colHeader++);
                            cell.setCellStyle(styleRespuestas);
                            cell.setCellValue(pregunta.getNumResponseNo());

                            cell = row.createCell(colHeader++);
                            cell.setCellStyle(styleRespuestas);
                            cell.setCellValue(pregunta.getNumResponseNoProcede());
                        }
                    }
                }
            }
        }else {
            if (null != protocolo.getQuestion()) {
                int numPregunta = 0;
                List<QuestionsDTO> preguntasOrder = protocolo.getQuestion().stream().sorted(Comparator.comparingInt(QuestionsDTO::getOrderQuestion)).toList();
                for (QuestionsDTO pregunta : preguntasOrder) {
                    colHeader = 0;
                    numPregunta++;
                    if (pregunta.getResponse().equals("N")) {
                        if (numPregunta > 1){
                            row = sheet.createRow(rowNum++);
                            row.setHeightInPoints(5);
                            sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowNum - 1, colHeader, colHeader + 3));
                        }
                        row = sheet.createRow(rowNum++);
                        cell = row.createCell(colHeader++);
                        cell.setCellValue(numPregunta + " - " + pregunta.getQuestion());
                        cell.setCellStyle(styleTituloPreguntas);

                        cell = row.createCell(colHeader++);
                        cell.setCellStyle(stylePreguntas);

                        cell = row.createCell(colHeader++);
                        cell.setCellStyle(stylePreguntas);

                        cell = row.createCell(colHeader++);
                        cell.setCellStyle(stylePreguntas);

                    } else {
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

    //excel de resultados finales por ipr
    public <E> byte[] createExportExcelResults(IprDTO ipr) {
        log.debug("ExcelUtils.createExportExcelResults.init()-----");
        ByteArrayOutputStream bosResults = new ByteArrayOutputStream();

        // Creamos el libro de trabajo de Excel formato OOXML
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheetPortada = workbook.createSheet("PORTADA");
        int rowNumP = 0;
        int colHeaderP = 0;
        XSSFCellStyle styleP = workbook.createCellStyle();

        XSSFRow rowP = sheetPortada.createRow(rowNumP++);
        rowP.setHeightInPoints(50);
        XSSFCell cellP = rowP.createCell(colHeaderP);
        cellP.setCellValue(ipr.getResultsResponseDTO().getCampaignName());
        cellP.setCellStyle(styleP);
        sheetPortada.addMergedRegion(new CellRangeAddress(rowNumP - 1, rowNumP - 1, colHeaderP, colHeaderP + 3));

        // La hoja donde pondremos los datos
        XSSFSheet sheet = workbook.createSheet("DATOS");

        // Ajustar el ancho de la columna A, B, C y D
        sheet.setColumnWidth(0, 25 * 1200); // Ancho en píxeles
        sheet.setColumnWidth(1, 25 * 120);
        sheet.setColumnWidth(2, 25 * 120);
        sheet.setColumnWidth(3, 25 * 120);

        // Creamos el estilo para las celdas del encabezado - Titulo campaña
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

        // Alinear el texto a la izquierda
        CellStyle styleLeft = workbook.createCellStyle();
        styleLeft.setAlignment(HorizontalAlignment.LEFT);
        styleLeft.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styleLeft.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
        styleLeft.setIndention((short)5); // Añade una sangría de 1 espacio

        // bordes
        styleLeft.setBorderBottom(BorderStyle.THIN);
        styleLeft.setBorderTop(BorderStyle.THIN);
        styleLeft.setBorderRight(BorderStyle.THIN);
        styleLeft.setBorderLeft(BorderStyle.THIN);

        styleLeft.setWrapText(true);

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
        styleRespuestas.setAlignment(HorizontalAlignment.RIGHT);
        styleRespuestas.setFillForegroundColor(IndexedColors.YELLOW.getIndex());

        // Establecer el color de fondo Interlineado
        XSSFCellStyle styleInterlineado = workbook.createCellStyle();
        styleInterlineado.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        styleInterlineado.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        log.debug("Creating excel");
        int rowNum = 0;
        int colHeader = 0;

        //celda de porcentaje respect to
        CellStyle stylePorcentajeRespectTo = workbook.createCellStyle();
        stylePorcentajeRespectTo.setBorderBottom(BorderStyle.THIN);
        stylePorcentajeRespectTo.setBorderTop(BorderStyle.THIN);
        stylePorcentajeRespectTo.setBorderRight(BorderStyle.THIN);
        stylePorcentajeRespectTo.setBorderLeft(BorderStyle.THIN);

        // Celda de Porcentaje
        CellStyle percentageStyle = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();

        // Establece el formato de porcentaje con dos decimales
        percentageStyle.setDataFormat(format.getFormat("0.00%"));
        percentageStyle.setBorderBottom(BorderStyle.THIN);
        percentageStyle.setBorderTop(BorderStyle.THIN);
        percentageStyle.setBorderRight(BorderStyle.THIN);
        percentageStyle.setBorderLeft(BorderStyle.THIN);

        // Primera fila
        XSSFRow row = sheet.createRow(rowNum++);
        row.setHeightInPoints(30);
        XSSFCell cell = row.createCell(colHeader);
        cell.setCellValue(ipr.getResultsResponseDTO().getCampaignName());
        cell.setCellStyle(style);
        sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowNum - 1, colHeader, colHeader + 3));

        // Segunda fila
        row = sheet.createRow(rowNum++);
        row.setHeightInPoints(21);
        cell = row.createCell(colHeader);
        cell.setCellValue(ipr.getResultsResponseDTO().getProtocolName());
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
        row.setHeightInPoints(21);
        cell = row.createCell(colHeader);
        cell.setCellValue("IPR: "+ipr.getName());
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
        row.setHeightInPoints(21);
        cell = row.createCell(colHeader);
        cell.setCellValue((String) "RESULTADOS A NIVEL ESTATAL");
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
        row.setHeightInPoints(21);
        cell = row.createCell(colHeader);
        cell.setCellValue("PRODUCTO: "+ipr.getResultsResponseDTO().getProductName());
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
        cell.setCellValue((String) "Total");
        cell.setCellStyle(styleTituloPreguntas);

        cell = row.createCell(colHeader++);
        cell.setCellValue((String) "Porcentaje (%)");
        cell.setCellStyle(styleTituloPreguntas);

        cell = row.createCell(colHeader++);
        cell.setCellValue((String) "% Respecto a");
        cell.setCellStyle(styleTituloPreguntas);

        // Preguntas
        if (null != ipr.getResultsResponseDTO().getQuestionsResponseDTOS()) {
            int numPregunta = 0;

            for (QuestionsResponseDTO pregunta : ipr.getResultsResponseDTO().getQuestionsResponseDTOS()) {

                // Verificar si la pregunta actual es "DC1. Nº de establecimientos existentes"
                if ("DC1.Nº de establecimientos existentes".equals(pregunta.getQuestion())) {
                    // Añadir una fila vacía antes de comenzar el nuevo bloque
                    row = sheet.createRow(rowNum++);
                    row.createCell(0); // Crear una celda vacía

                    row = sheet.createRow(rowNum++);
                    cell = row.createCell(0);
                    cell.setCellStyle(styleTituloPreguntas);

                    cell = row.createCell(1);
                    cell.setCellStyle(styleTituloPreguntas);

                    cell = row.createCell(2);
                    cell.setCellStyle(styleTituloPreguntas);

                    cell = row.createCell(3);
                    cell.setCellStyle(styleTituloPreguntas);
                }
                colHeader = 0;
                numPregunta++;
                row = sheet.createRow(rowNum++);
                cell = row.createCell(colHeader++);
                if (!pregunta.getQuestion().startsWith("DC")) {
                    cell.setCellValue(numPregunta + ". " + pregunta.getQuestion());
                    cell.setCellStyle(styleLeft);
                }else{
                    cell.setCellValue(pregunta.getQuestion());
                    cell.setCellStyle(styleLeft);
                }

                cell = row.createCell(colHeader++);
                cell.setCellStyle(styleRespuestas);
                cell.setCellValue(pregunta.getTotal());

                cell = row.createCell(colHeader++);
                if (!pregunta.getQuestion().startsWith("DC")){
                    if (pregunta.getPercentage() != null) {
                        cell.setCellValue(pregunta.getPercentage()  / 100);
                        cell.setCellStyle(percentageStyle); // Aplica el estilo de porcentaje
                    } else {
                        cell.setCellValue("");
                        cell.setCellStyle(percentageStyle);
                    }
                }else{
                    cell.setCellValue("");
                    cell.setCellStyle(percentageStyle);
                }

                cell = row.createCell(colHeader++);
                if (pregunta.getPercentageRespectTo() != null) {
                    cell.setCellValue(pregunta.getPercentageRespectTo());
                    cell.setCellStyle(stylePorcentajeRespectTo);

                } else {
                    cell.setCellValue("");
                    cell.setCellStyle(stylePorcentajeRespectTo);
                }
            }
        }

        try {
            workbook.write(bosResults);
            workbook.close();
        } catch (FileNotFoundException e) {
            log.debug("Error_FileNotFoundException" + e);
            return null;
        } catch (IOException e) {
            log.debug("IOException" + e);
            return null;
        }
        log.debug("Excel Done!!");
        log.debug("ExcelUtils.createExportExcelTablas.end()-----");
        return bosResults.toByteArray();
    }
}

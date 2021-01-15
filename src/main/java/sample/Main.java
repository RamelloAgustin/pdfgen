package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Cell;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.BasicConfigurator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Main /*extends Application */{
    /*
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
*/

    public static void main(String[] args) {
        //launch(args);

        try {

            String data;
            InputStream is = new FileInputStream("/home/agustin/Escritorio/results-survey714643 completas.xls");
            HSSFWorkbook wb = (HSSFWorkbook) WorkbookFactory.create(is);
            Sheet sheet = wb.getSheetAt(0);
            Iterator rowIter = sheet.rowIterator();
            Row r = (Row)rowIter.next();

            rowIter = sheet.rowIterator();

            ArrayList<String> headerArray = new ArrayList<>();
            int headerSize=0;
            int actualCellCol=0;
            int contador=0;
            while(rowIter.hasNext()) {
                Iterator cellIter = ((Row)rowIter.next()).cellIterator();

                //primera fila sacar tamaño header
                if (headerSize ==0) {
                    while (cellIter.hasNext()) {
                        org.apache.poi.ss.usermodel.Cell cell = (org.apache.poi.ss.usermodel.Cell) cellIter.next();
                        headerSize++;
                        DataFormatter df = new DataFormatter();
                        data = df.formatCellValue(cell);
                        headerArray.add(data);
                    }
                    System.out.println("Cantidad encuestas: "+headerSize);
                    System.out.println(headerArray);
                }



                ArrayList<String> rowData=new ArrayList<>();
                for (int i=0;i<headerSize;i++){
                    if(cellIter.hasNext()){
                        org.apache.poi.ss.usermodel.Cell cell = (org.apache.poi.ss.usermodel.Cell) cellIter.next();

                        actualCellCol= cell.getColumnIndex();

                        DataFormatter df = new DataFormatter();
                        data = df.formatCellValue(cell);

                        if(actualCellCol==i) rowData.add(data);
                        if(actualCellCol!=i){
                            int aux=actualCellCol-i;
                            for (int j=0;j<aux;j++){
                                rowData.add("--");
                            }
                            rowData.add(data);
                            i+=aux;
                        }
                    }else rowData.add("--");
                }
                //call generar
                if (contador!=0){
                    Genera generador=new Genera(headerArray,rowData);
                    generador.generar();
                }
                contador++;
                //System.out.println(rowData);
            }
            is.close();

        }
        catch(Exception e) {
            e.printStackTrace();
            return;
        }



    }
}

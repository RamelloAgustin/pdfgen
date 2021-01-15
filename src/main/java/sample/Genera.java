package sample;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import com.itextpdf.kernel.pdf.PdfDocument;

import java.io.FileOutputStream;
import java.util.Date;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Genera {
    ArrayList<String> colHeader;
    ArrayList<String> rowContent;

    public ArrayList<String> getcolHeader() {
        return colHeader;
    }

    public void setHeader(ArrayList<String> colHeader) {
        this.colHeader = colHeader;
    }

    public Genera(ArrayList<String> colHeader, ArrayList<String> rowContent) {
        this.colHeader = colHeader;
        this.rowContent = rowContent;
    }
    public boolean generar() throws IOException {
        int headerLenght=colHeader.size();
        String actualCol;
        String actualCell;
        String archivo=new String();
        archivo=archivo+"/home/agustin/testspdf/"+rowContent.get(0)+".pdf";
        File file=new File(archivo);
        file.getParentFile().mkdirs();
        try{
            PdfWriter writer =new PdfWriter(archivo);
            PdfDocument pdf = new PdfDocument(writer);
            Document document =new Document(pdf);

            for (int i=0;i<headerLenght;i++){
                actualCol=this.colHeader.get(i);
                actualCell=this.rowContent.get(i);

                if(actualCell!="--"){

                    document.add(new Paragraph(actualCol));

                    document.add(new Paragraph(actualCell));

                }
            }
            document.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        try (PDDocument doc = new PDDocument()) {

            PDPage myPage = new PDPage();
            doc.addPage(myPage);
            String id=colHeader.get(0);

            try (PDPageContentStream cont = new PDPageContentStream(doc, myPage)) {
                cont.beginText();
                cont.setLeading(13f);
                cont.newLineAtOffset(25,700);
                PDType0Font fontBold = PDType0Font.load(doc,new File("/usr/share/fonts/truetype/roboto/unhinted/RobotoTTF/Roboto-Black.ttf"));
                PDType0Font fontNormal = PDType0Font.load(doc,new File("/usr/share/fonts/truetype/roboto/unhinted/RobotoTTF/Roboto-Regular.ttf"));
                for (int i=0;i<headerLenght;i++){
                    actualCol=this.colHeader.get(i);
                    actualCell=this.rowContent.get(i);

                    if(actualCell!="--"){
                        cont.setFont(fontBold,16);
                        actualCol=actualCol.replace("\n","<br>").replace("\r"," ");
                        cont.showText(actualCol);
                        cont.newLine();

                        cont.setFont(fontNormal,14);
                        actualCell=actualCell.replace("\n","<br>").replace("\r"," ");
                        cont.showText(actualCell);
                        cont.newLine();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            doc.save("/home/agustin/testspdf/"+id+".pdf");
        }
        */

        return true;
    }

}

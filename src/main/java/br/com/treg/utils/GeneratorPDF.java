/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Gustavo
 */
public class GeneratorPDF {

    public GeneratorPDF(String msg) {
        //criação do documento
        Document document = new Document();

        try{
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Recibo.pdf"));
            document.open();
            
            document.add(new Paragraph(msg));
        }catch(DocumentException | IOException de){
            System.err.println(de.getMessage());
        }
        document.close();
        
    }
    
    
}

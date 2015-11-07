/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.utils;

import br.com.treg.business.model.ObraFuncionario;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Gustavo
 */
public class GeneratorPDF {
    
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
      Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
        Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
        Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
        Font.BOLD);


    public GeneratorPDF(String valorExtenso, ObraFuncionario of, double valor) {
        //criação do documento
        Document document = new Document();

        try{
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Gustavo\\Desktop\\Recibo.pdf"));
            document.open();
            
            Paragraph preface = new Paragraph();
            
            addEmptyLine(preface, 1);
            
            preface.add(new Paragraph("RECIBO", catFont));
            addEmptyLine(preface, 1);
            
            preface.add(new Paragraph("Valor: R$ "+valor));
            addEmptyLine(preface, 3);
            
            preface.add(new Paragraph("Recebi(emos) de Thierys Pereira Maia "));
            addEmptyLine(preface, 1);
            preface.add(new Paragraph("A importância de "+valorExtenso+"."));
            addEmptyLine(preface, 1);
            preface.add(new Paragraph("Referente a serviços prestados de "+of.getFuncionario().getFuncao()+"."));
            
            addEmptyLine(preface, 4);
            
            Calendar data = Calendar.getInstance();
            String mes = NomeDoMes(data.get(Calendar.MONTH), 0);
            int ds = data.get(Calendar.DAY_OF_WEEK);
            
            preface.add(new Paragraph("Campo Grande, "+data.get(Calendar.DATE)+ " de "+
                    mes+" de "+data.get(Calendar.YEAR) + " (" + DiaDaSemana(ds, 1) + ")."));
            addEmptyLine(preface, 1);
            preface.add(new Paragraph("Emitente: "+of.getFuncionario().getNome()+" - CPF/RG: "+ of.getFuncionario().getCpf()));
            addEmptyLine(preface, 1);
            preface.add(new Paragraph("Endereço: "+of.getFuncionario().getEndereco()));
            addEmptyLine(preface, 1);
            preface.add(new Paragraph("Assinatura: _____________________________"));
            
            document.add(preface);
        }catch(DocumentException | IOException de){
            System.err.println(de.getMessage());
        }
        document.close();
        
    }
    
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
          paragraph.add(new Paragraph(" "));
        }
    }
    
    public static String NomeDoMes(int i, int tipo) { 
        String mes[] = {"janeiro", "fevereiro", "março", "abril", "maio", "junho", "julho", "agosto", "setembro", "outubro", "novembro", "dezembro"};
        if (tipo == 0)
            return(mes[i-1]);
        else return(mes[i-1].substring(0, 3));
    }

    public static String DiaDaSemana(int i, int tipo) { 
        String diasem[] = {"domingo", "segunda-feira", "terça-feira", "quarta-feira", "quinta-feira", "sexta-feira", "sábado"};
        if (tipo == 0) 
            return(diasem[i-1]);
        else return(diasem[i-1].substring(0, 3));
    }
}

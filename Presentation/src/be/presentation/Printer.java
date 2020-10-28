package be.presentation;


import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JEditorPane;

public class Printer {
	private static JEditorPane p = new JEditorPane();
	private static boolean showPrintDialog = true;
	private static MessageFormat footer = new MessageFormat(" Page #{0,number,integer}");
	private static MessageFormat header = new MessageFormat("");
	
	public static void print(ArrayList<Spectacle> listeSp) {
		Collections.sort(listeSp);
		
		p.setContentType("text/html");
		Configuration cfg = FmConfig.getConfiguration();
		Template templ = null;
		
		try {
			templ = cfg.getTemplate("test.ftlh");
			
			HashMap<String, Object> root = new HashMap<String, Object>();
		    root.put( "spectacles", listeSp );
		    Writer out = new StringWriter();
			
			try {
				templ.process(root, out);
				
				p.setText(out.toString());
				
				try {
					boolean complete = p.print(header, footer, showPrintDialog, null, null, false);
				    if (complete) {
				        System.out.println("Impression OK");
				    } else {
				    	System.out.println("Impression annulée");
				    }
				} catch (PrinterException pe) {
					System.out.println("Problème avec l'imprimante");
				}
			} catch (TemplateException | IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


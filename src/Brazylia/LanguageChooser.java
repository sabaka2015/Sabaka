package Brazylia;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import net.miginfocom.swing.MigLayout;



public class LanguageChooser extends JFrame {
	/**
	 * 
	 */
	private final long serialVersionUID = 1L;

	Object selectedValue;
	
	public LanguageChooser(){
	setLayout(new MigLayout());
	
	Object[] values={"English", "Polski"};
	
	selectedValue=JOptionPane.showInputDialog(this, "Witaj w grze!", "Wybór Języka", JOptionPane.INFORMATION_MESSAGE, null, values, values[0]);

	}

	Object getLanguage(){
		return selectedValue;
	}
	
	
}

class GettingLine{
	InputStreamReader isr;
	BufferedReader buf;
	LanguageChooser lang=new LanguageChooser();
	
	/*public GettingLine(LanguageChooser l){
	lang=l;
	}
	*/
	{
	String name = null;
	if (lang.getLanguage()=="English") name="english.txt";
	else {
		if (lang.getLanguage()=="Polski") name="polski.txt";
		else System.exit(0);
	}
	
	File file =new File(name);
	FileInputStream fis=null;
	
	try {
		fis = new FileInputStream(file);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	isr=new InputStreamReader(fis, Charset.forName("utf-8"));
	buf=new BufferedReader(isr);
	}
	
	String getLine(){
		String linia=null;
		try {
			linia = buf.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return linia;
		
	}
	}

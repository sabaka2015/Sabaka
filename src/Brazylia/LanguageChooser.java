package Brazylia;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import net.miginfocom.swing.MigLayout;

/**
 * Author: Mateusz
 * Texts from txt files. 
 * Subtitles are downloaded by getLine function.
 */

public class LanguageChooser extends JFrame {

	private static final long serialVersionUID = 1L;
	Object selectedValue;
	
	public LanguageChooser(){
	setLayout(new MigLayout());
	
	Object[] values={"English", "Polski"};
	
	selectedValue=JOptionPane.showInputDialog(this, "Witaj w grze!", "Wybór Języka",+
			JOptionPane.INFORMATION_MESSAGE, null, values, values[0]);

	}

	Object getLanguage(){
		return selectedValue;
	}
}

class GettingLine{
	InputStreamReader isr;
	BufferedReader buf;
	LanguageChooser lang=new LanguageChooser();
	
	{
	String name = null;
	if (lang.getLanguage()=="English") name="english.txt";
	else {
		if (lang.getLanguage()=="Polski") name="polski.txt";
		else System.exit(0);
	}
	InputStream fis=null;
	
	fis = getClass().getResourceAsStream(name);
	
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

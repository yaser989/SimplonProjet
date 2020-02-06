package org.openup.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class testController {
	

	
	@GetMapping("/index")
	public void vu() {
	
	}
	
	
	
	
	@PostMapping("/send")
	public String vu(Model model,@RequestParam(name = "nom", required = false)String nom) throws IOException {
		
		 String textToAppend = nom;
 BufferedWriter writer = new BufferedWriter(
		                 new FileWriter("C://Users//y.abbas//Desktop/samplefile.txt", true)  //Set true for append mode
		                            );  
//		    writer.newLine();   //Add new line
		    writer.write(textToAppend);
		    writer.close();
		return "redirect:/home";
	}
	
	
	@GetMapping("/home")
	public String vuz(Model model){
		

		BufferedReader br = null;
		String fileName = "C://Users//y.abbas//Desktop/samplefile.txt";
		try
		{
			FileInputStream fis = new FileInputStream(fileName);
			InputStreamReader isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			model.addAttribute("massage", line);
			return "home";
		}
		}
		catch (IOException e)
		{
			System.out.println("Impossible d'ouvrir le fichier " + fileName
					+ ".");
		}
		finally
		{
			try
			{
				if (br != null)
					br.close();
			}
			catch (IOException e)
			{
				System.out.println("Impossible de fermer le fichier " + fileName
						+ ".");
			}
		}
	
		return "index";
	}
	

}

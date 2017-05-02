package com.amazonaws.samples;

import javax.swing.JOptionPane;

public class Main {
	
	public static int ligado = 0;
	private static String[] choicesMain = {  "Selecione","IMAGENS","INSTANCIAS" };
	
	public static void main(String[] args) {
		
		while(ligado == 0){
		    String input = (String) JOptionPane.
		    						showInputDialog(null, 
		    										"Selecione o que você quer gerenciar:",
													"AWSManager", 
													JOptionPane.QUESTION_MESSAGE, 
													null,
													choicesMain, 
													choicesMain[0]);
		    
		    
		    //choicesMain[1] Imagem
		    //choisesMain[2] Instancia
		    if(input == choicesMain[2]){
		    	Main.telaInstancias();
		    }else if(input == choicesMain[1]){
		    	Main.telaImagens();		    	
		    }else{
		    	Main.still();
		    }
		}
		JOptionPane
		.showMessageDialog(null, "Até mais!\nGrupo - Andre 1, Andre 2, Português, Jay-Gay");
	}
	
	
	public static void telaImagens(){	
    	try {
			JOptionPane
			.showMessageDialog(
					null, 
					ImagensAWS.criar(), 
					"Start Image",
					JOptionPane.INFORMATION_MESSAGE);
			Main.still();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void telaInstancias(){
			try {
				InstancesAWS.main();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				e.printStackTrace();
			}
	    
	    Main.still();

	}
	
	public static void still(){
	    ligado = JOptionPane.showConfirmDialog(null, 
				   "Deseja continuar?",
				   "AWSManager",JOptionPane.YES_NO_OPTION);
	}
}

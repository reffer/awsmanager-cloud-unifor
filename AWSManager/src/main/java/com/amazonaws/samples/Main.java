package com.amazonaws.samples;

import javax.swing.JOptionPane;

public class Main {
	
	public static int ligado = 0;
	private static String[] choicesMain = {  "Selecione","IMAGENS","INSTANCIAS" };
	private static String input;
	
	public static void main(String[] args) {
		
		while(ligado == 0){
		    input = (String) JOptionPane.showInputDialog(null, "Selecione o que você quer gerenciar:",
		        "AWSManager", JOptionPane.QUESTION_MESSAGE, null,
		        choicesMain, // Array of choices
		        choicesMain[0]); // Initial choice
		    
		    if(input == "INSTANCIAS"){
		    	Main.telaInstancias();
		    }else if(input == "IMAGENS"){
		    	Main.telaImagens();		    	
		    }else{
		    	Main.still();
		    }
		}

		JOptionPane.showMessageDialog(null, "Até mais!\nGrupo - Andre 1, Andre 2, Português, Jay-Gay");
	}
	
	
	public static void telaImagens(){
		ImagensAWS ec2 = new ImagensAWS();
    	try {
			JOptionPane.showMessageDialog(null, ec2.criar(), "Start Image",JOptionPane.INFORMATION_MESSAGE);
			Main.still();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void telaInstancias(){
	    String[] imagemOptions = { "Selecione","Listar","Iniciar","Parar","Remover" };
	    String inputImageOptions = (String) JOptionPane.showInputDialog(null, "O que você quer fazer?",
	        "AWSManager - INSTANCIAS", JOptionPane.QUESTION_MESSAGE, null,
	        imagemOptions, // Array of choices
	        imagemOptions[0]); // Initial choice
	    Main.still();

	}
	
	public static void still(){
	    ligado = JOptionPane.showConfirmDialog(null, 
				   "Deseja continuar?",
				   "AWSManager",JOptionPane.YES_NO_OPTION);
	}
}

package com.amazonaws.samples;

import javax.swing.JOptionPane;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.RunInstancesRequest;

public class ImagensAWS {
	
	public String criar() throws Exception {	
		
		Image linuxAmazon = new Image();
		linuxAmazon.setId("ami-c58c1dd3");
		linuxAmazon.setName("Amazon Linux AMI 2017.03.0 (HVM), SSD Volume Type");
		
		Image linuxRedHat = new Image();
		linuxRedHat.setId("ami-b63769a1");
		linuxRedHat.setName("Red Hat Enterprise Linux 7.3 (HVM), SSD Volume Type"); 
		
		Image linuxSuse = new Image();
		linuxSuse.setId("ami-fde4ebea");
		linuxSuse.setName("SUSE Linux Enterprise Server 12 SP2 (HVM), SSD Volume Type"); 
		
		Image[] imageLista = {
				linuxAmazon,
				linuxRedHat,
				linuxSuse
		};
		
		String[] stringLista = {
				"Selecione",
				imageLista[0].getName() ,
				imageLista[1].getName() ,
				imageLista[2].getName()
		};
		
		AWSCredentials credentials = new ProfileCredentialsProvider("default").getCredentials();
        AmazonEC2 ec2 = new AmazonEC2Client(credentials);
        
        
        JOptionPane.showMessageDialog(null, "A imagem selecionada será criada em US-EAST"+
        									"\nEsta é a região mais barata atualmente."
        									, "Imagens - AVISO", JOptionPane.INFORMATION_MESSAGE);
        
        ec2.setEndpoint("ec2.us-east-1.amazonaws.com");
        
        String input = (String) JOptionPane.showInputDialog(null, "Selecione a imagem para criar",
		        "AWSManager - Images", JOptionPane.QUESTION_MESSAGE, null,
		        stringLista, // Array of choices
		        stringLista[0]); // Initial choice
       
        for(int n = 0; n<3;n++){
        	if(input == imageLista[n].getName()){
        		RunInstancesRequest runInstancesRequest = new RunInstancesRequest();
            	runInstancesRequest.withImageId(imageLista[n].getId())
        	                     .withInstanceType("t2.micro")
        	                     .withMinCount(1)
        	                     .withMaxCount(1)
        	                     .withKeyName("uniforTests")
        	                     .withSecurityGroups("default");
            	ec2.runInstances(runInstancesRequest);
            	continue;
        	}
        	if (n == 2)
        		return "Nenhuma Imagem foi selecionada.";
        }    	
    	return "Criada com sucesso! Instancia será iniciada.";
    }

}
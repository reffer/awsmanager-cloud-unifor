package com.amazonaws.samples;

import java.util.List;

import javax.swing.JOptionPane;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;

public class InstancesAWS {
	
	private static String[] lista = {};
	private static String[] imagemOptions = { "Selecione","Iniciar","Parar","Terminar" };
	private static String input;
	
    @SuppressWarnings("deprecation")
	public static void main() throws Exception {
    	AWSCredentials credentials = new ProfileCredentialsProvider("default").getCredentials();
        AmazonEC2 ec2 = new AmazonEC2Client(credentials);

        DescribeInstancesResult describeInstancesRequest = ec2.describeInstances();
        List<Reservation> reservations = describeInstancesRequest.getReservations();

        for (Reservation reservation : reservations) {
        	List<Instance> instances = reservation.getInstances();
        	for (Instance instance : instances) {
        		lista = InstancesAWS.addList(instance);
        	}
        	
        }
        String select = (String) JOptionPane.showInputDialog(null, "Selecione uma instancia",
    	        "AWSManager - INSTANCIAS", JOptionPane.QUESTION_MESSAGE, null,
    	        lista, // Array of choices
    	        lista[0]); // Initial choice    
        
        for(int n= 0 ; n < lista.length; n++){
        	if(select == lista[n]){        	    
        	    input = (String) JOptionPane.showInputDialog(null, "O que você quer fazer?",
        	        "INSTANCIA - "+select, JOptionPane.QUESTION_MESSAGE, null,
        	        imagemOptions, // Array of choices
        	        imagemOptions[0]); // Initial choice
        		continue;
        	}
        }
        if(input == imagemOptions[1]){
        	// START
        	StartInstancesRequest request = new StartInstancesRequest()
            .withInstanceIds(select.substring(0,select.indexOf(" ")));
        	ec2.startInstances(request);

        	JOptionPane.showMessageDialog(null, "Instancia Iniciada");
        } else if(input == imagemOptions[2]){
        	// STOP
        	StopInstancesRequest request = new StopInstancesRequest()
            .withInstanceIds(select.substring(0,select.indexOf(" ")));
        	ec2.stopInstances(request);

        	JOptionPane.showMessageDialog(null, "Instancia Parada");
        ec2.stopInstances(request);
        } else if(input == imagemOptions[3]){
        	//	 TERMINATE
        	TerminateInstancesRequest request = new TerminateInstancesRequest()
        	.withInstanceIds(select.substring(0,select.indexOf(" ")));
        	ec2.terminateInstances(request);
        	
        	JOptionPane.showMessageDialog(null, "Instancia Terminada");
        }
    }
    
    public static String[] addList(Instance instance){
    	String[] newLista = new String[lista.length + 1];
		System.arraycopy(lista, 0, newLista, 0, lista.length);
			newLista[lista.length] = instance.getInstanceId() 
			+" - "+ instance.getState().getName();
			return newLista;
    }
}
        

package com.amazonaws.samples;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;

public class InstancesAWS {
	
	private static String[] lista = {};
	private static String text ="ID-Instance\t\tStatus\n\n";
    
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
        for(int n = 0;n < lista.length;n++){
        	text += lista[n]+"\n";
        }
        JOptionPane.showMessageDialog(null, new JTextArea(text), "AWSManager - Instance List",1);
        
    }
    
    public static String[] addList(Instance instance){
    	String[] newLista = new String[lista.length + 1];
		System.arraycopy(lista, 0, newLista, 0, lista.length);
		newLista[lista.length] = instance.getInstanceId() +"\t"+ instance.getState().getName();
		return newLista;
    }
}
        

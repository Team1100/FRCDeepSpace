package frc.robot;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;

/**
 * The purpose of this class is to create a set of methods to 
 * facilitate communication between a client and a robot using the UDP protocol.
 * Ideally, this class will be used to set up a UDP server on the robot that 
 * alows us to tune PID variables on the fly from a computer connected to the
 * robot network.
 */
public class UDPRecieve{
    DatagramSocket recieveSocket;
    byte[] recieveData = new byte[2048];
    DatagramPacket recievePacket;

    /**
     * Sets up the UDP socket that the robot will listen to for incoming information
     * @param clinet_inet_addr String of the client's internet address on the robot network
     * @param listening_port Integer port that the robot will listen on. NOTE: Should be between 5800 and 5810 per FMS Whitepaper
     */
    public UDPRecieve(String client_inet_addr, int listening_port){
        try{
            recieveSocket = new DatagramSocket(listening_port);
            recievePacket = new DatagramPacket(recieveData, recieveData.length);
        }catch (IOException e){
            System.out.println("Could not setup UDP reciever socket " + e.getMessage());
            recieveSocket = null;
        }
    }

    public String getPacket(){
        boolean last_packet = false;
        String recieved_text = "";
        if(recieveSocket != null){
            while(last_packet == false){
                try{
                    recieveSocket.receive(recievePacket);
                    recieved_text = new String(recievePacket.getData(), 0, recievePacket.getLength());
                }catch(SocketTimeoutException e){
                    if(recieved_text.length() != 0){
                        last_packet = true;
                    }
                }catch(IOException e){
                    System.out.println("Cant get data from UDP socket " + e.getMessage());
                    recieveSocket = null;
                }
            }
        }

        return recieved_text;
    }





}
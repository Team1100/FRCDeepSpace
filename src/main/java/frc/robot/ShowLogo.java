package frc.robot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import edu.wpi.first.wpilibj.Filesystem;

/**
 * Was bored so I decided to create a program that prints our logo at the start of a Robot Program.
 * Logo is in ascii located in /src/main/deploy/logo.txt
 */
public class ShowLogo{


    public static void ShowLogoOnStartup() {

        BufferedReader br;

        String path = Filesystem.getDeployDirectory().getPath() + "/logo.txt";

        try{
            br = new BufferedReader(new FileReader(path));
        } catch(FileNotFoundException exception1){
            System.out.println("File Not Found");
            return;
        }

        String logo = "";

        try{
            while((logo = br.readLine()) != null){
                System.out.println(logo);
            }
        }catch(IOException exception2){
            System.out.println("Input Output exception");
        }
        finally{
            try {
				br.close();
			} catch(IOException exception3) {
				System.out.println("Could Not Close Buffered Reader");
            } 
        }

    }
}
package bq.springbatch.demo.helloworld;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class TestdataGen {

	public static void main(String[] args) {
		URL is = TestdataGen.class.getClassLoader().getResource("batch_helloworld.csv");
		
		try {
			System.out.println(is.toURI());
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		
		try {
			FileWriter fw = new FileWriter(new File(is.toURI()));
			
			for(int i = 0; i < 100; i++) {
				fw.write("name" + i + "," + "value" + i + "\n");
			}
			
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
}

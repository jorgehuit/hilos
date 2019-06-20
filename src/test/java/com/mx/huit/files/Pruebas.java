package com.mx.huit.files;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.csvreader.CsvWriter;

import lombok.Data;
import lombok.extern.log4j.Log4j;

@Log4j
public class Pruebas {
	
	public static void main(String args []) {
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		};
		
	}

	@Test
	public void mensaje() throws IOException {
		Class<Pruebas> clazz = Pruebas.class;
		InputStream inputStream = clazz.getResourceAsStream("/file.txt");
		List<Persona> data = readFromInputStream(inputStream);
		log.info("tamanio de la lista :" + data.size());
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
//				crearCSV(data);
				System.out.println(mensajes());
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
		Thread thread2 = new Thread(runnable);
		thread2.start();
		Thread thread3 = new Thread(runnable);
		thread3.start();

	}
	
	private String mensajes() {
		return "hola";
	}

	private void crearCSV(List<Persona> data) {
		log.info("Entrando a la creacion de csv");
		String outputFile = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		outputFile = "C:/logs/file"  + dateFormat.format(new Date()) + ".csv";
		try {
			CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
			csvOutput.write("Nombre");
			csvOutput.write("aPaterno");
			csvOutput.write("aMaterno");
			csvOutput.endRecord();

			for(Persona personDTO : data){
				csvOutput.write(personDTO.getNombre());
				csvOutput.write(personDTO.getApPat());
				csvOutput.write(personDTO.getApMat());
				csvOutput.endRecord();
				
			}
			
			csvOutput.close();
			
		}catch (IOException e) {
				e.printStackTrace();
				log.error("Error al crear CSV...");
			}
	}

	private List<Persona> readFromInputStream(InputStream inputStream) throws IOException {
		List<Persona> listPerson = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] split = line.split(" ");
				listPerson.add(new Persona(split[0], split[1], split[2]));
			}
		}
		return listPerson;
	}
	
	@Data
	private class Persona{
		private String nombre;
		private String apMat;
		private String apPat;
		
		public Persona(String nombre, String apMat, String apPat) {
			super();
			this.nombre = nombre;
			this.apMat = apMat;
			this.apPat = apPat;
		}

		public Persona() {
		}
		
	}
}

package com.revolving.data;

import java.io.*;
import java.util.*;

import com.revolving.data.object.Store;

public class StoreData {

	public static ArrayList<Store> list;

	static {
		//test
		StoreData.list = new ArrayList<Store>();
	}

	public static void load() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("data\\store.txt"));

			String line;
	        while ((line = reader.readLine()) != null) {
	            String[] temp = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

	            for (int i = 0; i < temp.length; i++) {
	                temp[i] = temp[i].replaceAll("\"", "").trim();
	            }

	            String tel = (temp.length > 4) ? temp[4] : null; 

	            if (temp.length >= 4) {
	                Store store = new Store(temp[0], temp[1], temp[2], temp[3], tel);
	                StoreData.list.add(store);
	            } else {
	                System.out.println("Invalid data: " + Arrays.toString(temp));
	            }
	        }

			reader.close();
		} catch (Exception e) {
			System.out.println("at Data.load");
			e.printStackTrace();
		}
	}

	public static void save() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("data\\store.txt"));

			for (Store store : StoreData.list) {
				writer.write(String.format("%s,%s,%s,%s,%s\r\n", store.getNo(), store.getName(),
						store.getMenuNo(), store.getAddress(), store.getTel()));
			}

			writer.close();
		} catch (Exception e) {
			System.out.println("at Data.save");
			e.printStackTrace();
		}
	}
}

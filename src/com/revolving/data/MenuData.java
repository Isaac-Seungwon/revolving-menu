package com.revolving.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import com.revolving.data.object.Menu;

public class MenuData {

	public static ArrayList<Menu> list;

	static {
		MenuData.list = new ArrayList<Menu>();
	}

	public static void load() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("data\\Menu.txt"));

			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split(",");
				Menu menu = new Menu(temp[0], temp[1], temp[2], temp[3], temp[4]);
				MenuData.list.add(menu);
			}

			reader.close();
		} catch (Exception e) {
			System.out.println("at Data.load");
			e.printStackTrace();
		}
	}

	public static void save() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("data\\Menu.txt"));

			for (Menu menu : MenuData.list) {
				writer.write(String.format("%s,%s,%s,%s,%s\r\n", menu.getNo(), menu.getName(), menu.getCategoryNo(),
						menu.getSeasonNo(), menu.getPrice()));
			}

			writer.close();
		} catch (Exception e) {
			System.out.println("at Data.save");
			e.printStackTrace();
		}
	}
}

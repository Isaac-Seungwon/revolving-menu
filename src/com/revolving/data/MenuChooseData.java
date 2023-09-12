package com.revolving.data;

import java.io.*;
import java.util.*;

import com.revolving.data.object.MenuChoose;

public class MenuChooseData {

	public static ArrayList<MenuChoose> list;

	static {
		MenuChooseData.list = new ArrayList<MenuChoose>();
	}

	public static void load() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("data\\menuChoose.txt"));

			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split(",");
				MenuChoose menuChoose = new MenuChoose(temp[0], temp[1], temp[2], temp[3]);
				MenuChooseData.list.add(menuChoose);
			}

			reader.close();
		} catch (Exception e) {
			System.out.println("at Data.load");
			e.printStackTrace();
		}
	}

	public static void save() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("data\\menuChoose.txt"));

			for (MenuChoose menuChoose : MenuChooseData.list) {
				writer.write(String.format("%s,%s,%s,%s\r\n", menuChoose.getNo(), menuChoose.getMemberNo(),
						menuChoose.getMenuNo(), menuChoose.getDate()));
			}

			writer.close();
		} catch (Exception e) {
			System.out.println("at Data.save");
			e.printStackTrace();
		}
	}
}

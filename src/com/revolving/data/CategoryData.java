package com.revolving.data;

import java.io.*;
import java.util.*;

import com.revolving.data.object.Category;

public class CategoryData {

	public static ArrayList<Category> list;

	static {
		CategoryData.list = new ArrayList<Category>();
	}

	public static void load() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("data\\category.txt"));

			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split(",");
				Category category = new Category(temp[0], temp[1]);
				CategoryData.list.add(category);
			}

			reader.close();
		} catch (Exception e) {
			System.out.println("at Data.load");
			e.printStackTrace();
		}
	}

	public static void save() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("data\\category.txt"));

			for (Category Category : CategoryData.list) {
				writer.write(String.format("%s,%s\r\n", Category.getNo(), Category.getCookery()));
			}

			writer.close();
		} catch (Exception e) {
			System.out.println("at Data.save");
			e.printStackTrace();
		}
	}
}

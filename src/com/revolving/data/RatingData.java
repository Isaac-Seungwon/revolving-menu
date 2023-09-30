package com.revolving.data;

import java.io.*;
import java.util.*;

import com.revolving.data.object.Rating;

public class RatingData {

	public static ArrayList<Rating> list;

	static {
		RatingData.list = new ArrayList<Rating>();
	}

	public static void load() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("data\\storeReview.txt"));

			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split(",");
				Rating review = new Rating(temp[0], temp[1], temp[2], temp[3], temp[4]);
				RatingData.list.add(review);
			}

			reader.close();
		} catch (Exception e) {
			System.out.println("at Data.load");
			e.printStackTrace();
		}
	}

	public static void save() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("data\\storeReview.txt"));

			int reviewNumber = 1;
			
			for (Rating review : RatingData.list) {
				writer.write(String.format("%d,%s,%s,%s,%s\r\n", reviewNumber, review.getMemberNo(),
						review.getStoreNo(), review.getReview(), review.getScore()));
				reviewNumber++;
			}

			writer.close();
		} catch (Exception e) {
			System.out.println("at Data.save");
			e.printStackTrace();
		}
	}
}

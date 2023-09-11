package com.revolving.data;

import java.io.*;
import java.util.*;

import com.revolving.data.object.Member;

public class MemberData {

	public static ArrayList<Member> list;

	static {
		MemberData.list = new ArrayList<Member>();
	}

	public static void load() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("data\\member.txt"));

			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split(",");
				Member member = new Member(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5]);
				MemberData.list.add(member);
			}

			reader.close();
		} catch (Exception e) {
			System.out.println("at Data.load");
			e.printStackTrace();
		}
	}

	public static void save() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("data\\member.txt"));

			for (Member member : MemberData.list) {
				writer.write(String.format("%s,%s,%s,%s,%s,%s\r\n", member.getNo(), member.getName(), member.getId(),
						member.getPwd(), member.getTel(), member.getBirth()));
			}

			writer.close();
		} catch (Exception e) {
			System.out.println("at Data.save");
			e.printStackTrace();
		}
	}
}

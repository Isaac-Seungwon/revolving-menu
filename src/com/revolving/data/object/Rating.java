package com.revolving.data.object;

public class Rating {
	private String no;
	private String memberNo;
	private String storeNo;
	private String review;
	private String score;
	
	public Rating(String no, String memberNo, String storeNo, String review, String score) {
		this.no = no;
		this.memberNo = memberNo;
		this.storeNo = storeNo;
		this.review = review;
		this.score = score;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return String.format("%s,%s,%s,%s,%s", no, memberNo, storeNo, review, score);
	}
}

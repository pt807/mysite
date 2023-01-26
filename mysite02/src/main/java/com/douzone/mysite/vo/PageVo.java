package com.douzone.mysite.vo;

public class PageVo {
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int pageNum;
	private int amount;
	private int total;

	public PageVo(int pageNum, int amount, int total) {
		this.pageNum = pageNum;
		this.amount = amount;
		this.total = total;

		if (this.pageNum == 0) {
			this.pageNum += 1;
		}

		// ceil 반올림
		this.endPage = (int) Math.ceil(this.pageNum * 0.1) * this.amount;

		this.startPage = this.endPage - this.amount + 1;

		// 전체글 / 화면에 뿌려줄 데이터 개수
		int endPageTotal = (int) Math.ceil(this.total / (double) this.amount);

		if (this.endPage > endPageTotal) {
			this.endPage = endPageTotal;
		}

		this.prev = this.startPage > 1;

		this.next = this.endPage < endPageTotal;

	}
}

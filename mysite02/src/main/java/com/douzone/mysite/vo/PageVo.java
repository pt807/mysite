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

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "PageVo [startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next
				+ ", pageNum=" + pageNum + ", amount=" + amount + ", total=" + total + "]";
	}

}

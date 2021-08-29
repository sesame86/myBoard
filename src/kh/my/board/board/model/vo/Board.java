package kh.my.board.board.model.vo;

import java.sql.Date;

//CREATE TABLE BOARD(
//	    BNO NUMBER PRIMARY KEY,
//	    TITLE VARCHAR2(50) NOT NULL,
//	    CONTENT VARCHAR2(400) NOT NULL,
//	    CREATE_DATE DATE DEFAULT SYSDATE,
//	    WRITER VARCHAR2(20),
//	    DELETE_YN CHAR(2) DEFAULT 'N',
//	    FOREIGN KEY (WRITER) REFERENCES MEMBER(MEMBER_ID),
//	    CHECK(DELETE_YN IN('Y','N'))
//	);
//
//	-- BOARD 테이블의 PK로 사용될 시퀀스
//	CREATE SEQUENCE SEQ_BOARD;
public class Board {
	 private int bno;
	 private String title;
	 private String content;
	 private Date createDate;
	 private String writer;
	 private char deleteYn;
	
	public Board() {}
	public Board(String title, String content, String writer) {
		this.title = title;
		this.content = content;
		this.writer = writer;
	}
	public Board(int bno, String title, String content) {
		this.bno = bno;
		this.title = title;
		this.content = content;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getCreate_date() {
		return createDate;
	}
	public void setCreate_date(Date create_date) {
		this.createDate = create_date;
	}
	public char getDelete_yn() {
		return deleteYn;
	}
	public void setDelete_yn(char delete_yn) {
		this.deleteYn = delete_yn;
	}
	@Override
	public String toString() {
		return bno + ") title = " + title + " | content = " + content + " | " + createDate
				+ " | " + writer + " | " + deleteYn;
	}
}

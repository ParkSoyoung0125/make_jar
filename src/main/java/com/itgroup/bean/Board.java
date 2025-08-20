package com.itgroup.bean;

public class Board {
    private int no;
    private String writer;
    private String password;
    private String subject;
    private String content;
    private int readHit;
    private String regDate;

    public Board(int no, String writer, String password, String subject, String content, int readHit, String regDate) {
        this.no = no;
        this.writer = writer;
        this.password = password;
        this.subject = subject;
        this.content = content;
        this.readHit = readHit;
        this.regDate = regDate;
    }

    public Board() {

    }

    @Override
    public String toString() {
        return "Board{" +
                "no=" + no +
                ", writer='" + writer + '\'' +
                ", password='" + password + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", readHit=" + readHit +
                ", regDate='" + regDate + '\'' +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReadHit() {
        return readHit;
    }

    public void setReadHit(int readHit) {
        this.readHit = readHit;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
}

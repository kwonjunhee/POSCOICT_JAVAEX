package vo;

// VO = Value Object (값을 보관하는 객체)
public class NewsVO {
	private int id;
	private String writer; // =member.id
	private String title;
	private String writeDate;
	private String content;
	private int cnt;// 조회수
	private int good;
	private int bad;

	public int getGood() {
		return good;
	}

	public void setGood(int good) {
		this.good = good;
	}

	public int getBad() {
		return bad;
	}

	public void setBad(int bad) {
		this.bad = bad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "NewsVO [id=" + id + ", writer=" + writer + ", title=" + title + ", writeDate=" + writeDate
				+ ", content=" + content + ", cnt=" + cnt + ", good=" + good + ", bad=" + bad + "]";
	}
}

package src.br.ufrn.imd.lp2.model;

public class Quote {
	private String id;
	private String date;
	private String url;
	private String content;
	private String treatedContent;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTreatedContent() {
		return treatedContent;
	}
	public void setTreatedContent(String treatedContent) {
		this.treatedContent = treatedContent;
	}
}

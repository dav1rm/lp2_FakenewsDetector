package br.ufrn.imd.lp2.model;

public class Quote {
	private int id;
	private String data;
	private String url;
	private String content;
	private String treatedContent;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
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

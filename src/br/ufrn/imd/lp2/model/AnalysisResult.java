package br.ufrn.imd.lp2.model;

public class AnalysisResult {
	private String content;
	private double accuracy;
	private String fakenews;
	
	public AnalysisResult() {
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public double getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}

	public String getFakenews() {
		return fakenews;
	}

	public void setFakenews(String fakenews) {
		this.fakenews = fakenews;
	}

}

package br.ufrn.imd.lp2.model;

import java.util.ArrayList;

public class Fakenews extends Quote {
	private ArrayList <Exception> errs;

	public ArrayList <Exception> getErrs() {	return errs;	}
	public void setErrs(ArrayList <Exception> errs) {	this.errs = errs;	}
}

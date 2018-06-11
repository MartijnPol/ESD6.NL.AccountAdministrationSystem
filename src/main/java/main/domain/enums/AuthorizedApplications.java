package main.domain.enums;

import java.io.Serializable;

public enum AuthorizedApplications implements Serializable {

	DS("JgrTojsMQvZR4WFTh0gp"),
	DRIVER("oSCOrcWPw3tbNxp802mR");

	private String App;

	AuthorizedApplications(String app){
		this.App = app;
	}

	public String App(){
		return App;
	}
}

package main.domain.enums;

import java.io.Serializable;

public enum AuthorizedApplications implements Serializable {

	DS("JgrTojsMQvZR4WFTh0gp"),
	DRIVER("oSCOrcWPw3tbNxp802mR"),
	SIM("XD2DZafI3eq1Pg7fnYF1"),
	POL("kTRGkE488lp2v3Fyp2ZN");

	private String App;

	AuthorizedApplications(String app){
		this.App = app;
	}

	public String App(){
		return App;
	}
}

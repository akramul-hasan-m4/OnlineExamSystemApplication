package com.mfasia.onlineexamsystem.models;

public class ResultCounter {

	private int correctResult = 0;
	private int falseResult = 0;
	
	public int getCorrectResult() {
		return correctResult;
	}
	public void setCorrectResult(int cResult) {
		if (cResult > 0) {
			correctResult++;
		}
	}
	public int getFalseResult() {
		return falseResult;
	}
	public void setFalseResult(int fResult) {
		if (fResult >0) {
			falseResult++;
		}
	}
}

package io.manasobi.commons.constant;


/**
 * DESC : 작업 및 결과 상태에 관한 enum. 
 * 
 * Constans 상수에 의한 결과 처리도 magic number를 방지하는 방법이 될수도 있으나, 
 * type에 대해 보다 엄격한 체크가 가능하고 의미 전달을 명확하게 할수 있는 enum의 사용을 권장한다.  
 * 
 * @Company ePapyrus.inc
 * @author taewook.jang
 * @Date 2012. 3. 21. 오전 11:38:58
 */
public enum Status {
	
	SUCCESS(1), START(1), RUN(1), TRUE(1),
	EMPTY(0), WAIT(0),
	FAIL(-1), ERROR(-1), END(-1), STOP(-1), FALSE(-1);
	
	@SuppressWarnings("unused")
	private int code;
	
	private Status(int code) {
		this.code = code;
	}
	
}

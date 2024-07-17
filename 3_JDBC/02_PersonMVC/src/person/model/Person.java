package person.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// @NoArgsConstructor - 기본 생성자
// @Data : 기본 생성자 제외 나머지들 한꺼번에 만들어주는 어노테이션
// @AllArgsConstructor - 모든 필드값 파라미터로 받는 생성자
// @Setter - Setter
// @Getter - Getter
// @ToString - toString
@Data @NoArgsConstructor
public class Person {
	
	private int id;
	private String name;
	private int age;
	private String addr;
}

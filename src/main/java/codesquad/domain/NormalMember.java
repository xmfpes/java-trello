package codesquad.domain;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue("normal")
@Setter
@Getter
@ToString
public class NormalMember extends Member {
	public NormalMember(String uid, String upw, String uemail, Date regdate, Date updatedate) {
		super(uid, upw, uemail, regdate, updatedate);
	}

}

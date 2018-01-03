package codesquad.domain;

import java.util.Arrays;
import java.util.Date;

public class MemberBuilder {
	private String uid;
	private String upw;
	private String uemail;
	private Date regdate;
	private Date updatedate;
	
	public MemberBuilder setUid(String uid) {
		this.uid = uid;
		return this;
	}
	public MemberBuilder setUpw(String upw) {
		this.upw = upw;
		return this;
	}
	public MemberBuilder setUemail(String uemail) {
		this.uemail = uemail;
		return this;
	}
	public MemberBuilder setRegdate(Date regdate) {
		this.regdate = regdate;
		return this;
	}
	public MemberBuilder setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
		return this;
	}
	public Member build(){
        Member member = new Member(uid, upw, uemail, regdate, updatedate);
        MemberRole role = new MemberRole();
        role.setRoleName("BASIC");
        member.setRoles(Arrays.asList(role));
        return member;
    }
	
	
}

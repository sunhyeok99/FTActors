package com.a602.actors.domain.member.dto;

import com.a602.actors.domain.member.Member;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberDtoForChat {
	private Long id;
	private String stageName;
	private String profileImage;

	public static MemberDtoForChat from(Member member){
		return MemberDtoForChat.builder()
			.id(member.getId())
			.stageName(member.getStageName())
			.profileImage(member.getProfileImage())
			.build();
	}
}

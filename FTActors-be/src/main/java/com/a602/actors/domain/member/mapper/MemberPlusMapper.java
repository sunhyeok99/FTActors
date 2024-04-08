package com.a602.actors.domain.member.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.a602.actors.domain.member.Member;
import com.a602.actors.domain.member.dto.MemberPlusDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberPlusMapper {
	List<MemberPlusDTO> MemberListToMemberPlusDtoList(List<Member> member);
}

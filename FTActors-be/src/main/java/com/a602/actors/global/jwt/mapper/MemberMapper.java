package com.a602.actors.global.jwt.mapper;

import com.a602.actors.domain.member.Member;
import com.a602.actors.global.jwt.dto.JwtDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface    MemberMapper {
//    Member MemberDtoToMember(JwtDto.Simple jwtDto);

    JwtDto.Simple MemberToMemberDtoResponse(Member member);
}

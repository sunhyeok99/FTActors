package com.a602.actors.global.jwt.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface    MemberMapper {
//    Member MemberDtoToMember(JwtDto.Simple jwtDto);

//    JwtDto.Simple MemberToMemberDtoResponse(Member member);
}

package com.a602.actors.domain.profile.mapper;

import com.a602.actors.domain.profile.dto.ProfileListDto;
import com.a602.actors.domain.profile.entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProfileMapper {
    //To do: Oauth 구현 후, 멤버에서 아이디 추출해서 넣는 구현체 만들기
    List<ProfileListDto> ProfileListToProfileDtoList(List<Profile> profiles); //멤버에서 -> 아이디 추출해서 넣기 구현체 만들기

    ProfileListDto ProfileToProfileDto(Profile profile); //멤버에서 -> 아이디 추출해서 넣기 구현체 만들기
}

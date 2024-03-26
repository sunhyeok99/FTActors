package com.a602.actors.domain.profile.mapper;

import com.a602.actors.domain.profile.dto.ProfileDto;
import com.a602.actors.domain.profile.entity.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProfileMapper {
    public List<ProfileDto> ProfileListToProfileDtoList(List<Profile> profiles) {
        if ( profiles == null ) {
            return null;
        }

        List<ProfileDto> list = new ArrayList<ProfileDto>( profiles.size() );
        for ( Profile profile : profiles ) {
            list.add( ProfileToProfileDto( profile ) );
        }

        return list;
    }

    public ProfileDto ProfileToProfileDto(Profile profile) {
        if ( profile == null ) {
            return null;
        }

        ProfileDto.ProfileDtoBuilder profileDto = ProfileDto.builder();

        profileDto.id( profile.getId() );
        profileDto.memberId( profile.getMember().getId());
        profileDto.content( profile.getContent() );
        profileDto.type( profile.getType() );
        profileDto.portfolio( profile.getPortfolio() );
        profileDto.privatePost( profile.getPrivatePost() );

        return profileDto.build();
    }

}

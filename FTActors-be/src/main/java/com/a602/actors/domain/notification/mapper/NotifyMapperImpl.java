package com.a602.actors.domain.notification.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.a602.actors.domain.notification.document.Notify;
import com.a602.actors.domain.notification.dto.NotifyDto;

@Component
public class NotifyMapperImpl implements NotifyMapper {

	@Override
	public List<NotifyDto.Response> NotifyToNotifyDtoResponse(List<Notify> notify){
		return notify.stream()
			.map(NotifyDto.Response::from)
			.collect(Collectors.toList());
	}
}

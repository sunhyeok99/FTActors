package com.a602.actors.domain.notification.mapper;

import java.util.List;

import com.a602.actors.domain.notification.document.Notify;
import com.a602.actors.domain.notification.dto.NotifyDto;

public interface NotifyMapper {
	public List<NotifyDto.Response> NotifyToNotifyDtoResponse(List<Notify> notify);
}

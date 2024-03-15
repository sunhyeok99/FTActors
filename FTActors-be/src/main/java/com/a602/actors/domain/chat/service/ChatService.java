package com.a602.actors.domain.chat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.a602.actors.domain.chat.dto.ChatRoomDto;
import com.a602.actors.domain.chat.dto.ParticipantsDto;
import com.a602.actors.domain.chat.entity.ChatRoom;
import com.a602.actors.domain.chat.entity.Participants;
import com.a602.actors.domain.chat.mapper.ChatMapper;
import com.a602.actors.domain.chat.mapper.ParticipantsMapperImpl;
import com.a602.actors.domain.chat.repository.ChatRoomRepository;
import com.a602.actors.domain.chat.repository.ParticipantsRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {
	private final ChatRoomRepository chatRoomRepository;
	private final ParticipantsRepository participantsRepository;
	private final ChatMapper chatMapper;
	// private final ParticipantsMapper participantsMapper;
	private final ParticipantsMapperImpl participantsMapper;

	public void createChatRoom(String title) {
		chatRoomRepository.save(new ChatRoom(title));
	}

	public List<ChatRoomDto> findAllChatRooms() {
		return chatMapper.ChatRoomListToChatRoomDtoList(chatRoomRepository.findAll());
	}

	public ChatRoomDto findRoomByRoomId(Long roomId) {
		ChatRoom chatRoom = chatRoomRepository.findById(roomId)
			.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 채팅방입니다."));
		return chatMapper.ChatRoomToChatRoomDto(chatRoom);
	}

	public void joinChat(Long roomId, Long memberId) {
		Optional<Participants> participants =
			participantsRepository.getParticipantsByChatRoomIdAndMemberId(roomId, memberId);

		validateJoinChat(participants);

		participantsRepository.save(
			participantsMapper.ParticipantsDtoToParticipants(ParticipantsDto.of(roomId, memberId)));

		// insert는 기본적으로 조회 - 유효성 검사 - 저장 순서로 이루어진다.

		// 정적 팩토리 메서드, 규칙이니까 그냥 외워 ㅎ
		// 		- LocalDateTime.of -> 이거 parameter 여러개니까 of
		// 		- LocalDateTime.from -> 이건 parameter 한개니까 from

		// shift + F6 : 프로젝트 전체 이름 일괄 수정

		// ctrl + alt + m : 지정한 블록을 메서드로 추출
		// 					return 값이 0개 또는 1개인 것만 가능. 2개 이상이면 애초에 안빼짐.
	}

	public void quitChat(Long roomId, Long memberId){
		Long id = participantsRepository.getParticipantsByChatRoomIdAndMemberId(roomId, memberId).get().getId();
		participantsRepository.deleteById(id);
	}

	private void validateJoinChat(Optional<Participants> participants) {
		if (participants.isPresent()) {
			log.info("validateJoinChat ========== {}", participants.toString());
			throw new DataIntegrityViolationException("같은 채팅방에 중복해서 참가할 수 없습니다.");
		}
	}
}

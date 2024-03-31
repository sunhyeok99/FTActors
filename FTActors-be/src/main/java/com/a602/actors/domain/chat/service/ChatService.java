//package com.a602.actors.domain.chat.service;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.amqp.core.AmqpAdmin;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.stereotype.Service;
//
//import com.a602.actors.domain.chat.dto.ChatRoomDto;
//import com.a602.actors.domain.chat.dto.ParticipantsDto;
//import com.a602.actors.domain.chat.entity.ChatRoom;
//import com.a602.actors.domain.chat.entity.Participants;
//import com.a602.actors.domain.chat.mapper.ChatMapper;
//import com.a602.actors.domain.chat.mapper.ParticipantsMapperImpl;
//import com.a602.actors.domain.chat.repository.ChatRoomRepository;
//import com.a602.actors.domain.chat.repository.ChatRoomRepositoryCustomImpl;
//import com.a602.actors.domain.chat.repository.ParticipantsRepository;
//
//import jakarta.persistence.EntityNotFoundException;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class ChatService {
//	private final ChatRoomRepository chatRoomRepository;
//	private final ChatRoomRepositoryCustomImpl chatRoomRepositoryCustom;
//	private final ParticipantsRepository participantsRepository;
//	private final ChatMapper chatMapper;
//	private final ParticipantsMapperImpl participantsMapper;
//	private final AmqpAdmin amqpAdmin;
//	private final RabbitTemplate rabbitTemplate;
//
//	public void createChatRoom(String title) {
//		chatRoomRepository.save(new ChatRoom(title)).getId();
//
//		// Long chatRoomId = chatRoomRepository.save(new ChatRoom(title)).getId();
//
//		// Queue queue = new Queue("chat.queue." + chatRoomId);
//		// amqpAdmin.declareQueue(queue);
//		//
//		// TopicExchange exchange = new TopicExchange("chat.exchange");
//		// amqpAdmin.declareExchange(exchange);
//		//
//		// Binding binding = BindingBuilder.bind(queue).to(exchange).with("*.room." + chatRoomId);
//		// amqpAdmin.declareBinding(binding);
//		//
//		// // 해당 채팅방에 대한 @RabbitListener 동적 설정
//		// createRabbitListener(chatRoomId);
//	}
//
//	// private void createRabbitListener(Long chatRoomId) {
//	// 	SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//	// 	container.setConnectionFactory(rabbitTemplate.getConnectionFactory());
//	// 	container.setQueueNames("chat.queue." + chatRoomId);
//	//
//	// 	// 메시지 리스너 설정
//	// 	MessageListenerAdapter listenerAdapter = new MessageListenerAdapter();
//	// 	container.setMessageListener(listenerAdapter);
//	// 	container.start();
//	// }
//
//	// @RabbitListener(queues = "#{container.queueNames}")
//	// public void handleMessage(ChatMessageDto chatMessageDto) {
//	// 	log.info("Received message: {}", chatMessageDto.getMessage());
//	// }
//
//	// private void createRabbitListener(Long chatRoomId) {
//	// 	SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//	// 	container.setConnectionFactory(rabbitTemplate.getConnectionFactory());
//	// 	container.setQueueNames("chat.queue." + chatRoomId);
//	// 	container.setMessageListener(new MessageListenerAdapter(new Object() {
//	// 		@RabbitListener(queues = "#{container.queueNames}")
//	// 		public void receive(ChatMessageDto chatMessageDto) {
//	// 			log.info("receive ============ chatDto.getMessage() = {}", chatMessageDto.getMessage());
//	// 		}
//	// 	}));
//	// 	container.start();
//	// }
//
//	// private void createRabbitListener(Long chatRoomId) {
//	// 	SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//	// 	container.setConnectionFactory(rabbitTemplate.getConnectionFactory());
//	// 	container.setQueueNames("chat.queue." + chatRoomId);
//	//
//	// 	// 메시지 리스너 어댑터 설정
//	// 	MessageListenerAdapter listenerAdapter = new MessageListenerAdapter(new Object() {
//	// 		@RabbitListener(queues = "#{container.queueNames}")
//	// 		public void handleMessage(ChatMessageDto chatMessageDto) {
//	// 			log.info("Received message: {}", chatMessageDto.getMessage());
//	// 		}
//	// 	});
//	//
//	// 	// 리스너 어댑터를 컨테이너에 등록
//	// 	container.setMessageListener(listenerAdapter);
//	// 	container.start();
//	// }
//
//	public List<ChatRoomDto> findAllChatRooms() {
//		return chatMapper.ChatRoomListToChatRoomDtoList(chatRoomRepository.findAll());
//	}
//
//	public ChatRoomDto findRoomByRoomId(Long roomId) {
//		ChatRoom chatRoom = chatRoomRepository.findById(roomId)
//			.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 채팅방입니다."));
//		return chatMapper.ChatRoomToChatRoomDto(chatRoom);
//	}
//
//	public void joinChat(Long roomId, Long memberId) {
//		Optional<Participants> participants =
//			participantsRepository.getParticipantsByChatRoomIdAndMemberId(roomId, memberId);
//
//		validateJoinChat(participants);
//
//		participantsRepository.save(
//			participantsMapper.ParticipantsDtoToParticipants(ParticipantsDto.of(roomId, memberId)));
//
//		// insert는 기본적으로 조회 - 유효성 검사 - 저장 순서로 이루어진다.
//
//		// 정적 팩토리 메서드, 규칙이니까 그냥 외워 ㅎ
//		// 		- LocalDateTime.of -> 이거 parameter 여러개니까 of
//		// 		- LocalDateTime.from -> 이건 parameter 한개니까 from
//
//		// shift + F6 : 프로젝트 전체 이름 일괄 수정
//
//		// ctrl + alt + m : 지정한 블록을 메서드로 추출
//		// 					return 값이 0개 또는 1개인 것만 가능. 2개 이상이면 애초에 안빼짐.
//	}
//
//	public void quitChat(Long roomId, Long memberId){
//		Long id = participantsRepository.getParticipantsByChatRoomIdAndMemberId(roomId, memberId).get().getId();
//		participantsRepository.deleteById(id);
//	}
//
//	public List<ChatRoomDto.Response> getMyChatRoomList(Long memberId){
//		List<ChatRoomDto.Response> list = chatRoomRepositoryCustom.getMyChatRoomList(memberId);
//		Collections.sort(list);
//
//		return list;
//	}
//
//	private void validateJoinChat(Optional<Participants> participants) {
//		if (participants.isPresent()) {
//			log.info("validateJoinChat ========== {}", participants.toString());
//			throw new DataIntegrityViolationException("같은 채팅방에 중복해서 참가할 수 없습니다.");
//		}
//	}
//}

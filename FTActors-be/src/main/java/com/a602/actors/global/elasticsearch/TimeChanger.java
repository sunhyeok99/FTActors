package com.a602.actors.global.elasticsearch;

import java.time.*;
import java.time.format.*;

public class TimeChanger {
    // UTC 시간을 한국 시간으로 변환하는 메서드
    public static LocalDateTime convertUtcToKoreaTime() {
        // 현재 시간을 UTC로 가져옵니다.
        ZonedDateTime utcTime = ZonedDateTime.now(ZoneOffset.UTC);

        // 한국 시간으로 변환합니다.
        ZonedDateTime koreaTime = utcTime.withZoneSameInstant(ZoneId.of("Asia/Seoul"));

        // ZonedDateTime을 LocalDateTime으로 변환합니다.
        LocalDateTime koreaLocalDateTime = koreaTime.toLocalDateTime();

        return koreaLocalDateTime;
    }
}


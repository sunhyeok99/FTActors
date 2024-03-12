package com.a602.actors.global.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KakaoService {
    private static String GRANT_TYPE = "grant_type";
    private static String CLIENT_ID = "client_id";
    private static String REDIRECT_URI_KEY = "redirectt_uri_key";
    private static String BEARER = "Bearer";


}

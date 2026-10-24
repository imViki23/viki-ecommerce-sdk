package com.viki.api.security.services;

import com.viki.api.security.clients.OpaClient;
import com.viki.api.security.models.OpaRequest;
import com.viki.api.security.models.OpaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class OpaAuthorizationService {
    private final OpaClient opaClient;

    public boolean isAuthorized(String method, String path, List<String> roles) {
        OpaRequest opaRequest = OpaRequest.builder()
                .input(OpaRequest.OpaRequestInput.builder()
                        .method(method)
                        .path(Stream.of(path.split("/")).filter(s -> !s.isEmpty()).toList())
                        .roles(roles)
                        .build())
                .build();
        OpaResponse opaResponse = opaClient.checkAuthorization(opaRequest);
        return opaResponse.isResult();
    }
}

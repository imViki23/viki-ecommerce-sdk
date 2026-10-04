package com.viki.api.security.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OpaRequest {
    private OpaRequestInput input;

    @Data
    @Builder
    public static class OpaRequestInput {
        private String method;
        private List<String> path;
        private List<String> roles;
    }
}

package com.viki.api.common.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class DebeziumDto<T> implements Serializable {
    private DebeziumPayload<T> payload;

    @Data
    public static class DebeziumPayload<T> {
        private T before;
        private T after;
        private DebeziumSource source;
        private String op;
    }

    @Data
    public static class DebeziumSource {
        private String schema;
        private String table;
        private String db;
    }
}

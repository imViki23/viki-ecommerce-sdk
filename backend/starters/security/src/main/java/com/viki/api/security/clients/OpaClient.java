package com.viki.api.security.clients;

import com.viki.api.security.models.OpaRequest;
import com.viki.api.security.models.OpaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "opa-client", url = "${opa.url:http://localhost:8181}")
public interface OpaClient {

    @PostMapping("/v1/data/authz/allow")
    OpaResponse checkAuthorization(OpaRequest request);
}

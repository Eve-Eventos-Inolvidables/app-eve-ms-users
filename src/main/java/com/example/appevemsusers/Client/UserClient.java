package com.example.appevemsusers.Client;

import com.example.appevecommon.Service.Utilities.Responses.Response;
import com.example.appevemsusers.Config.FeignInterceptorConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient( name = "app-eve-ms-auth",configuration = FeignInterceptorConfig.class)
public interface UserClient {
    @GetMapping("/api/users/{id}")
     Response<Object> getById(@PathVariable Long id);
}

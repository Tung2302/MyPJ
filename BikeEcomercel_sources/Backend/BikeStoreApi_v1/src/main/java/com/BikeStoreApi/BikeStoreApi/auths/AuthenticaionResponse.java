package com.BikeStoreApi.BikeStoreApi.auths;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticaionResponse {
    private String token;
    private String refreshToken;
}

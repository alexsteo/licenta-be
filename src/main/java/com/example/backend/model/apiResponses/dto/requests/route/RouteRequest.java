package com.example.backend.model.apiResponses.dto.requests.route;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RouteRequest {
    private String from;
    private String to;
}

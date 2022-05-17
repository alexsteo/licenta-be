package com.example.backend.model.dto.requests.route;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RouteRequest {
    private String from;
    private String to;
}

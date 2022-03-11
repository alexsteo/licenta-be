package com.example.backend.model.apiResponses.apis.route;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoundingBox{
    public Lr lr;
    public Ul ul;
}

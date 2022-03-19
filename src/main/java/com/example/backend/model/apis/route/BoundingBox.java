package com.example.backend.model.apis.route;

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

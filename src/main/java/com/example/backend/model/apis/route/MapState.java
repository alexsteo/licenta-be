package com.example.backend.model.apis.route;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MapState{
    public Center center;
    public Integer width;
    public Integer scale;
    public Integer height;
}

package com.guilherme.fiapfood.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class CustomerRequestDTO {
    private String name;
    private String cpf;
    private String email;
}

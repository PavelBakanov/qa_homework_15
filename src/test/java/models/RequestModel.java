package models;

import lombok.*;

@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class RequestModel {
    @NonNull
    String email, password, name, job;
}

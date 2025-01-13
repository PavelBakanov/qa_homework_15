package models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class RequestModel {
    String email, password, name, job, updatedAt;
}

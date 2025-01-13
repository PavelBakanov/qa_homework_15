package models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class RequestModel {
    final String name, job, email, password;
}

package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.ResponseSpecification;

public class CheckEmailSpec {
    public static ResponseSpecification usersResponseSpec = new ResponseSpecBuilder()
            .log(LogDetail.BODY)
            .expectStatusCode(200)
            .log(LogDetail.STATUS)
            .build();
}

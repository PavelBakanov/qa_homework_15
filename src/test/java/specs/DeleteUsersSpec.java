package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.ResponseSpecification;

public class DeleteUsersSpec {
    public static ResponseSpecification deleteResponseSpec = new ResponseSpecBuilder()
            .log(LogDetail.BODY)
            .expectStatusCode(204)
            .log(LogDetail.STATUS)
            .build();
}

package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:properties/${env}.properties",
        "classpath:properties/local.properties"
})
public interface WebConfig extends Config {
    @Key("baseURI")
    String getBaseUri();

    @Key("basePath")
    String getBasePath();
}

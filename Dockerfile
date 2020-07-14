FROM openjdk:11
ADD target/trade_point*.jar trade_point.jar
ENTRYPOINT ["java", "-jar", "trade_point.jar"]
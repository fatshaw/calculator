# RPN calculator

## BigDecimal transaction

* new BigDecimal from string = 1.2 with scale = 15 and rounding mode is floor
new BigDecimal("1.2").setScale(15,RoundingMode.FLOOR)

* run app
./gradlew build  
java -jar build/libs/cacluator-1.0-SNAPSHOT.jar   
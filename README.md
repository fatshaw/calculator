# RPN calculator

## BigDecimal transaction

* new BigDecimal from string = 1.2 with scale = 15 and rounding mode is floor
new BigDecimal("1.2").setScale(15,RoundingMode.FLOOR)

* run app
./gradlew build  
java -jar build/libs/cacluator-1.0-SNAPSHOT.jar   

## Check Pointer
1. OOD，是否有OO思想，去model这个计算器
2. SOLID Principles的体现，看的时候想一下如果要加一个方法的话，会需要多少改动。如果候选人用了注册的方法来添加operations，用了memento pattern，一般就可以认为达标。
3. 类、方法命名
4. BigDecimal的处理
5. History Stack的数据结构选择，用Deque比较好，Stack算及格。
6. Divide, Square Root的处理
7. 测试覆盖
8. build script
9. log
10. exception handling
11. input output的处理
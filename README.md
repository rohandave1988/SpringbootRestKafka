

Step 1: Download kafka from Apache Kafka site

Step 2: Extract the folder and go to the kafka location

Step 3: Start the ZooKeeper and Kafka Server by using the below command

Step 4: Start ZooKeeper

bin/zookeeper-server-start.sh config/zookeeper.properties

Step 5: Start Kafka Server

bin/kafka-server-start.sh config/server.properties

Step 6: Create a topic named accountTopic using the below command in another terminal, in the apache kafka installation directory:-

bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic accountTopic

Step 7: Build the code by running the command in the project home directory:-

mvn clean install

Step 8: Run the jar using command:-

java -jar /spring-rest-kafka-producer-consumer/target/spring-rest-kafka-producer-consumer-0.0.1-SNAPSHOT.jar

Step 9: Go to browser and hit the following URL:-

http://localhost:8051/publish/acc/21121312313

Step 10: Console will show the complete JSON with Account Details

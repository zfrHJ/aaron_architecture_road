FROM openjdk:alpine 

RUN echo "Asia/Shanghai" > /etc/timezone
#RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
ENV TZ=Asia/Shanghai


# 添加项目构建出来的 Jar 包
ADD spring-0.0.1-SNAPSHOT.jar /root/bin/starter.jar

WORKDIR /root/bin

EXPOSE 8080

ENV JAVA_OPTS="\
-server \
-Xmx4g \
-Xms4g \
-Xmn2g \
-XX:SurvivorRatio=8 \
-XX:MetaspaceSize=256m \
-XX:MaxMetaspaceSize=512m \
-XX:+UseParallelGC \
XX:ParallelGCThreads=4 \
-XX:+UseParallelOldGC \
-XX:+UseAdaptiveSizePolicy \
-XX:+PrintGCDetails \
-XX:+PrintTenuringDistribution \
-XX:+PrintGCTimeStamps \
-XX:+HeapDumpOnOutOfMemoryError \
-XX:HeapDumpPath=/ \
-Xloggc:/gc.log \
-XX:+UseGCLogFileRotation \
-XX:NumberOfGCLogFiles=5 \
-XX:GCLogFileSize=10M"

#ENV JAVA_OPTS="-Dspring.profiles.active=dev"


# 设定镜像的主命令
CMD ["java", "-jar", "starter.jar", "--spring.profiles.active=dev", "-Djava.security.egd=file:/dev/./urandom"]

# 另外一种方式添加；启动的时候添加参数
#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /starter.jar.jar" ]
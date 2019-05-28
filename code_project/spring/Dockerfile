FROM openjdk:alpine 

RUN echo "Asia/Shanghai" > /etc/timezone
#RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
ENV TZ=Asia/Shanghai


# 添加项目构建出来的 Jar 包
ADD spring-0.0.1-SNAPSHOT.jar /root/bin/starter.jar

WORKDIR /root/bin

EXPOSE 8080

# 设定镜像的主命令
CMD ["java", "-jar", "starter.jar", "--spring.profiles.active=dev", "-Djava.security.egd=file:/dev/./urandom"]

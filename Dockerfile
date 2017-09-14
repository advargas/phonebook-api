FROM davidcaste/alpine-java-unlimited-jce:jre8
WORKDIR /opt
ADD target/phonebook-api-*.jar /opt/app.jar
RUN sh -c 'touch /opt/app.jar'
ENV spring.application.name phonebook-api
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dloader.classpath=$PWD", "-jar","/opt/app.jar"]

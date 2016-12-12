FROM anapsix/alpine-java:8
ADD app.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 1099 1100 8801
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Xdebug","-Xrunjdwp:transport=dt_socket,address=1100,server=y,suspend=n","-Dcom.sun.management.jmxremote=","-Dcom.sun.management.jmxremote.port=1099","-Dcom.sun.management.jmxremote.rmi.port=1099","-Dcom.sun.management.jmxremote.ssl=false","-Dcom.sun.management.jmxremote.authenticate=false","-Djava.rmi.server.hostname=192.168.99.100","-jar","/app.jar"]

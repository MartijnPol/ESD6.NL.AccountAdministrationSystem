FROM oracle/glassfish

RUN curl https://repo1.maven.org/maven2/mysql/mysql-connector-java/5.1.45/mysql-connector-java-5.1.45.jar -o $GLASSFISH_HOME/glassfish/lib/mysql-connector-java-5.1.45.jar

COPY glassfish-resources.xml /tmp/glassfish-resources.xml
RUN asadmin start-domain domain1 \
    && asadmin add-resources /tmp/glassfish-resources.xml \
    && asadmin stop-domain domain1
COPY target/AccountAdministrationSystem.war $GLASSFISH_HOME/glassfish/domains/domain1/autodeploy/AccountAdministrationSystem.war
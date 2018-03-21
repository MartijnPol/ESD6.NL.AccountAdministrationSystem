FROM airhacks/glassfish
COPY ./target/AccountAdministrationSystem.war ${DEPLOYMENT_DIR}

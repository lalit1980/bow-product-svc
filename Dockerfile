# Required for starting application up.
RUN apt-get update && apt-get add /bin/sh

RUN mkdir -p /opt/app
ENV PROJECT_HOME /opt/app

COPY target/bow-product-svc-1 $PROJECT_HOME/bow-product-svc.jar

WORKDIR $PROJECT_HOME

CMD ["java" ,"-jar","./bow-product-svc.jar"]

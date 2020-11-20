# Required for starting application up.
RUN apk update && apk add /bin/sh

RUN mkdir -p /opt/app
ENV PROJECT_HOME /opt/app

COPY target/bow-product-svc-1.0.jar $PROJECT_HOME/bow-product-svc.jar

WORKDIR $PROJECT_HOME

CMD ["java" ,"-jar","./bow-product-svc.jar"]

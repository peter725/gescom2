mvn install:install-file -Dfile=./../ge-resources/libs/bcprov-jdk15on-152.jar -DgroupId=bouncycastle -DartifactId=bcprov-jdk15on -Dversion=152 -Dpackaging=jar -DgeneratePom=true

mvn install:install-file -Dfile=./../ge-resources/libs/clave2-client-1.0.0.jar -DgroupId=es.mscbs.claveclient -DartifactId=clave2-client -Dversion=1.0.0 -Dpackaging=jar -DgeneratePom=true

mvn install:install-file -Dfile=./../ge-resources/libs/eidas-encryption-1.4.3-clave.jar -DgroupId=eu.eidas -DartifactId=eidas-encryption -Dversion=1.4.3-clave -Dpackaging=jar -DgeneratePom=true

mvn install:install-file -Dfile=./../ge-resources/libs/eidas-commons-1.4.3-clave.jar -DgroupId=eu.eidas -DartifactId=eidas-commons -Dversion=1.4.3-clave -Dpackaging=jar -DgeneratePom=true

mvn install:install-file -Dfile=./../ge-resources/libs/eidas-configmodule-1.4.3-clave.jar -DgroupId=eu.eidas -DartifactId=eidas-configmodule -Dversion=1.4.3-clave -Dpackaging=jar -DgeneratePom=true

mvn install:install-file -Dfile=./../ge-resources/libs/eidas-saml-engine-1.4.3-clave.jar -DgroupId=eu.eidas -DartifactId=eidas-saml-engine -Dversion=1.4.3-clave -Dpackaging=jar -DgeneratePom=true

mvn install:install-file -Dfile=./../ge-resources/libs/eidas-light-commons-1.4.3-clave.jar -DgroupId=eu.eidas -DartifactId=eidas-light-commons -Dversion=1.4.3-clave -Dpackaging=jar -DgeneratePom=true

mvn install:install-file -Dfile=./../ge-resources/libs/core.base-2.3.0.jar -DgroupId=es.mspsi.ovfrw -DartifactId=core.base -Dversion=2.3.0 -Dpackaging=jar -DgeneratePom=true

mvn install:install-file -Dfile=./../ge-resources/libs/Commons-2.0.1.jar -DgroupId=eu.stork.msssi -DartifactId=Commons -Dversion=2.0.1 -Dpackaging=jar -DgeneratePom=true

mvn install:install-file -Dfile=./../ge-resources/libs/opensaml-2.6.5-eidas_1.jar -DgroupId=org.opensaml -DartifactId=opensaml -Dversion=2.6.5-eidas_1 -Dpackaging=jar -DgeneratePom=true

mvn install:install-file -Dfile=./../ge-resources/libs/openws-1.5.5.jar -DgroupId=org.opensaml -DartifactId=openws -Dversion=1.5.5 -Dpackaging=jar -DgeneratePom=true

mvn install:install-file -Dfile=./../ge-resources/libs/xmltooling-1.4.5.jar -DgroupId=org.opensaml -DartifactId=xmltooling -Dversion=1.4.5 -Dpackaging=jar -DgeneratePom=true

mvn install:install-file -Dfile=./../ge-resources/libs/aut-sdk-2.1.1.jar -DgroupId=es.sag -DartifactId=autentica -Dversion=2.1.1 -Dpackaging=jar -DgeneratePom=true



mvn install:install-file -Dfile=./../ge-commons/target/gescom-commons-app-0.0.2.jar -DgroupId=es.consumo.gescom -DartifactId=gescom-commons-app -Dversion=0.0.2 -Dpackaging=jar -DgeneratePom=true

mvn install:install-file -Dfile=../ge-commons/target/gescom-commons-app-0.0.2.jar -DgroupId=es.consumo.gescom -DartifactId=gescom-commons-app -Dversion=0.0.2 -Dpackaging=jar -DgeneratePom=true

localhost:9080/jjaa-auth-app/oauth/token
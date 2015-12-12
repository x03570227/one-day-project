#!/bin/sh  
#memcached auto-start   
#  
# description: Auto-starts memcached  
# processname: memcached  
# pidfile: /var/memcached.pid   

 
case $1 in  
p)  
    mvn clean package -Pproduction -Dmaven.test.skip
    ;;
pt)
	mvn clean package -Ptest -Dmaven.test.skip
	;;
ci)
	mvn clean install -Dmaven.test.skip -DdownloadSources=true
	;;
ee)
	mvn eclipse:eclipse -Dwtpversion=2.0 -DdownloadSources=true
	;;
ec)
	mvn eclipse:clean
	;;
td)
	mvnDebug tomcat7:run
	;;
t)
	mvn tomcat7:run
	;;
s)
	mvn dependency:sources
	;;
gb)
	git branch -av --color
	;;
init)
	mvn install:install-file -Dfile="lib/net/caiban/yy-utils/1.1.9/yy-utils-1.1.9.jar" -DgroupId=net.caiban -DartifactId=yy-utils -Dversion=1.1.9 -Dpackaging=jar -DpomFile="lib/net/caiban/yy-utils/1.1.9/yy-utils-1.1.9.pom" -Dsources="lib/net/caiban/yy-utils/1.1.9/yy-utils-1.1.9-sources.jar"
	mvn install:install-file -Dfile="lib/net/caiban/yy-utils/1.2.0/yy-utils-1.2.0.jar" -DgroupId=net.caiban -DartifactId=yy-utils -Dversion=1.2.0 -Dpackaging=jar -DpomFile="lib/net/caiban/yy-utils/1.2.0/yy-utils-1.2.0.pom" -Dsources="lib/net/caiban/yy-utils/1.2.0/yy-utils-1.2.0-sources.jar"
	mvn install:install-file -Dfile="lib/net/caiban/yy-conn/1.0.2/yy-conn-1.0.2.jar" -DgroupId=net.caiban -DartifactId=yy-conn -Dversion=1.0.2 -Dpackaging=jar -DpomFile="lib/net/caiban/yy-conn/1.0.2/yy-conn-1.0.2.pom" -Dsources="lib/net/caiban/yy-conn/1.0.2/yy-conn-1.0.2-sources.jar"
	mvn install:install-file -Dfile="lib/net/caiban/yy-util-mvcupload/1.1.0/yy-util-mvcupload-1.1.0.jar" -DgroupId=net.caiban -DartifactId=yy-util-mvcupload -Dversion=1.1.0 -Dpackaging=jar -DpomFile="lib/net/caiban/yy-util-mvcupload/1.1.0/yy-util-mvcupload-1.1.0.pom" -Dsources="lib/net/caiban/yy-util-mvcupload/1.1.0/yy-util-mvcupload-1.1.0-sources.jar"
	;;
*)  
    echo 'p:package -Pproduction; pt:package -Ptest; ci:clean install; ee: eclipse:eclipse'
    echo 'ec:eclipse:clean; td:debug tomcat:run; t: tomcat:run; s: dependency:sources'
    ;;  
esac  
  
exit 0

http://www.mkyong.com/java/java-web-start-jnlp-tutorial-unofficial-guide/

keytool -genkey -keystore testKeys -alias jdc

jarsigner -keystore testKeys TestJnlp.jar jdc

mvn webstart:jnlp

http://mojo.codehaus.org/webstart/webstart-maven-plugin/jnlp-mojo.html
### 打包

mvn clean compile test-compile

mvn jar:jar jar:test-jar dependency:copy-dependencies

mvn assembly:single -Ddescriptor=src/test/resources/assembly.xml

### 测试

unzip apijson.zip

java -cp apijson-tests.jar\;apijson.jar\;dependency/fastjson-1.2.83.jar\;dependency/h2-2.2.224.jar\;dependency/HikariCP-4.0.3.jar\;dependency/hutool-all-5.8.28.jar\;dependency/slf4j-api-1.7.30.jar apijson.hutool.DemoApp

### 可选自定义MENIFEST.MF

jar -uvfm target/apijson-tests.jar src/test/resources/META-INF/MANIFEST.MF

java -jar apijson-tests.jar

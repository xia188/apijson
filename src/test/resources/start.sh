#!/bin/bash

JVM_OPS="-Dapijson.debug=false -Dapijson.adminUserId=0 -Dapijson.correctJson=true"

cp=apijson-tests.jar\;apijson.jar

for file in `find dependency/*.jar`; do
cp=$cp\;$file
done

java -cp $cp apijson.hutool.DemoApp

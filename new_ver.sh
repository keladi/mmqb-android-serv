tag=`date +"1.%Y%m%d.%H%M%S"`
./gradlew install
git add .
git commit -a -m -
git tag $tag
git push origin --tags

echo $tag

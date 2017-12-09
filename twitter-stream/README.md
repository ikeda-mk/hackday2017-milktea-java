# twitter-stream

### 使い方

src/main/resource/twitter4j.properties.sampleをsrc/main/resource/twitter4j.properties にリネーム

中身の次の４設定を修正 (正しいtwitter apiの認証情報に書き換える)

- oauth.consumerKey=REPLACE_ME
- oauth.consumerSecret=REPLACE_ME
- oauth.accessToken=REPLACE_ME
- oauth.accessTokenSecret=REPLACE_ME


```
./mvnw clean package -DskipTests
java -jar target/twitter-stream-0.0.1-SNAPSHOT.jar
```


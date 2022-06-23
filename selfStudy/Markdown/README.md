## 문자열을 Markdown 형식으로 변환하기

- https://noties.io/Markwon/
- Markwon 라이브러리 사용

---
<br>

- 테이블 형식 사용하려면 https://noties.io/Markwon/docs/v4/ext-tables/#theme 이 링크 참고!
- 기본 Markwon으로는 테이블 적용x
```kotlin
val markwon = Markwon.create(this)
```

![image](https://user-images.githubusercontent.com/69443895/175331188-57e91189-f393-4497-b9b9-1061b46c0ee2.png)

<br>

```kotlin
val markwon = Markwon.builder(this)
            .usePlugin(TablePlugin.create(this))
            .build()
```

![image](https://user-images.githubusercontent.com/69443895/175331372-cf3e24f0-cb07-4c26-911c-522d6c0dda34.png)


---

<br>

### EditText에 입력한 문자열 변환하기

![markdown1](https://user-images.githubusercontent.com/69443895/175331792-816a07d3-932d-415e-b5b9-0cc9fcb74c6a.gif)

<br>



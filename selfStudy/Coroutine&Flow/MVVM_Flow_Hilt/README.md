## MVVM + Flow + Hilt 를 적용한 예제

- 프로젝트 구조

![image](https://user-images.githubusercontent.com/69443895/168019333-41ee9669-070d-4d86-8b6c-1d100eb260cc.png)

---

- https://jsonplaceholder.typicode.com/posts 의 json data를 로드하여 리사이클러뷰에 나타내도록 구현
- 네트워크 통신 : retrofit 라이브러리 사용
- di : Hilt 사용

---

- Repository 에서 데이터 요청 후 flow로 반환

![image](https://user-images.githubusercontent.com/69443895/168020152-124b9d5a-0b3e-4b5e-a6a5-ec7700b0fa82.png)

<br>

- ViewModel 에서 StateFlow에 데이터 저장

![image](https://user-images.githubusercontent.com/69443895/168020376-29efe58f-732e-4990-83ce-b0fb6ba296fe.png)

<br>

- Activity 에서는 데이터 상태에 따라 UI 처리

![image](https://user-images.githubusercontent.com/69443895/168020828-3bdbad7d-5a4f-4558-841f-e29f0aa2ca32.png)


